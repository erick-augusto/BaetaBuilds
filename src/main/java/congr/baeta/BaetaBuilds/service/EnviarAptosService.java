package congr.baeta.BaetaBuilds.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import congr.baeta.BaetaBuilds.dto.responsavel.RetornoAptosAleatoriosDTO;
import congr.baeta.BaetaBuilds.model.Apartamento;
import congr.baeta.BaetaBuilds.model.Territorio;
import congr.baeta.BaetaBuilds.repository.ApartamentoRepository;
import congr.baeta.BaetaBuilds.repository.TerritorioRepository;
import congr.baeta.BaetaBuilds.repository.TorreRepository;

@Service
public class EnviarAptosService {

    @Autowired
    ApartamentoRepository apartamentoRepository;

    @Autowired
    TorreRepository torreRepository;

    @Autowired
    TerritorioRepository territorioRepository;

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

                //Se não houver mais apartamentos disponíveis, finaliza o território
                var aptosFaltando = apartamentoRepository.verificaAptosFaltando(territorio.getTerritorioID());
                if(aptosFaltando == 0){
                    finalizarTerritorio(territorio);
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

    public void finalizarTerritorio(Territorio territorio){
        territorio.setDataFim(LocalDate.now());
        territorioRepository.save(territorio);
    }

}
