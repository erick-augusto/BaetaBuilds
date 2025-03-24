package congr.baeta.BaetaBuilds.dto;

import java.time.LocalDate;

import congr.baeta.BaetaBuilds.model.Territorio;

public record ConsultaTerritorioDTO(Long territorioID, LocalDate dataInicio, LocalDate dataFim, int totalAptos) {

    public ConsultaTerritorioDTO(Territorio territorio) {
        this(territorio.getTerritorioID(), territorio.getDataInicio(), territorio.getDataFim(), territorio.getTotalAptos());
    }

}
