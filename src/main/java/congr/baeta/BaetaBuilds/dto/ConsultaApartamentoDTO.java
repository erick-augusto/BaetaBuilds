package congr.baeta.BaetaBuilds.dto;

import java.time.LocalDate;

import congr.baeta.BaetaBuilds.model.Apartamento;

public record ConsultaApartamentoDTO(int numApto, LocalDate dataEntrega, String nomeResponsavel, Long torreID) {

    public ConsultaApartamentoDTO(Apartamento apartamento) {
        this(apartamento.getNumApto(), apartamento.getDataEntrega(), apartamento.getNomeResponsavel(), apartamento.getTorre().getTorreID());
    }

}
