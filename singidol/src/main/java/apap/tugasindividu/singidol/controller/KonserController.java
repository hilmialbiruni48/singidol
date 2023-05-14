package apap.tugasindividu.singidol.controller;

import apap.tugasindividu.singidol.model.IdolModel;;
import apap.tugasindividu.singidol.model.KonserModel;
import apap.tugasindividu.singidol.model.PenampilanKonserModel;
import apap.tugasindividu.singidol.service.KonserService;
import apap.tugasindividu.singidol.service.IdolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class KonserController {
    @Qualifier("konserServiceImpl")
    @Autowired
    private KonserService konserService;
    @Qualifier("idolServiceImpl")
    @Autowired
    private IdolService idolService;

    @GetMapping("/konser")
    public String listKonser(Model model) {
        List<KonserModel> listKonser = konserService.getListKonser();
        model.addAttribute("listKonser", listKonser);
        return "viewall-konser";
    }

    @GetMapping("/konser/tambah")
    public String addKonserFormPage(Model model) {
        KonserModel konser = new KonserModel();
        List<PenampilanKonserModel> listPenampilanNew = new ArrayList<>();

        konser.setListPenampilan(listPenampilanNew);
        konser.getListPenampilan().add(new PenampilanKonserModel());

        List<IdolModel> listIdolExisting = idolService.getListIdol();
        model.addAttribute("listIdolExisting",listIdolExisting);
        model.addAttribute("konser", konser);
        return "form-add-konser";
    }

    @PostMapping(value = "/konser/tambah", params = { "save" })
    public String addKonserSubmit(@ModelAttribute KonserModel konser, Model model) {
        if (konser.getListPenampilan() == null || konser.getListPenampilan().size() == 0) {
            konser.setListPenampilan(new ArrayList<>());
        }
        konser.setTotalPendapatan(0L);
        for (PenampilanKonserModel tampil: konser.getListPenampilan()){
            tampil.setKonser(konser);
        }
        konserService.addKonser(konser);
        model.addAttribute("namaKonser", konser.getNamaKonser());
        return "success-add-konser";
    }

    @PostMapping(value = "konser/tambah", params = ("addRow"))
    private String addRowIdolMultiple(@ModelAttribute KonserModel konser, Model model) {
        if (konser.getListPenampilan() == null || konser.getListPenampilan().size() == 0){
            konser.setListPenampilan(new ArrayList<>());
        }
        konser.getListPenampilan().add(new PenampilanKonserModel());

        List<IdolModel> listIdol = idolService.getListIdol();
        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);
        return "form-add-konser";
    }

    @GetMapping("/konser/{id}")
    public String viewDetailKonserPage(@PathVariable(value = "id") String id, Model model) {
        KonserModel konser = konserService.getKonserByIdKonser(Long.parseLong(id));
        List<PenampilanKonserModel> listPenampilan = konser.getListPenampilan();
        model.addAttribute("listPenampilan", listPenampilan);
        model.addAttribute("konser", konser);
        return "view-konser";
    }

    @GetMapping("konser/ubah/{id}")
    public String updateKonserFormPage(@PathVariable(value = "id") String id, Model model) {
        KonserModel konser = konserService.getKonserByIdKonser(Long.parseLong(id));
        List<IdolModel> listIdol = idolService.getListIdol();
        model.addAttribute("listIdolExisting", listIdol);
        model.addAttribute("konser", konser);
        return "form-update-konser";
    }

    @PostMapping("konser/ubah")
    public String updateKonserSubmitPage(@ModelAttribute KonserModel konser, Model model) {
        if (konser.getListPenampilan() == null) {
            konser.setListPenampilan(new ArrayList<>());
        }
        KonserModel updatedKonser = konserService.updateKonser(konser);
        model.addAttribute("code", updatedKonser.getIdKonser());
        return "success-update-konser";
    }

    @PostMapping(value = "konser/ubah", params = ("addRow"))
    private String addRowIdolMultipleUpdate(@ModelAttribute KonserModel konser, Model model) {
        if (konser.getListPenampilan() == null || konser.getListPenampilan().size() == 0){
            konser.setListPenampilan(new ArrayList<>());
        }
        konser.getListPenampilan().add(new PenampilanKonserModel());

        List<IdolModel> listIdol = idolService.getListIdol();
        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);
        return "form-update-konser";
    }

    @PostMapping(value = "konser/ubah", params = ("deleteRow"))
    private String deleteRowIdolMultipleUpdate(@ModelAttribute KonserModel konser, @RequestParam("deleteRow") Integer row, Model model) {
        final Integer rowId = Integer.valueOf(row);
        konser.getListPenampilan().remove(rowId.intValue());

        List<IdolModel> listIdol = idolService.getListIdol();
        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);
        return "form-update-konser";
    }

    @PostMapping(value = "konser/tambah", params = ("deleteRow"))
    private String deleteRowIdolMultiple(@ModelAttribute KonserModel konser, @RequestParam("deleteRow") Integer row, Model model) {
        final Integer rowId = Integer.valueOf(row);
        konser.getListPenampilan().remove(rowId.intValue());

        List<IdolModel> listIdol = idolService.getListIdol();
        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);
        return "form-add-konser";
    }

    @GetMapping(value="konser/cari")
    public String findKonserFormPage(Model model){
        List<IdolModel> listIdolExisting = idolService.getListIdol();
        model.addAttribute("listIdolExisting", listIdolExisting);
        model.addAttribute("listKonser", new ArrayList<>());

        return "form-search-konser";
    }

    @GetMapping(value="konser/cari", params = "search")
    public String findKonserSubmitPage(@RequestParam(value="totalPendapatan") Long totalPendapatan, @RequestParam(value="idIdol") Long idIdol, Model model){
        List<IdolModel> listIdolExisting = idolService.getListIdol();
        List<KonserModel> listKonser = konserService.filterSearchKonser(totalPendapatan, idIdol);
        model.addAttribute("listIdolExisting", listIdolExisting);
        model.addAttribute("listKonser", listKonser);

        return "form-search-konser";
    }
}
