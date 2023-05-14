package apap.tugasindividu.singidol.controller;

import apap.tugasindividu.singidol.model.KonserModel;
import apap.tugasindividu.singidol.model.TipeModel;
import apap.tugasindividu.singidol.service.KonserService;
import apap.tugasindividu.singidol.model.TiketModel;
import apap.tugasindividu.singidol.service.TiketService;
import apap.tugasindividu.singidol.service.TipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TiketController {
    @Qualifier("tiketServiceImpl")
    @Autowired
    private TiketService tiketService;
    @Qualifier("konserServiceImpl")
    @Autowired
    private KonserService konserService;
    @Qualifier("tipeServiceImpl")
    @Autowired
    private TipeService tipeService;

    @GetMapping("/tiket")
    public String listTiket(Model model) {
        List<TiketModel> listTiket = tiketService.getListTiket();
        model.addAttribute("listTiket", listTiket);
        return "viewall-tiket";
    }

    @GetMapping("/tiket/{id}")
    public String detailTiket(@PathVariable String id, Model model){
        TiketModel tiket = tiketService.getTiketByIdTiket(Long.parseLong(id));
        model.addAttribute("tiket",tiket);
        return "view-tiket";
    }

    @GetMapping("/tiket/pesan")
    public String addTiketFormPage(Model model) {
        TiketModel tiket = new TiketModel();
        List<KonserModel> listKonser = konserService.getListKonser();
        List<TipeModel> listTipe = tipeService.getListTipe();
        model.addAttribute("listTipe", listTipe);
        model.addAttribute("listKonser", listKonser);
        model.addAttribute("tiket", tiket);
        return "form-add-tiket";
    }

    @PostMapping(value = "/tiket/pesan", params = { "save" })
    public String addTiketSubmitPage(@ModelAttribute TiketModel tiket, Model model) {
        KonserModel konser = konserService.getKonserByIdKonser(tiket.getKonser().getIdKonser());
        TipeModel tipe = tipeService.getTipeByIdTipe(tiket.getTipe().getIdTipe());
        String nomorTiket = tiketService.calcNoTiket(tiket, konser, tipe);

        tiket.setNomorTiket(nomorTiket);
        tiket.setTanggalPembelian(LocalDateTime.now());
        konserService.addTotalPendapatan(konser, tipe);
        tiketService.addTiket(tiket);
        model.addAttribute("nomor", tiket.getNomorTiket());
        model.addAttribute("namaKonser", konser.getNamaKonser());
        return "success-add-tiket";
    }

    @GetMapping("/tiket/hapus/{id}")
    public String deleteTiket(@PathVariable Long id ,Model model){
        TiketModel tiket = tiketService.getTiketByIdTiket(id);
        KonserModel konser = konserService.getKonserByIdKonser(tiket.getKonser().getIdKonser());
        TipeModel tipe = tipeService.getTipeByIdTipe(tiket.getTipe().getIdTipe());
        konserService.minusTotalPendapatan(konser, tipe);
        tiketService.deleteTiket(tiket);
        model.addAttribute("nomor", tiket.getNomorTiket());
        model.addAttribute("namaKonser", tiket.getKonser().getNamaKonser());
        return "success-delete-tiket";
    }

}
