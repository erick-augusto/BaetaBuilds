package congr.baeta.BaetaBuilds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import congr.baeta.BaetaBuilds.model.Apartamento;

public interface ApartamentoRepository extends JpaRepository<Apartamento, Long> {

    @Query("""
        SELECT a FROM Apartamento a
        WHERE a.dataEntrega is null
        ORDER BY RAND()
        LIMIT :total
    """)
    List<Apartamento> buscarAptosDisponiveis(int total);

    @Query("""
        SELECT count(a) FROM Apartamento a
        WHERE a.dataEntrega is null
        AND a.torre.territorio.territorioID = :territorioId
    """)
    int verificaAptosFaltando(Long territorioId);

    @Query("""
        UPDATE Apartamento a
        SET a.dataEntrega = null
            , a.responsavel = null
    """)
    void resetarAptos();
}
