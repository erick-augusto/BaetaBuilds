package congr.baeta.BaetaBuilds.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import congr.baeta.BaetaBuilds.dto.historico.EstatisticaDTO;
import congr.baeta.BaetaBuilds.repository.ApartamentoRepository;
import congr.baeta.BaetaBuilds.repository.TerritorioRepository;

@ExtendWith(MockitoExtension.class)
public class EstatisticaServiceTest {
    
    @InjectMocks
    private EstatisticaService estatisticaService;

    @Mock
    private ApartamentoRepository aptoRepository;

    @Mock
    private TerritorioRepository territorioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    EstatisticaDTO estatistica;

    @Test
    void testGerarEstatisticas() {
        // Arrange
        when(aptoRepository.countAptosFeitos()).thenReturn(20);
        when(aptoRepository.countAptos()).thenReturn(100);
        when(territorioRepository.countTerritoriosFeitos()).thenReturn(5);
        when(territorioRepository.countTerritorios()).thenReturn(15);
        when(territorioRepository.minDatInit()).thenReturn(LocalDate.now().minusDays(50));
        
        // Act
        estatistica = estatisticaService.gerarEstatisticas();

        // Assert
        int totDias = LocalDate.now().getDayOfYear() - LocalDate.now().minusDays(50).getDayOfYear();
        double mediaAptosPorDia = (double) 20 / totDias;
        assertNotNull(estatistica);
        assertEquals(20, estatistica.totalAptosFeitos());
        assertEquals(80, estatistica.totalAptosNaoFeitos());
        assertEquals(5, estatistica.totalTerritoriosFeitos());
        assertEquals(10, estatistica.totalTerritoriosNaoFeitos());
        assertEquals(totDias, estatistica.totalDias());
        assertEquals(mediaAptosPorDia, estatistica.mediaAptosPorDia());
    }
}
