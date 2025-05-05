package congr.baeta.BaetaBuilds.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import congr.baeta.BaetaBuilds.dto.historico.EstatisticaDTO;
import congr.baeta.BaetaBuilds.repository.ApartamentoRepository;
import congr.baeta.BaetaBuilds.repository.TerritorioRepository;

@Service
public class EstatisticaService {

    @Autowired
    ApartamentoRepository aptoRepository;

    @Autowired
    TerritorioRepository territorioRepository;

    public EstatisticaDTO gerarEstatisticas() {
        //Total aptos feitos
        int totAptosFeitos = aptoRepository.countAptosFeitos();
        //Total aptos não feitos
        int totAptosNaoFeitos = aptoRepository.countAptos() - totAptosFeitos;
        //Total territorios feitos
        int totTerritoriosFeitos = territorioRepository.countTerritoriosFeitos();
        //Total territorios não feitos
        int totTerritoriosNaoFeitos = territorioRepository.countTerritorios() - totTerritoriosFeitos;
        //Total dias
        LocalDate dataInicio = territorioRepository.minDatInit();
        LocalDate hoje = LocalDate.now();
        int totalDias = (int) (hoje.toEpochDay() - dataInicio.toEpochDay());
        if(totalDias == 0) {
            totalDias = 1;
        }
        //Total aptos por dia
        double medAptosPorDia = (double) totAptosFeitos / totalDias;

        EstatisticaDTO estatistica = new EstatisticaDTO(totAptosFeitos, totAptosNaoFeitos, totTerritoriosFeitos, totTerritoriosNaoFeitos, totalDias, medAptosPorDia);
        return estatistica;
    }
}
