package br.com.ednei.bip.ejb;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BeneficioTransferAccountsValidator {

    public void execute(Beneficio from, Beneficio to) {
        if (from == null) {
            throw new IllegalStateException("Origem não encontrado.");
        }
        if (!from.getAtivo()) {
            throw new IllegalStateException("Origem inativa.");
        }
        if (to == null) {
            throw new IllegalArgumentException("Destino não encontrado.");
        }
        if (!to.getAtivo()) {
            throw new IllegalArgumentException("Destino inativa.");
        }
    }

}
