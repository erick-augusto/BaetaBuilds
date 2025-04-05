package congr.baeta.BaetaBuilds.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import congr.baeta.BaetaBuilds.model.Historico;
import congr.baeta.BaetaBuilds.repository.ApartamentoRepository;
import congr.baeta.BaetaBuilds.repository.HistoricoRepository;
import congr.baeta.BaetaBuilds.repository.TerritorioRepository;

@Service
public class HistoricoService {

    @Autowired
    ApartamentoRepository aptoRepository;
    @Autowired
    TerritorioRepository territorioRespository;
    @Autowired
    HistoricoRepository historicoRepository;

    public void gerarHistorico(){
        Historico historico = new Historico();

        LocalDate dataInicio = territorioRespository.minDatInit();
        historico.setDataIni(dataInicio);
        historico.setDataFim(LocalDate.now());
        int totAptos = aptoRepository.countAptos();
        historico.setTotAptos(totAptos);
        int numAptosFeitos = aptoRepository.countAptosFeitos();
        historico.setNumAptosFeitos(numAptosFeitos);
        int totDias = LocalDate.now().getDayOfYear() - dataInicio.getDayOfYear();
        historico.setTotDias(totDias);

        historicoRepository.save(historico);
    }
}
