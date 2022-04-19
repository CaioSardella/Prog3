package br.edu.femass.biblioteca.dao;

import br.edu.femass.biblioteca.model.Emp;

import java.util.ArrayList;
import java.util.List;

public class EmpDao implements Dao<Emp> {

    private static List<Emp> emps = new ArrayList<Emp>();

    @Override
    public void gravar(Emp objeto) throws Exception {
        emps.add(objeto);
    }

    @Override
    public List<Emp> listar() throws Exception {
        return emps;
    }

    @Override
    public void excluir(Emp objeto) throws Exception {
        emps.remove(objeto);

    }
}