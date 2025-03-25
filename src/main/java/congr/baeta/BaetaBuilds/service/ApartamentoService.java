package congr.baeta.BaetaBuilds.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import congr.baeta.BaetaBuilds.dto.cadastro.DadosCondominioDTO;
import congr.baeta.BaetaBuilds.dto.cadastro.RetornoCadAptoDTO;
import congr.baeta.BaetaBuilds.model.Apartamento;
import congr.baeta.BaetaBuilds.model.Torre;
import congr.baeta.BaetaBuilds.repository.ApartamentoRepository;

@Service
public class ApartamentoService {

    @Autowired
    ApartamentoRepository repository;

    public List<RetornoCadAptoDTO> cadastrarApto(DadosCondominioDTO dados, Torre torre){
        System.out.println("Novo apartamento cadastrado");

        var primAndIni = dados.primAndIni();
        var primAndFim = dados.primAndFim();
        var intervalo = primAndFim - primAndIni + 1;
        var ultAntIni = dados.ultAndIni();
        List<RetornoCadAptoDTO> aptos = new ArrayList<>();
        if(ultAntIni != null){
            var andares = (ultAntIni - primAndIni)/10 + 1;
            for(int i = 1; i <= andares; i++){
                for(int j = 1; j <= intervalo; j++){
                    var numApto = i*10 + j;
                    var novoApto = repository.save(new Apartamento(numApto, torre));
                    aptos.add(new RetornoCadAptoDTO(novoApto.getNumApto()));
                }
            }
        }

        return aptos;
    }
}
