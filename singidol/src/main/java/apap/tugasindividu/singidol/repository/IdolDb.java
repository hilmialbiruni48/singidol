package apap.tugasindividu.singidol.repository;

import apap.tugasindividu.singidol.model.IdolModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IdolDb extends JpaRepository<IdolModel, Long>  {
//    Optional<IdolModel> findByNamaIdol(String namaIdol);
    Optional<IdolModel> findByIdIdol(Long id);
}