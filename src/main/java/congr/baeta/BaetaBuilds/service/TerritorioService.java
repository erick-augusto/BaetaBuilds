package congr.baeta.BaetaBuilds.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import congr.baeta.BaetaBuilds.model.Territorio;
import congr.baeta.BaetaBuilds.repository.TerritorioRepository;
import jakarta.transaction.Transactional;

@Service
public class TerritorioService {

    @Autowired
    TerritorioRepository repository;

    public Territorio cadTerrirorio(int totalAptos){
        return repository.save(new Territorio(totalAptos));
    }

    public Optional<Territorio> findById(Long territorioID) {
        return repository.findById(territorioID);
    }

    @Transactional
    public void resetarTerritorios() {
        repository.resetarTerritorios();
    }

}
