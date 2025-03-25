package congr.baeta.BaetaBuilds.dto.cadastro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCondominioDTO(Long territorioID,
                                String nomeTorre,
                                @NotBlank
                                String endereco,
                                @NotBlank
                                @Pattern(regexp = "\\d{5}-\\d{3}")
                                String cep,
                                @NotNull
                                Integer totalAptosTorre,
                                @NotNull
                                Integer primAndIni,
                                @NotNull
                                Integer primAndFim,
                                Integer ultAndIni,
                                Integer ultAndFim) {

}
