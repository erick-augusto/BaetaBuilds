package congr.baeta.BaetaBuilds.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

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

    @Modifying
    @Query("""
        UPDATE Apartamento a
        SET a.dataEntrega = null,
            a.nomeResponsavel = null
    """)
    void resetarAptos();

    @Query("""
        SELECT count(a) FROM Apartamento a
    """)
    int countAptos();

    @Query("""
        SELECT count(a) FROM Apartamento a
        WHERE a.dataEntrega is not null
    """)
    int countAptosFeitos();

    @Query("""
        SELECT a FROM Apartamento a
        WHERE a.numApto = :numApto
        AND a.torre.torreID = :torreId
    """)
    Optional<Apartamento> findApto(Integer numApto, Long torreId);
}
