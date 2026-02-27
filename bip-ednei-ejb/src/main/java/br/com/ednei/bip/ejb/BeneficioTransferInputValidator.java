package br.com.ednei.bip.ejb;

import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.util.Objects;

@ApplicationScoped
public class BeneficioTransferInputValidator {

    public void execute(BeneficioTransferInput input) {
        if (input == null) {
            throw new IllegalArgumentException("Requisição de transferência é inválida");
        }
        if (input.fromId() == null || input.fromId().compareTo(0L) <= 0) {
            throw new IllegalArgumentException("Origem de transferência é inválido");
        }
        if (input.toId() == null || input.toId().compareTo(0L) <= 0) {
            throw new IllegalArgumentException("Destino de transferência é inválido");
        }
        if (Objects.equals(input.fromId(), input.toId())) {
            throw new IllegalArgumentException("Origem e Destinos não podem ser iguais.");
        }
        if (input.amount() == null || input.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor de transferência é inválido.");
        }
    }

}
