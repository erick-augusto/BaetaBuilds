package congr.baeta.BaetaBuilds.dto.historico;

import java.time.LocalDate;

import congr.baeta.BaetaBuilds.model.Historico;
import jakarta.validation.constraints.NotNull;

public record HistoricoDTO(@NotNull
                          int numAptosFeitos,
                          @NotNull
                          int totAptos,
                          @NotNull
                          LocalDate dataIni,
                          @NotNull
                          LocalDate dataFim,
                          @NotNull
                          int totDias) {

    public HistoricoDTO(Historico historico) {
        this(historico.getTotAptos(), historico.getNumAptosFeitos(), historico.getDataIni(), historico.getDataFim(), historico.getTotDias());
    }

}
