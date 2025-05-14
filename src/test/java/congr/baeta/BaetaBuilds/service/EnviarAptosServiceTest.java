package congr.baeta.BaetaBuilds.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import congr.baeta.BaetaBuilds.model.Apartamento;
import congr.baeta.BaetaBuilds.model.Territorio;
import congr.baeta.BaetaBuilds.repository.ApartamentoRepository;
import congr.baeta.BaetaBuilds.repository.TerritorioRepository;

@ExtendWith(MockitoExtension.class)
public class EnviarAptosServiceTest {

    @InjectMocks
    private EnviarAptosService enviarAptosService;

    @Mock
    private TerritorioRepository territorioRepository;

    @Mock
    private ApartamentoRepository apartamentoRepository;

    @Captor
    private ArgumentCaptor<Territorio> territorioCaptor;

    @Captor
    private ArgumentCaptor<Apartamento> apartamentoCaptor;

    @Test
    void testBuscarAptosAleatorios() {
        
    }
    
    @Test
    void testUsarAptos() {
        //Arrange
        Apartamento apto1 = new Apartamento();
        Apartamento apto2 = new Apartamento();
        List<Apartamento> aptos = Arrays.asList(apto1, apto2);
        
        String nomeResponsavel = "João";
        LocalDate hoje = LocalDate.now();

        //Act
        enviarAptosService.usarAptos(aptos, nomeResponsavel);

        //Assert
        verify(apartamentoRepository, times(2)).save(apartamentoCaptor.capture());
        List<Apartamento> aptosCapturados = apartamentoCaptor.getAllValues();
        for (Apartamento apto : aptosCapturados) {
            Assertions.assertThat(apto.getNomeResponsavel()).isEqualTo(nomeResponsavel);
            Assertions.assertThat(apto.getDataEntrega()).isEqualTo(hoje);
        }
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
