package congr.baeta.BaetaBuilds.dto.responsavel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponsavelDTO(@NotBlank
                            String nome,
                            @NotNull
                            int totalAptos) {

}
