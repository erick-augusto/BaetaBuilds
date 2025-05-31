package congr.baeta.BaetaBuilds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import congr.baeta.BaetaBuilds.service.HistoricoService;
import congr.baeta.BaetaBuilds.service.ResetarService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/resetar")
@SecurityRequirement(name = "bearer-key")
public class ResetarController {

    @Autowired
    ResetarService resetarService;

    @Autowired
    HistoricoService historicoService;

    @PutMapping
    public ResponseEntity<String> resetarDados() {
        //Gerar novo histórico
        historicoService.gerarHistorico();
        // Lógica para resetar histórico de aptos e territórios concluídos
        resetarService.resetarAptos();
        resetarService.resetarTerritorios();

        return ResponseEntity.ok("Todos os territórios foram resetados com sucesso!");
    }
}
