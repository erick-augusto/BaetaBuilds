package congr.baeta.BaetaBuilds.dto.cadastro;

import java.util.List;

import congr.baeta.BaetaBuilds.model.Torre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RetornoCadTorreDTO(@NotBlank
                                String nomeTorre,
                                @NotBlank
                                String endereco,
                                @NotBlank
                                @Pattern(regexp = "\\d{5}-\\d{3}")
                                String cep,
                                @NotNull
                                int totalAptos,
                                List<RetornoCadAptoDTO> apartamentos) {

    public RetornoCadTorreDTO(Torre torre, List<RetornoCadAptoDTO> aptos) {
        this(torre.getNomeTorre(), torre.getEndereco(), torre.getCep(), torre.getTotalAptosTorre(), aptos);
    }

    public RetornoCadTorreDTO() {
        this(null, null, null, 0, null);
    }

}
