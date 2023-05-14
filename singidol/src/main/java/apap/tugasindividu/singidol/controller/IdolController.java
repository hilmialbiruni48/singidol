package apap.tugasindividu.singidol.controller;

import apap.tugasindividu.singidol.model.IdolModel;
import apap.tugasindividu.singidol.model.PenampilanKonserModel;
import apap.tugasindividu.singidol.service.IdolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IdolController  {
    @Qualifier("idolServiceImpl")
    @Autowired
    private IdolService idolService;

    @GetMapping("/idol")
    public String listIdol(Model model) {
        List<IdolModel> listIdol = idolService.getListIdol();
        model.addAttribute("listIdol", listIdol);
        return "viewall-idol";
    }

    @GetMapping("/idol/tambah")
    public String addIdolFormPage(Model model) {
        IdolModel idol = new IdolModel();
        List<PenampilanKonserModel> listPenampilanNew = new ArrayList<>();
        idol.setListPenampilan(listPenampilanNew);
        idol.getListPenampilan().add(new PenampilanKonserModel());
        model.addAttribute("idol", idol);
        return "form-add-idol";
    }

    @PostMapping("/idol/tambah")
    public String addIdolSubmitPage(@ModelAttribute IdolModel idol, Model model) {
        idolService.addIdol(idol);
        model.addAttribute("namaIdol", idol.getNamaIdol());
        return "success-add-idol";
    }

    @GetMapping("/idol/{idIdol}")
    public String viewDetailIdolPage(@PathVariable(value = "idIdol") String idIdol, Model model) {
        IdolModel idol = idolService.getIdolByIdIdol(Long.parseLong(idIdol));
        model.addAttribute("idol", idol);
        return "view-idol";
    }
}
