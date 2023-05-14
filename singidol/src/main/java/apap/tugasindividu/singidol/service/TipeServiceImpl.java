package apap.tugasindividu.singidol.service;

import apap.tugasindividu.singidol.model.TipeModel;
import apap.tugasindividu.singidol.repository.TipeDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TipeServiceImpl implements TipeService{
    @Autowired
    TipeDb tipeDb;

    @Override
    public void addTipe(TipeModel tipe) {
        tipeDb.save(tipe);
    }

    @Override
    public List<TipeModel> getListTipe() {
        return tipeDb.findAll();
    }

    @Override
    public TipeModel getTipeByIdTipe(Long idTipe) {
        Optional<TipeModel> tipe = tipeDb.findByIdTipe(idTipe);
        if (tipe.isPresent())
            return tipe.get();
        else
            return null;
    }
}
