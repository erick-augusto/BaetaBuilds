package congr.baeta.BaetaBuilds.service;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import congr.baeta.BaetaBuilds.model.Territorio;
import congr.baeta.BaetaBuilds.repository.TerritorioRepository;

@ExtendWith(MockitoExtension.class)
public class EnviarAptosServiceTest {

    @InjectMocks
    private EnviarAptosService enviarAptosService;

    @Mock
    private TerritorioRepository territorioRepository;

    @Captor
    private ArgumentCaptor<Territorio> territorioCaptor;

    @Test
    void testUsarAptos() {
        
    }

    @Test
    void testBuscarAptosAleatorios() {

    }

    @Test
    void testInicializarTerritorio() {
        //Arrange
        Territorio territorio = new Territorio();
        LocalDate hoje = LocalDate.now();
        territorio.setDataInicio(hoje);

        //Act
        enviarAptosService.inicializarTerritorio(territorio);

        //Assert
        BDDMockito.then(territorioRepository).should().save(territorioCaptor.capture());
        Territorio territorioCapturado = territorioCaptor.getValue();
        Assertions.assertThat(territorioCapturado.getDataInicio()).isEqualTo(hoje);
    }
    
    @Test
    void testFinalizarTerritorio() {
        //Arrange
        Territorio territorio = new Territorio();
        LocalDate hoje = LocalDate.now();
        territorio.setDataFim(hoje);

        //Act
        enviarAptosService.inicializarTerritorio(territorio);

        //Assert
        BDDMockito.then(territorioRepository).should().save(territorioCaptor.capture());
        Territorio territorioCapturado = territorioCaptor.getValue();
        Assertions.assertThat(territorioCapturado.getDataFim()).isEqualTo(hoje);
    }
}
