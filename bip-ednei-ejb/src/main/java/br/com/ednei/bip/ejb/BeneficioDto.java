package br.com.ednei.bip.ejb;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public record BeneficioDto(
        Long id,
        String nome,
        String descricao,
        BigDecimal valor,
        Boolean ativo,
        Long version
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static BeneficioDto of(Beneficio beneficio) {
        return new BeneficioDto(
                beneficio.getId(),
                beneficio.getNome(),
                beneficio.getDescricao(),
                beneficio.getValor(),
                beneficio.getAtivo(),
                beneficio.getVersion()
        );
    }

}
