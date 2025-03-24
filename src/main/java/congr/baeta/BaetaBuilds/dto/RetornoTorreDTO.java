package congr.baeta.BaetaBuilds.dto;

import congr.baeta.BaetaBuilds.model.Torre;

public record RetornoTorreDTO (Long TorreID, String nomeTorre, String endereco, String cep, int totalAptos, Long territorioID, String msrErro) {
    
    public RetornoTorreDTO (Torre torre) {
        this(torre.getTorreID(), torre.getNomeTorre(), torre.getEndereco(), torre.getCep(), torre.getTotalAptosTorre(), torre.getTerritorio().getTerritorioID(), null);
    }

    public RetornoTorreDTO(String msgErro) {
        this(null, null, null, null, 0, null, msgErro);
    }
}
