package congr.baeta.BaetaBuilds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import congr.baeta.BaetaBuilds.dto.historico.HistoricoDTO;
import congr.baeta.BaetaBuilds.service.HistoricoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/historico")
@SecurityRequirement(name = "bearer-key")
public class HistoricoController {

    @Autowired
    HistoricoService historicoService;

    @GetMapping
    public ResponseEntity<List<HistoricoDTO>> getHistorico() {
        List<HistoricoDTO> historico = historicoService.listarHistorico();

        return ResponseEntity.ok(historico);
    }
}
