package congr.baeta.BaetaBuilds.dto;

import java.time.LocalDate;

import congr.baeta.BaetaBuilds.model.Apartamento;

public record RetornoApartamentoDTO(Long aptoID, int numApto, LocalDate dataEntrega, String nomeResponsavel, Long torreID, String msgErro) {
    
    public RetornoApartamentoDTO (Apartamento apartamento) {
        this(apartamento.getAptoID(), apartamento.getNumApto(), apartamento.getDataEntrega(), apartamento.getNomeResponsavel(), apartamento.getTorre().getTorreID(), null);
    }

    public RetornoApartamentoDTO(String msgErro) {
        this(null, 0, null, null, null, msgErro);
    }

}
