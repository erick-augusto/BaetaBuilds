package congr.baeta.BaetaBuilds.dto.cadastro;

public record DadosCondominioDTO(Long territorioID,
                                String nomeTorre,
                                String endereco,
                                String cep,
                                Integer totalAptosTorre,
                                Integer primAndIni,
                                Integer primAndFim,
                                Integer ultAndIni,
                                Integer ultAndFim) {

}
