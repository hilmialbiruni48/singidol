package apap.tugasindividu.singidol.service;

import apap.tugasindividu.singidol.model.IdolModel;

import java.util.List;
public interface IdolService {
    List<IdolModel> getListIdol();
    IdolModel getIdolByIdIdol(Long idIdol);
    void addIdol(IdolModel idol);
}
