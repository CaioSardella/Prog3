package br.edu.femass.biblioteca.model;

import lombok.Data;

import java.util.Date;

@Data
public class Emp {

    private String nomeUser;
    private TipoUser user;
    private String dataDevolucao = "";
    private String dataEmprestimo;
    private Integer prazoDevolucao;
    private String NomeEmp;
    private Integer CodigoEmp;



}
