package br.com.ednei.bipedneims.http;

import br.com.ednei.bip.ejb.BeneficioTransferInput;
import br.com.ednei.bip.ejb.BeneficioDto;
import br.com.ednei.bipedneims.service.BeneficioService;
import br.com.ednei.bipedneims.service.BeneficioTransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/beneficios")
public class BeneficioController {

    private final BeneficioTransferService beneficioTransferService;
    private final BeneficioService beneficioService;

    public BeneficioController(BeneficioTransferService beneficioTransferService,
                               BeneficioService beneficioService) {
        this.beneficioTransferService = beneficioTransferService;
        this.beneficioService = beneficioService;
    }

    @GetMapping
    public ResponseEntity<List<BeneficioDto>> list() {
        return ResponseEntity.ok(beneficioService.list());
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody BeneficioTransferInput input) {
        beneficioTransferService.transfer(input);

        return ResponseEntity.ok().build();
    }
}