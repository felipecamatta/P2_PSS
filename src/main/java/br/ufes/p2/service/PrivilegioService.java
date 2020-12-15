package br.ufes.p2.service;

import br.ufes.p2.dao.interfaces.IPrivilegioDAO;
import br.ufes.p2.model.Privilegio;

public class PrivilegioService {

    private IPrivilegioDAO privilegioDAO;

    public PrivilegioService(IPrivilegioDAO privilegioDAO) {
        this.privilegioDAO = privilegioDAO;
    }

    public void save(Privilegio privilegio) throws Exception {
        this.privilegioDAO.save(privilegio);
    }

    public void update(Privilegio privilegio) throws Exception {
        this.privilegioDAO.update(privilegio);
    }

}
