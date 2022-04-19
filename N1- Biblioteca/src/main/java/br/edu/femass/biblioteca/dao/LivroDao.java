package br.edu.femass.biblioteca.dao;

import br.edu.femass.biblioteca.model.Livro;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LivroDao implements Dao<Livro> {

    private static List<Livro> livros = new ArrayList<Livro>();

    @Override
    public void gravar(Livro objeto) throws Exception {
        livros.add(objeto);

    }

    @Override
    public List<Livro> listar() throws Exception {
        return livros;
    }

    @Override
    public void excluir(Livro objeto) throws Exception {
        livros.remove(objeto);

    }
}