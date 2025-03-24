package congr.baeta.BaetaBuilds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import congr.baeta.BaetaBuilds.dto.ConsultaTerritorioDTO;
import congr.baeta.BaetaBuilds.dto.DadosTerritorioDTO;
import congr.baeta.BaetaBuilds.dto.RetornoTerritorioDTO;
import congr.baeta.BaetaBuilds.model.Territorio;
import congr.baeta.BaetaBuilds.repository.TerritorioRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/territorio")
public class TerritorioController {

    @Autowired
    private TerritorioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<RetornoTerritorioDTO> cadastrar(@RequestBody DadosTerritorioDTO dados) {
        System.out.println("Novo território cadastrado");
        var territorio = repository.save(new Territorio(dados));

        return ResponseEntity.ok(new RetornoTerritorioDTO(territorio));
    }

    @GetMapping
    public ResponseEntity<List<ConsultaTerritorioDTO>> listar(){
        System.out.println("Listando territórios");
        var territorios = repository.findAll();
        var lista = territorios.stream().map(t -> new ConsultaTerritorioDTO(t)).toList();
        return ResponseEntity.ok(lista);
    }
}
