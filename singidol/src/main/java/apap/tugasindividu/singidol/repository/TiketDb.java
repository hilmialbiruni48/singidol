package apap.tugasindividu.singidol.repository;

import apap.tugasindividu.singidol.model.TiketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TiketDb extends JpaRepository<TiketModel, String>{
    Optional<TiketModel> findByIdTiket(Long id);

//    @Query("SELECT i FROM TiketModel i WHERE i.idTiket =:idTiket")
//    Optional<TiketModel> findByIdTiketUsingQuery(@Param("idTiket") Long idTiket);
}
