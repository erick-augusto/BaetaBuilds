package congr.baeta.BaetaBuilds.dto.historico;

import jakarta.validation.constraints.NotNull;

public record EstatisticaDTO(@NotNull
    Integer totalAptosFeitos,
    @NotNull
    Integer totalAptosNaoFeitos,
    @NotNull
    Integer totalTerritoriosFeitos,
    @NotNull
    Integer totalTerritoriosNaoFeitos,
    @NotNull
    Integer totalDias,
    @NotNull
    Double mediaAptosPorDia) {

}
