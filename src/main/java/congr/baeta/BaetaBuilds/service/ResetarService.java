package congr.baeta.BaetaBuilds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import congr.baeta.BaetaBuilds.repository.ApartamentoRepository;
import congr.baeta.BaetaBuilds.repository.TerritorioRepository;
import jakarta.transaction.Transactional;

@Service
public class ResetarService {

    @Autowired
    private ApartamentoRepository apartamentoRepository;

    @Autowired
    private TerritorioRepository territorioRepository;

    @Transactional
    public void resetarAptos() {
        apartamentoRepository.resetarAptos();
    }

    @Transactional
    public void resetarTerritorios() {
        territorioRepository.resetarTerritorios();
    }
}
