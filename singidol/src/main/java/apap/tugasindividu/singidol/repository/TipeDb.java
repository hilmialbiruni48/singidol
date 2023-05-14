package apap.tugasindividu.singidol.repository;

import apap.tugasindividu.singidol.model.TipeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipeDb extends JpaRepository<TipeModel, String> {
    Optional<TipeModel> findByIdTipe(Long id);

//    @Query("SELECT i FROM TipeModel i WHERE i.idTipe =:idTipe")
//    Optional<TipeModel> findByIdTipeUsingQuery(@Param("idTipe") Long idTipe);
}
