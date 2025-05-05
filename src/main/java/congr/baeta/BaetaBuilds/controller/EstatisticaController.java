package congr.baeta.BaetaBuilds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import congr.baeta.BaetaBuilds.dto.historico.EstatisticaDTO;
import congr.baeta.BaetaBuilds.service.EstatisticaService;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticaController {

    @Autowired
    EstatisticaService estatisticaService;

    @GetMapping
    public ResponseEntity<EstatisticaDTO> gerarEstatisticas() {
        EstatisticaDTO estatisticas = estatisticaService.gerarEstatisticas();

        return ResponseEntity.ok(estatisticas);
    }
}
