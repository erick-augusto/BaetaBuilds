package congr.baeta.BaetaBuilds.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import congr.baeta.BaetaBuilds.dto.cadastro.DadosCondominioDTO;
import congr.baeta.BaetaBuilds.dto.cadastro.RetornoCadAptoDTO;
import congr.baeta.BaetaBuilds.model.Apartamento;
import congr.baeta.BaetaBuilds.model.Territorio;
import congr.baeta.BaetaBuilds.model.Torre;
import congr.baeta.BaetaBuilds.repository.ApartamentoRepository;
import congr.baeta.BaetaBuilds.repository.TerritorioRepository;
import congr.baeta.BaetaBuilds.repository.TorreRepository;
import jakarta.transaction.Transactional;

@Service
public class CadastroService {

    @Autowired
    TerritorioRepository territorioRepository;

    @Autowired
    TorreRepository torreRepository;

    @Autowired
    ApartamentoRepository apartamentoRepository;

    public Territorio cadTerrirorio(int totalAptos){
        return territorioRepository.save(new Territorio(totalAptos));
    }

    public Torre cadastroTorre(DadosCondominioDTO dados, Territorio territorio){
        var nomeTorre = dados.nomeTorre();
        var totalAptos = dados.totalAptosTorre();
        var endereco = dados.endereco();
        var cep = dados.cep();

        return torreRepository.save(new Torre(nomeTorre,endereco,cep,totalAptos, territorio));
    }

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

    public Optional<Territorio> findTerritorioById(Long territorioID) {
        return territorioRepository.findById(territorioID);
    }

    public Optional<Torre> findTorreById(Long idTorre) {
        return torreRepository.findById(idTorre);
    }

    public Optional<Apartamento> findApto(Integer numApto, Torre torre) {
        return apartamentoRepository.findApto(numApto, torre.getTorreID());
    }

    public void salvarApto(Apartamento apto) {
        apartamentoRepository.save(apto);
    }

    @Transactional
    public void addAptoTorre(Torre torre, int i) {
        torreRepository.addAptoTorre(torre.getTorreID(), i);
    }

    @Transactional
    public void addAptoTerritorio(Territorio territorio, int i) {
        territorioRepository.addAptoTerritorio(territorio.getTerritorioID(), i);
    }
}
