package br.com.ednei.bipedneims.service;

import br.com.ednei.bip.ejb.BeneficioServiceRemote;
import br.com.ednei.bip.ejb.BeneficioTransferInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BeneficioTransferServiceTest {

    @Mock
    private BeneficioServiceRemote beneficioServiceRemote;

    @InjectMocks
    private BeneficioTransferService service;

    @Test
    void transfer_shouldDelegateToRemote() {
        BeneficioTransferInput input = mock(BeneficioTransferInput.class);

        service.transfer(input);

        verify(beneficioServiceRemote).transfer(input);
        verifyNoMoreInteractions(beneficioServiceRemote);
    }

    @Test
    void transfer_shouldWrapAnyExceptionAsIllegalArgumentException_withSameMessage() {
        BeneficioTransferInput input = mock(BeneficioTransferInput.class);
        doThrow(new RuntimeException("remote failed")).when(beneficioServiceRemote).transfer(input);

        assertThatThrownBy(() -> service.transfer(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("remote failed");

        verify(beneficioServiceRemote).transfer(input);
        verifyNoMoreInteractions(beneficioServiceRemote);
    }
}