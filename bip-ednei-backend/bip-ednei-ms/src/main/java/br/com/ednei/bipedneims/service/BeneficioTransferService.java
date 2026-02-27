package br.com.ednei.bipedneims.service;

import br.com.ednei.bip.ejb.BeneficioServiceRemote;
import br.com.ednei.bip.ejb.BeneficioTransferInput;
import org.springframework.stereotype.Service;

@Service
public class BeneficioTransferService {

    private final BeneficioServiceRemote beneficioService;

    public BeneficioTransferService(BeneficioServiceRemote beneficioService) {
        this.beneficioService = beneficioService;
    }

    public void transfer(BeneficioTransferInput input) {
        try {
            beneficioService.transfer(input);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
