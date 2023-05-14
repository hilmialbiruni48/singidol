package apap.tugasindividu.singidol.service;

import apap.tugasindividu.singidol.model.KonserModel;
import apap.tugasindividu.singidol.model.TipeModel;

import java.time.LocalDateTime;
import java.util.List;

public interface KonserService {
    List<KonserModel> getListKonser();
    List<KonserModel> filterSearchKonser(Long minPendapatan, Long idIdol);
    KonserModel getKonserByIdKonser(Long id);
    KonserModel updateKonser(KonserModel konser);
    void addKonser(KonserModel konser);
    void addTotalPendapatan(KonserModel konser, TipeModel tipe);
    void minusTotalPendapatan(KonserModel konser, TipeModel tipe);
}
