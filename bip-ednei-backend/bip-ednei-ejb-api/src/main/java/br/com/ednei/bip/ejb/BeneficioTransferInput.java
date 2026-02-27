package br.com.ednei.bip.ejb;

import java.io.Serializable;
import java.math.BigDecimal;

public record BeneficioTransferInput(Long fromId, Long toId, BigDecimal amount) implements Serializable {
}
