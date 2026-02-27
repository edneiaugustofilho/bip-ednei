package br.com.ednei.bipedneims.service;

import br.com.ednei.bip.ejb.BeneficioServiceRemote;
import br.com.ednei.bip.ejb.BeneficioDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficioService {

    private final BeneficioServiceRemote beneficioServiceRemote;

    public BeneficioService(BeneficioServiceRemote beneficioServiceRemote) {
        this.beneficioServiceRemote = beneficioServiceRemote;
    }

    public List<BeneficioDto> list() {

        return beneficioServiceRemote.list();
    }

}
