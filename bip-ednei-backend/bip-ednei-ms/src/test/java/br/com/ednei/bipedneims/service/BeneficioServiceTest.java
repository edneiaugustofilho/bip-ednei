package br.com.ednei.bipedneims.service;

import br.com.ednei.bip.ejb.BeneficioDto;
import br.com.ednei.bip.ejb.BeneficioServiceRemote;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BeneficioServiceTest {

    @Mock
    private BeneficioServiceRemote beneficioServiceRemote;

    @InjectMocks
    private BeneficioService service;

    @Test
    void list_shouldDelegateToRemoteAndReturnResult() {
        List<BeneficioDto> expected = List.of(mock(BeneficioDto.class), mock(BeneficioDto.class));
        when(beneficioServiceRemote.list()).thenReturn(expected);

        List<BeneficioDto> result = service.list();

        assertThat(result).isSameAs(expected);
        verify(beneficioServiceRemote).list();
        verifyNoMoreInteractions(beneficioServiceRemote);
    }
}