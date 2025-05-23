package congr.baeta.BaetaBuilds.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import congr.baeta.BaetaBuilds.dto.cadastro.DadosCondominioDTO;
import congr.baeta.BaetaBuilds.model.Territorio;
import congr.baeta.BaetaBuilds.model.Torre;
import congr.baeta.BaetaBuilds.repository.TorreRepository;
import jakarta.transaction.Transactional;

@Service
public class TorreService {

    @Autowired
    TorreRepository repository;

    public Torre cadastroTorre(DadosCondominioDTO dados, Territorio territorio){
        var nomeTorre = dados.nomeTorre();
        var totalAptos = dados.totalAptosTorre();
        var endereco = dados.endereco();
        var cep = dados.cep();

        return repository.save(new Torre(nomeTorre,endereco,cep,totalAptos, territorio));
    }

    public Optional<Torre> findById(Long idTorre) {
        return repository.findById(idTorre);
    }

    @Transactional
    public void addAptoTorre(Torre torre, int i) {
        repository.addAptoTorre(torre.getTorreID(), i);
    }
}
