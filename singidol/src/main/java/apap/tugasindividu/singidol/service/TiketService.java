package apap.tugasindividu.singidol.service;
import apap.tugasindividu.singidol.model.KonserModel;
import apap.tugasindividu.singidol.model.TiketModel;
import apap.tugasindividu.singidol.model.TipeModel;

import java.util.List;
public interface TiketService {

    List<TiketModel> getListTiket();
    String calcNoTiket(TiketModel tiket, KonserModel konser, TipeModel tipe);
    TiketModel getTiketByIdTiket(Long idTiket);
    void addTiket(TiketModel tiket);
    void deleteTiket(TiketModel tiketModel);

}
