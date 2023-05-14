package apap.tugasindividu.singidol.repository;

import apap.tugasindividu.singidol.model.KonserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface KonserDb extends JpaRepository<KonserModel, Long>{
    Optional<KonserModel> findByIdKonser(Long id);

//    @Query("SELECT c FROM KonserModel c WHERE c.idKonser = : idKonser")
//    Optional<KonserModel> findByIdUsingQuery(@Param("idKonser") Long idKonser);
}
