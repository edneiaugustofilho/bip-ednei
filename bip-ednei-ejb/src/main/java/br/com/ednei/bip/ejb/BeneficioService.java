package br.com.ednei.bip.ejb;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;

import java.math.BigDecimal;
import java.util.List;

@Stateless
public class BeneficioService implements BeneficioServiceRemote {

    @PersistenceContext(unitName = "bipPU")
    private EntityManager em;

    @Inject
    private BeneficioTransferInputValidator inputValidator;

    @Inject
    private BeneficioTransferBalanceValidator balanceValidator;

    @Inject
    private BeneficioTransferAccountsValidator accountsValidator;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void transfer(BeneficioTransferInput input) {
        inputValidator.execute(input);

        Long a = input.fromId();
        Long b = input.toId();
        Long firstId = a < b ? a : b;
        Long secondId = a < b ? b : a;

        Beneficio first = em.find(Beneficio.class, firstId, LockModeType.PESSIMISTIC_WRITE);
        Beneficio second = em.find(Beneficio.class, secondId, LockModeType.PESSIMISTIC_WRITE);

        Beneficio from = a.equals(firstId) ? first : second;
        Beneficio to = a.equals(firstId) ? second : first;

        accountsValidator.execute(from, to);
        balanceValidator.execute(input, from);

        if (to.getValor() == null) {
            to.setValor(BigDecimal.ZERO);
        }

        from.setValor(from.getValor().subtract(input.amount()));
        to.setValor(to.getValor().add(input.amount()));
    }

    @Override
    public List<BeneficioDto> list() {
        List<Beneficio> list = em.createQuery("SELECT b FROM Beneficio b", Beneficio.class).getResultList();

        return list.stream().map(BeneficioDto::of).toList();
    }
}