package congr.baeta.BaetaBuilds.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import congr.baeta.BaetaBuilds.model.Territorio;

public interface TerritorioRepository extends JpaRepository<Territorio, Long>{

    @Modifying
    @Query("""
        UPDATE Territorio t
        SET t.dataInicio = null
            , t.dataFim = null
    """)
    void resetarTerritorios();

    @Query("""
        SELECT MIN(t.dataInicio) FROM Territorio t
        WHERE t.dataInicio IS NOT NULL
    """)
    LocalDate minDatInit();

    @Modifying
    @Query("""
        UPDATE Territorio t
        SET t.totalAptos = t.totalAptos + :i
        WHERE t.territorioID = :territorioID
    """)
    void addAptoTerritorio(Long territorioID, int i);
}
