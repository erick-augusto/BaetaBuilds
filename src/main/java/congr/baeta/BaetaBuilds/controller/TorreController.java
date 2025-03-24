package congr.baeta.BaetaBuilds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import congr.baeta.BaetaBuilds.dto.CadastroTorreDTO;
import congr.baeta.BaetaBuilds.dto.DadosTorreDTO;
import congr.baeta.BaetaBuilds.dto.RetornoTorreDTO;
import congr.baeta.BaetaBuilds.model.Torre;
import congr.baeta.BaetaBuilds.repository.TerritorioRepository;
import congr.baeta.BaetaBuilds.repository.TorreRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/torre")
public class TorreController {

    @Autowired
    private TorreRepository repository;
    @Autowired
    private TerritorioRepository territorioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<RetornoTorreDTO> cadastrar(@RequestBody DadosTorreDTO dados) {
        System.out.println("Nova torre cadastrada");

        var territorio = territorioRepository.findById(dados.territorioID());
        if(territorio.isPresent()){
            var cadastro = new CadastroTorreDTO(dados, territorio.get());

            var torre = repository.save(new Torre(cadastro));
            
            return ResponseEntity.ok(new RetornoTorreDTO(torre));
        } else{
            return ResponseEntity.badRequest().body(new RetornoTorreDTO("Território não encontrado"));
        }

    }
}
