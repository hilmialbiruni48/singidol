package apap.tugasindividu.singidol.service;

import apap.tugasindividu.singidol.model.KonserModel;
import apap.tugasindividu.singidol.model.PenampilanKonserModel;
import apap.tugasindividu.singidol.model.TipeModel;
import apap.tugasindividu.singidol.repository.KonserDb;
import apap.tugasindividu.singidol.repository.PenampilanKonserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KonserServiceImpl implements KonserService{
    @Autowired
    KonserDb konserDb;

    @Autowired
    PenampilanKonserDb penampilanKonserDb;
    @Override
    public List<KonserModel> getListKonser() {
        return konserDb.findAll();
    }
    @Override
    public void addKonser(KonserModel konser) {
        konserDb.save(konser);
    }
    @Override
    public KonserModel getKonserByIdKonser(Long idKonser) {
        Optional<KonserModel> konser = konserDb.findByIdKonser(idKonser);
        if (konser.isPresent()) {
            return konser.get();
        } else {
            return null;
        }
    }
    @Override
    public KonserModel updateKonser(KonserModel konser) {
        konserDb.save(konser);
        return konser;
    }
    @Override
    public List<KonserModel> filterSearchKonser(Long minPendapatan, Long idIdol){
        List<KonserModel> konserExisting = konserDb.findAll();
        List<KonserModel> konserFilter = new ArrayList<>();
        for (KonserModel konser : konserExisting){
            if (konser.getTotalPendapatan() > minPendapatan){
                for (PenampilanKonserModel tampil : konser.getListPenampilan()){
                    if (tampil.getIdol().getIdIdol().equals(idIdol)){
                        konserFilter.add(konser);
                    }
                }
            }
        }
        return konserFilter;
    }
    @Override
    public void addTotalPendapatan(KonserModel konser, TipeModel tipe) {
        Long sum = konser.getTotalPendapatan() + tipe.getHarga();
        konser.setTotalPendapatan(sum);
    }

    @Override
    public void minusTotalPendapatan(KonserModel konser, TipeModel tipe) {
        Long sum = konser.getTotalPendapatan() - tipe.getHarga();
        konser.setTotalPendapatan(sum);
    }
}
