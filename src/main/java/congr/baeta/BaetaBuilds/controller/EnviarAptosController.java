package congr.baeta.BaetaBuilds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import congr.baeta.BaetaBuilds.dto.responsavel.ResponsavelDTO;
import congr.baeta.BaetaBuilds.dto.responsavel.RetornoAptosAleatoriosDTO;
import congr.baeta.BaetaBuilds.service.ApartamentoService;

@RestController
@RequestMapping("/enviar")
public class EnviarAptosController {

    @Autowired
    ApartamentoService service;

    @GetMapping
    public ResponseEntity<List<RetornoAptosAleatoriosDTO>> aptosAleatorios(@RequestBody ResponsavelDTO responsavel){
        var totalSolicitado = responsavel.totalAptos();
        if(totalSolicitado > 0){
            List<RetornoAptosAleatoriosDTO> lista = service.buscarAptosAleatorios(totalSolicitado, responsavel.nome());
            
            return ResponseEntity.ok(lista);
        } else{
            return ResponseEntity.badRequest().body(List.of());
        }
    }
    
}
