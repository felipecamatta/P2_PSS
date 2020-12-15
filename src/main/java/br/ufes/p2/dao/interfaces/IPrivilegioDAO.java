package br.ufes.p2.dao.interfaces;

import br.ufes.p2.model.Privilegio;

public interface IPrivilegioDAO {

    public void save(Privilegio privilegio) throws Exception;

    public void update(Privilegio privilegio) throws Exception;

}
