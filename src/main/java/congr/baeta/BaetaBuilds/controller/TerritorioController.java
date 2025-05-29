package congr.baeta.BaetaBuilds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import congr.baeta.BaetaBuilds.dto.ConsultaTerritorioDTO;
import congr.baeta.BaetaBuilds.repository.TerritorioRepository;

@RestController
@RequestMapping("/territorio")
public class TerritorioController {

    @Autowired
    private TerritorioRepository repository;

    @GetMapping
    public ResponseEntity<List<ConsultaTerritorioDTO>> listar(){
        System.out.println("Listando territórios");
        var territorios = repository.findAll();
        var lista = territorios.stream().map(t -> new ConsultaTerritorioDTO(t)).toList();
        return ResponseEntity.ok(lista);
    }
}
