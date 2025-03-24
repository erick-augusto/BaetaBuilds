package congr.baeta.BaetaBuilds.dto;

import congr.baeta.BaetaBuilds.model.Territorio;

public record CadastroTorreDTO(String nomeTorre, String endereco, String cep, int totalAptosTorre, Territorio territorio) {
    
    public CadastroTorreDTO (DadosTorreDTO dados, Territorio territorio) {
        this(dados.nomeTorre(), dados.endereco(), dados.cep(), dados.totalAptosTorre(), territorio);
    }

}
