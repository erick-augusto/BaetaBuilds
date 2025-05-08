package congr.baeta.BaetaBuilds.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import congr.baeta.BaetaBuilds.repository.ApartamentoRepository;
import congr.baeta.BaetaBuilds.repository.TerritorioRepository;

@ExtendWith(MockitoExtension.class)
public class ResetarServiceTest {

    @InjectMocks
    private ResetarService resetarService;

    @Mock
    private ApartamentoRepository apartamentoRepository;

    @Mock
    private TerritorioRepository territorioRepository;

    @Test
    void testResetarAptos() {
        //Act
        resetarService.resetarAptos();

        //Assert
        BDDMockito.then(apartamentoRepository).should().resetarAptos();
    }

    @Test
    void testResetarTerritorios() {
        //Act
        resetarService.resetarTerritorios();

        //Assert
        BDDMockito.then(territorioRepository).should().resetarTerritorios();
    }
}
