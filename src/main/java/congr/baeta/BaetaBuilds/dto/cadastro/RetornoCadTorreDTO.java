package congr.baeta.BaetaBuilds.dto.cadastro;

import java.util.List;

import congr.baeta.BaetaBuilds.model.Torre;

public record RetornoCadTorreDTO(String nomeTorre,
                                String endereco,
                                String cep,
                                int totalAptos,
                                List<RetornoCadAptoDTO> apartamentos) {

    public RetornoCadTorreDTO(Torre torre, List<RetornoCadAptoDTO> aptos) {
        this(torre.getNomeTorre(), torre.getEndereco(), torre.getCep(), torre.getTotalAptosTorre(), aptos);
    }

    public RetornoCadTorreDTO() {
        this(null, null, null, 0, null);
    }

}
