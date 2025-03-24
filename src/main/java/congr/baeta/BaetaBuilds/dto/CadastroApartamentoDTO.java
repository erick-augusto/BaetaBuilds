package congr.baeta.BaetaBuilds.dto;

import java.time.LocalDate;

import congr.baeta.BaetaBuilds.model.Torre;

public record CadastroApartamentoDTO(int numApto, LocalDate dataEntrega, String nomeResponsavel, Torre torre) {

    public CadastroApartamentoDTO(DadosApartamentoDTO dados, Torre torre) {
        this(dados.numApto(), dados.dataEntrega(), dados.nomeResponsavel(), torre);
    }

}
