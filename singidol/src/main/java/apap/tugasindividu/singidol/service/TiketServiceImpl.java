package apap.tugasindividu.singidol.service;

import apap.tugasindividu.singidol.model.KonserModel;
import apap.tugasindividu.singidol.model.TiketModel;
import apap.tugasindividu.singidol.model.TipeModel;
import apap.tugasindividu.singidol.repository.KonserDb;
import apap.tugasindividu.singidol.repository.TiketDb;
import apap.tugasindividu.singidol.repository.TipeDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class TiketServiceImpl implements TiketService {
    @Autowired
    TiketDb tiketDb;
    @Autowired
    TipeDb tipeDb;
    @Autowired
    KonserDb konserDb;
    @Override
    public void addTiket(TiketModel tiket) {
        tiketDb.save(tiket);
    }
    public void deleteTiket(TiketModel tiketModel) {
        tiketDb.delete(tiketModel);
    }

    @Override
    public String calcNoTiket(TiketModel tiket, KonserModel konser, TipeModel tipe) {
        konser = konserDb.findById(tiket.getKonser().getIdKonser()).get();
        String threeWordName = tiket.getNamaLengkap().split(" ")[0].substring(0,3).toUpperCase();
        LocalDateTime birth = tiket.getTanggalLahir();
        String dateBirth = birth.getDayOfMonth() + String.valueOf(birth.getMonthValue());

        LocalDateTime now = LocalDateTime.now();
        String dateBoughtTicket = now.getDayOfMonth() + String.valueOf(now.getMonthValue());

        int dateBirthAddDateBought = Integer.parseInt(dateBirth) + Integer.parseInt(dateBoughtTicket);
        String fourWordDate;

        if (dateBirthAddDateBought < 1000){
            fourWordDate = "0" + dateBirthAddDateBought;
        } else {
            fourWordDate = String.valueOf(dateBirthAddDateBought);
        }

        String twoWordConcert = konser.getNamaKonser().split(" ")[0].substring(0,1).toLowerCase();
        String order = String.valueOf(twoWordConcert.charAt(0) - 'a' + 1);

        if (order.length() == 1){
            order = "0" + order;
        }
        String threeWordType;
        if (tipe.getNama().equals("VIP")){
            threeWordType = "VIP";
        }
        else if (tipe.getNama().equals("PLATINUM")){
            threeWordType = "PLT";
        }
        else if (tipe.getNama().equals("GOLD")){
            threeWordType = "GLD";
        }
        else {
            threeWordType = "SLV";
        }

        Random random = new Random();
        String oneWordRandom = String.valueOf((char) (random.nextInt(26) + 'a')).toUpperCase();
        String finalNomorTiket = threeWordName + fourWordDate + order + threeWordType + oneWordRandom;

        return finalNomorTiket;
    }

    @Override
    public List<TiketModel> getListTiket() {
        return tiketDb.findAll();
    }

    @Override
    public TiketModel getTiketByIdTiket(Long idTiket) {
        Optional<TiketModel> tiket = tiketDb.findByIdTiket(idTiket);
        if (tiket.isPresent()) {
            return tiket.get();
        } else {
            return null;
        }
    }
}
