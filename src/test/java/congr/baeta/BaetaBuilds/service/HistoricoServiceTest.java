package congr.baeta.BaetaBuilds.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
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

import congr.baeta.BaetaBuilds.dto.historico.HistoricoDTO;
import congr.baeta.BaetaBuilds.model.Historico;
import congr.baeta.BaetaBuilds.repository.ApartamentoRepository;
import congr.baeta.BaetaBuilds.repository.HistoricoRepository;
import congr.baeta.BaetaBuilds.repository.TerritorioRepository;

@ExtendWith(MockitoExtension.class)
public class HistoricoServiceTest {

    @InjectMocks
    private HistoricoService historicoService;

    @Mock
    ApartamentoRepository aptoRepository;

    @Mock
    TerritorioRepository territorioRespository;

    @Mock
    HistoricoRepository historicoRepository;

    @Captor
    private ArgumentCaptor<Historico> histCaptor;

    @Test
    void testGerarHistorico() {
        //Arrange
        when(territorioRespository.minDatInit()).thenReturn(LocalDate.now().minusDays(50));
        when(aptoRepository.countAptos()).thenReturn(100);
        when(aptoRepository.countAptosFeitos()).thenReturn(100);

        //Act
        historicoService.gerarHistorico();

        //Assert
        BDDMockito.then(historicoRepository).should().save(histCaptor.capture());
        Historico historico = histCaptor.getValue();
        Assertions.assertThat(historico.getDataIni()).isEqualTo(LocalDate.now().minusDays(50));
        Assertions.assertThat(historico.getDataFim()).isEqualTo(LocalDate.now());
        Assertions.assertThat(historico.getTotAptos()).isEqualTo(100);
        Assertions.assertThat(historico.getNumAptosFeitos()).isEqualTo(100);
        Assertions.assertThat(historico.getTotDias()).isEqualTo(50);
    }

    @Test
    void testListarHistorico() {
        //Arrange
        Historico h1 = new Historico();
        h1.setNumAptosFeitos(10);
        h1.setTotAptos(20);
        h1.setDataIni(LocalDate.of(2024, 1, 1));
        h1.setDataFim(LocalDate.of(2024, 1, 10));
        h1.setTotDias(10);

        Historico h2 = new Historico();
        h2.setNumAptosFeitos(5);
        h2.setTotAptos(15);
        h2.setDataIni(LocalDate.of(2024, 2, 1));
        h2.setDataFim(LocalDate.of(2024, 2, 5));
        h2.setTotDias(5);
        
        when(historicoRepository.findAll()).thenReturn(Arrays.asList(h1, h2));

        //Act
        List<HistoricoDTO> listaDTO = historicoService.listarHistorico();

        //Arrange
        assertEquals(2, listaDTO.size());

        HistoricoDTO dto1 = listaDTO.get(0);
        assertEquals(10, dto1.numAptosFeitos());
        assertEquals(20, dto1.totAptos());
        assertEquals(LocalDate.of(2024, 1, 1), dto1.dataIni());
        assertEquals(LocalDate.of(2024, 1, 10), dto1.dataFim());
        assertEquals(10, dto1.totDias());

        HistoricoDTO dto2 = listaDTO.get(1);
        assertEquals(5, dto2.numAptosFeitos());
        assertEquals(15, dto2.totAptos());
        assertEquals(LocalDate.of(2024, 2, 1), dto2.dataIni());
        assertEquals(LocalDate.of(2024, 2, 5), dto2.dataFim());
        assertEquals(5, dto2.totDias());
    }
}
