package apap.tugasindividu.singidol.repository;

import apap.tugasindividu.singidol.model.PenampilanKonserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PenampilanKonserDb extends JpaRepository<PenampilanKonserModel, Long>{
    Optional<PenampilanKonserModel> findByIdPenampilanKonser(Long idPenampilanKonser);

//    @Query("SELECT c FROM PenampilanKonserModel c WHERE c.idPenampilanKonser = :idPenampilanKonser")
//    Optional<PenampilanKonserModel> findByIdPenampilanKonserUsingQuery(@Param("idPenampilanKonser") Long idPenampilanKonser);
}
