package congr.baeta.BaetaBuilds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import congr.baeta.BaetaBuilds.model.Territorio;

public interface TerritorioRepository extends JpaRepository<Territorio, Long>{

    @Query("""
        UPDATE Territorio t
        SET t.dataInicio = null
            , t.dataFim = null
    """)
    void resetarTerritorios();

}
