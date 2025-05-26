package congr.baeta.BaetaBuilds.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import congr.baeta.BaetaBuilds.dto.responsavel.RetornoAptosAleatoriosDTO;
import congr.baeta.BaetaBuilds.model.Apartamento;
import congr.baeta.BaetaBuilds.model.Territorio;
import congr.baeta.BaetaBuilds.model.Torre;
import congr.baeta.BaetaBuilds.repository.ApartamentoRepository;
import congr.baeta.BaetaBuilds.repository.TerritorioRepository;
import congr.baeta.BaetaBuilds.repository.TorreRepository;

@ExtendWith(MockitoExtension.class)
public class EnviarAptosServiceTest {

    @InjectMocks
    @Spy
    private EnviarAptosService enviarAptosService;

    @Mock
    private TerritorioRepository territorioRepository;

    @Mock
    private ApartamentoRepository apartamentoRepository;

    @Captor
    private ArgumentCaptor<Territorio> territorioCaptor;

    @Mock
    private TorreRepository torreRepository;

    @Captor
    private ArgumentCaptor<Apartamento> apartamentoCaptor;

    @Test
    void testBuscarAptosAleatorios() {
        // Arrange
        int totalSolicitado = 2;
        String responsavel = "João";

        // Mock de Torre e Territorio
        Territorio territorio = new Territorio();
        territorio.setTerritorioID(1L);
        territorio.setDataInicio(null);

        Torre torre = new Torre();
        torre.setTorreID(100L);
        torre.setEndereco("Rua das Flores");
        torre.setCep("12345-678");
        torre.setNomeTorre("Torre A");
        torre.setTerritorio(territorio);

        // Mock dos Apartamentos
        Apartamento apto1 = new Apartamento();
        apto1.setNumApto(101);
        apto1.setTorre(torre);

        Apartamento apto2 = new Apartamento();
        apto2.setNumApto(102);
        apto2.setTorre(torre);

        List<Apartamento> listaAptos = Arrays.asList(apto1, apto2);

        // Comportamento dos mocks
        when(apartamentoRepository.buscarAptosDisponiveis(totalSolicitado)).thenReturn(listaAptos);
        when(torreRepository.findById(100L)).thenReturn(Optional.of(torre));
        when(apartamentoRepository.verificaAptosFaltando(anyLong()))
        .thenReturn(1) // na primeira vez ainda tem apto faltando
        .thenReturn(0); // na segunda vez não tem mais, então finaliza

        // Act
        List<RetornoAptosAleatoriosDTO> resultado = enviarAptosService.buscarAptosAleatorios(totalSolicitado, responsavel);

        // Assert
        Assertions.assertThat(resultado).hasSize(2);

        Assertions.assertThat(resultado).anySatisfy(retorno -> {
        Assertions.assertThat(retorno.enderecoTorre()).isEqualTo("Rua das Flores - Torre A");
        Assertions.assertThat(retorno.cep()).isEqualTo("12345-678");
        Assertions.assertThat(retorno.numApto()).isIn(101, 102);
        });

        // Verifica que os métodos auxiliares foram chamados
        verify(apartamentoRepository).buscarAptosDisponiveis(totalSolicitado);
        verify(torreRepository, times(2)).findById(100L);
        verify(apartamentoRepository, times(2)).verificaAptosFaltando(1L);
        verify(apartamentoRepository, times(2)).save(any(Apartamento.class));

        // Verifica que os métodos internos foram executados
        verify(enviarAptosService).inicializarTerritorio(territorio);
        verify(enviarAptosService).finalizarTerritorio(territorio);
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
