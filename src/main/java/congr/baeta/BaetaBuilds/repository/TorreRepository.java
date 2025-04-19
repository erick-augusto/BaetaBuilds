package congr.baeta.BaetaBuilds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import congr.baeta.BaetaBuilds.model.Torre;

public interface TorreRepository extends JpaRepository<Torre, Long> {

    @Modifying
    @Query("""
        UPDATE Torre t
        SET t.totalAptosTorre = t.totalAptosTorre + :i
        WHERE t.torreID = :torreID
    """)
    void addAptoTorre(Long torreID, int i);

}
