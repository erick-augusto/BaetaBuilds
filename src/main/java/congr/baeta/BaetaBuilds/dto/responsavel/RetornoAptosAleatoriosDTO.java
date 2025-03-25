package congr.baeta.BaetaBuilds.dto.responsavel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RetornoAptosAleatoriosDTO(@NotBlank
                                        String enderecoTorre,
                                        @NotBlank
                                        @Pattern(regexp = "\\d{5}-\\d{3}")
                                        String cep,
                                        @NotNull
                                        int numApto) {

}
