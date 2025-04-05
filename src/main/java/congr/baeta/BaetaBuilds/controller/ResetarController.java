package congr.baeta.BaetaBuilds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import congr.baeta.BaetaBuilds.service.ApartamentoService;
import congr.baeta.BaetaBuilds.service.HistoricoService;
import congr.baeta.BaetaBuilds.service.TerritorioService;

@RestController
@RequestMapping("/resetar")
public class ResetarController {

    @Autowired
    ApartamentoService aptoService;
    @Autowired
    TerritorioService territorioService;
    @Autowired
    HistoricoService historicoService;

    @PutMapping
    public ResponseEntity<String> resetarDados() {
        //Gerar novo histórico
        historicoService.gerarHistorico();
        // Lógica para resetar histórico de aptos e territórios concluídos
        aptoService.resetarAptos();
        territorioService.resetarTerritorios();

        return ResponseEntity.ok("Todos os territórios foram resetados com sucesso!");
    }
}
