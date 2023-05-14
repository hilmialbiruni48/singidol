package apap.tugasindividu.singidol.service;

import apap.tugasindividu.singidol.model.TipeModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface TipeService {
    List<TipeModel> getListTipe();
    TipeModel getTipeByIdTipe(Long idTipe);
    void addTipe(TipeModel tipe);
}
