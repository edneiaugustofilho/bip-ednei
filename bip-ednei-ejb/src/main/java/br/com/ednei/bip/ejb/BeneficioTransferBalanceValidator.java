package br.com.ednei.bip.ejb;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BeneficioTransferBalanceValidator {

    public void execute(BeneficioTransferInput input, Beneficio from) {
        if (input == null) {
            throw new IllegalArgumentException("Requisição inválida");
        }
        if (from == null) {
            throw new IllegalStateException("Origem inválida");
        }
        if (from.getValor() == null || from.getValor().compareTo(input.amount()) < 0) {
            throw new IllegalStateException("Saldo insuficiente");
        }
    }

}
