package br.com.ednei.bip.ejb;

import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface BeneficioServiceRemote {
    void transfer(BeneficioTransferInput input);

    List<BeneficioDto> list();
}