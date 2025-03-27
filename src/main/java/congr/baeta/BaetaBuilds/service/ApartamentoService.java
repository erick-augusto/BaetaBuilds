package congr.baeta.BaetaBuilds.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import congr.baeta.BaetaBuilds.dto.cadastro.DadosCondominioDTO;
import congr.baeta.BaetaBuilds.dto.cadastro.RetornoCadAptoDTO;
import congr.baeta.BaetaBuilds.dto.responsavel.RetornoAptosAleatoriosDTO;
import congr.baeta.BaetaBuilds.model.Apartamento;
import congr.baeta.BaetaBuilds.model.Territorio;
import congr.baeta.BaetaBuilds.model.Torre;
import congr.baeta.BaetaBuilds.repository.ApartamentoRepository;
import congr.baeta.BaetaBuilds.repository.TerritorioRepository;
import congr.baeta.BaetaBuilds.repository.TorreRepository;

@Service
public class ApartamentoService {

    @Autowired
    ApartamentoRepository apartamentoRepository;

    @Autowired
    TorreRepository torreRepository;

    @Autowired
    TerritorioRepository territorioRepository;

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
                    var novoApto = apartamentoRepository.save(new Apartamento(numApto, torre));
                    aptos.add(new RetornoCadAptoDTO(novoApto.getNumApto()));
                }
            }
        } else {
            for(int i = 1; i <= intervalo; i++){
                var novoApto = apartamentoRepository.save(new Apartamento(i, torre));
                aptos.add(new RetornoCadAptoDTO(novoApto.getNumApto()));
            }
        }

        return aptos;
    }

    public List<RetornoAptosAleatoriosDTO> buscarAptosAleatorios(int totalSolicitado, String responsavel){
        var aptos = apartamentoRepository.buscarAptosDisponiveis(totalSolicitado);
        List<RetornoAptosAleatoriosDTO> lista = new ArrayList<>();
        
        //Iterando aparmetos para pegar informações da torre e montar a lista de retorno
        for (int i = 0; i < aptos.size(); i++) {
            var torre = torreRepository.findById(aptos.get(i).getTorre().getTorreID());

            if(torre.isPresent()){
                var endereco = torre.get().getEndereco();
                var cep = torre.get().getCep();
                var nomeTorre = torre.get().getNomeTorre();
                var numApto = aptos.get(i).getNumApto();
                if(nomeTorre != null){
                    endereco = endereco + " - " + nomeTorre;
                }
                lista.add(new RetornoAptosAleatoriosDTO(endereco, cep, numApto));

                //Verifica se o território já foi inicializado
                var territorio = torre.get().getTerritorio();
                if(territorio.getDataInicio() == null){
                    inicializarTerritorio(territorio);
                }
            }
        }
        usarAptos(aptos, responsavel);
        return lista;
    }

    public void usarAptos(List<Apartamento> lista, String nome){
        for (Apartamento apto : lista) {
            apto.setDataEntrega(LocalDate.now());
            apto.setNomeResponsavel(nome);
            apartamentoRepository.save(apto);
        }
    }

    public void inicializarTerritorio(Territorio territorio){
        territorio.setDataInicio(LocalDate.now());
        territorioRepository.save(territorio);
    }
}
