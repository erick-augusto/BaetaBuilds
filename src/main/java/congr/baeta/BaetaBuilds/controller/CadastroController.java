package congr.baeta.BaetaBuilds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import congr.baeta.BaetaBuilds.dto.cadastro.DadosCondominioDTO;
import congr.baeta.BaetaBuilds.dto.cadastro.RetornoCadAptoDTO;
import congr.baeta.BaetaBuilds.dto.cadastro.RetornoCadTorreDTO;
import congr.baeta.BaetaBuilds.model.Apartamento;
import congr.baeta.BaetaBuilds.service.CadastroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    CadastroService cadastroService;

    @PostMapping("/novo")
    @Transactional
    public ResponseEntity<RetornoCadTorreDTO> cadastrar(@RequestBody @Valid DadosCondominioDTO dados) {
        //Cadastra o território
        var totalAptos = dados.totalAptosTorre();
        var territorio = cadastroService.cadTerrirorio(totalAptos);
        
        //Cadastra as torres
        var torre = cadastroService.cadastroTorre(dados, territorio);

        //Cadastra os apartamentos
        List<RetornoCadAptoDTO> aptos = cadastroService.cadastrarApto(dados, torre);

        return ResponseEntity.ok(new RetornoCadTorreDTO(torre, aptos));
    }

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<RetornoCadTorreDTO> addTorre(@RequestBody @Valid DadosCondominioDTO dados) {
        //Autaliza o território
        var territorioID = dados.territorioID();
        var territorio = cadastroService.findTerritorioById(territorioID);
        if(territorio.isPresent()){
            var totalAptos = dados.totalAptosTorre();
            var totalAptosTerritorio = territorio.get().getTotalAptos();
            territorio.get().setTotalAptos(totalAptosTerritorio+totalAptos);

            //Cadastra as torres
            var torre = cadastroService.cadastroTorre(dados, territorio.get());
    
            //Cadastra os apartamentos
            List<RetornoCadAptoDTO> aptos = cadastroService.cadastrarApto(dados, torre);
            
            return ResponseEntity.ok(new RetornoCadTorreDTO(torre, aptos));
        } else {
            return ResponseEntity.badRequest().body(new RetornoCadTorreDTO());
        }
    }

    @PostMapping("/individual/{idTorre}/{numApto}")
    @Transactional
    public String addApto(@PathVariable Long idTorre, @PathVariable Integer numApto) {
        var torre = cadastroService.findTorreById(idTorre);
        if(torre.isPresent()){
            //Verifica se o apartamento já existe
            var aptoExistente = cadastroService.findApto(numApto, torre.get());
            if(aptoExistente.isPresent()){
                return "Apartamento já cadastrado";
            } else{
                var apto = new Apartamento(numApto, torre.get());
                cadastroService.salvarApto(apto);
            }
            cadastroService.addAptoTorre(torre.get(), 1);
            cadastroService.addAptoTerritorio(torre.get().getTerritorio(), 1);
            return "Apartamento cadastrado com sucesso";
        } else {
            return "Torre não encontrada";
        }
    }
}
