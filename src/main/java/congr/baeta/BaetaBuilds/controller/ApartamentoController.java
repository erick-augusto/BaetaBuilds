package congr.baeta.BaetaBuilds.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import congr.baeta.BaetaBuilds.dto.CadastroApartamentoDTO;
import congr.baeta.BaetaBuilds.dto.ConsultaApartamentoDTO;
import congr.baeta.BaetaBuilds.dto.DadosApartamentoDTO;
import congr.baeta.BaetaBuilds.dto.RetornoApartamentoDTO;
import congr.baeta.BaetaBuilds.dto.responsavel.ResponsavelDTO;
import congr.baeta.BaetaBuilds.model.Apartamento;
import congr.baeta.BaetaBuilds.repository.ApartamentoRepository;
import congr.baeta.BaetaBuilds.repository.TorreRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/apartamento")
public class ApartamentoController {

    @Autowired
    ApartamentoRepository repository;

    @Autowired
    TorreRepository torreRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<RetornoApartamentoDTO> cadastrar(@RequestBody DadosApartamentoDTO dados) {
        System.out.println("Novo apartamento cadastrado");

        var torre = torreRepository.findById(dados.torreID());
        if(torre.isPresent()){
            var cadastro = new CadastroApartamentoDTO(dados, torre.get());

            var apartamento = repository.save(new Apartamento(cadastro));
    
            return ResponseEntity.ok(new RetornoApartamentoDTO(apartamento));
        } else{
            return ResponseEntity.badRequest().body(new RetornoApartamentoDTO("Torre não encontrada"));
        }
    }

    @GetMapping
    public ResponseEntity<List<ConsultaApartamentoDTO>> aptosAleatorios(@RequestBody ResponsavelDTO responsavel){
        System.out.println("Buscando apartamentos aleatórios");

        var totalSolicitado = responsavel.totalAptos();
        if(totalSolicitado > 0){
            var aptos = repository.buscarAptosDisponiveis(totalSolicitado);
            var lista = aptos.stream().map(a -> new ConsultaApartamentoDTO(a)).toList();
            usarAptos(aptos, responsavel.nome());
            return ResponseEntity.ok(lista);
        } else{
            return ResponseEntity.badRequest().body(List.of());
        }
        
    }

    public void usarAptos(List<Apartamento> lista, String nome){
        for (Apartamento apto : lista) {
            apto.setDataEntrega(LocalDate.now());
            apto.setNomeResponsavel(nome);
            repository.save(apto);
        }
    }
}
