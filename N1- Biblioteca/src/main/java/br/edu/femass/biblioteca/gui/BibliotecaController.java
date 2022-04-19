package br.edu.femass.biblioteca.gui;

import br.edu.femass.biblioteca.dao.Dao;
import br.edu.femass.biblioteca.dao.EmpDao;
import br.edu.femass.biblioteca.dao.LivroDao;
import br.edu.femass.biblioteca.model.Emp;
import br.edu.femass.biblioteca.model.Genero;
import br.edu.femass.biblioteca.model.Livro;
import br.edu.femass.biblioteca.model.TipoUser;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.*;

import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.IntStream;

public class BibliotecaController implements Initializable {

    public LivroDao livroDao = new LivroDao();
    public EmpDao empDao = new EmpDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<Genero> generos = FXCollections.observableArrayList(Genero.values());
        CboGenero.setItems(generos);

        ObservableList<TipoUser> tipouser = FXCollections.observableArrayList(TipoUser.values());
        CboTipoUser.setItems(tipouser);

        atualizarLista();
    }

    @FXML
    private TextField TxtCodigoLivro;

    @FXML
    private TextField TxtNomeLivro;

    @FXML
    private TextField TxtEdicao;

    @FXML
    private TextField TxtAutor;

    @FXML
    private TextField TxtAno;

    @FXML
    private ComboBox<Genero> CboGenero;

    @FXML
    private CheckBox ChbFixo;

    @FXML
    private ListView<Livro> LstLivros;



    @FXML
    private TextField TxtCodigoEmp;

    @FXML
    private TextField TxtNomeEmp;

    @FXML
    private TextField TxtNomeUser;

    @FXML
    private TextField TxtdataEmprestimo;

    @FXML
    private TextField TxtdataDevolucao;

    @FXML
    private ComboBox<TipoUser> CboTipoUser;

    @FXML
    private ListView<Emp> LstEmps;



    @FXML
    private void LstLivros_MouseClicked (MouseEvent evento){

        if(LstLivros.getSelectionModel().getSelectedItem()==null){
            System.out.println("Lista Vazia");
        } else {
            ExibirLivro();
            ler(true);
        }
        BtnGravar.setDisable(true);
    }

    @FXML
    private void LstLivros_KeyPressed (KeyEvent evento){
        ExibirLivro();
        BtnExcluir.setDisable(false);
        LstLivros.setDisable(false);
        ler(true);
        BtnGravar.setDisable(true);
    }

    @FXML
    private void LstEmps_MouseClicked (MouseEvent evento){

        if(LstLivros.getSelectionModel().getSelectedItem()==null){
            System.out.println("Lista Vazia");
        } else {
            ExibirEmp();
            ler(true);
        }
    }

    @FXML
    private void LstEmps_KeyPressed (KeyEvent evento){
        ExibirEmp();
        BtnExcluir.setDisable(false);
        LstLivros.setDisable(false);
    }


    @FXML
    private Button BtnIncluir;


    @FXML
    protected void BtnIncluir_Action(ActionEvent evento) {

        habilitar(true);
        limparBox();
        TxtNomeLivro.requestFocus();
    }

    @FXML
    private Button BtnExcluir;

    @FXML
    protected void BtnExcluir_Action(ActionEvent evento) {
        Livro livro = LstLivros.getSelectionModel().getSelectedItem();

        if(livro==null) return;

        try {
            livroDao.excluir((livro));
        } catch (Exception e) {
            e.printStackTrace();
        }
        limparBox();
        atualizarLista();

    }

    @FXML
    private Button BtnGravar;

    @FXML
    protected void BtnGravar_Action(ActionEvent evento) {
        Livro livro = new Livro();
        livro.setAno(TxtAno.getText());
        livro.setAutor(TxtAutor.getText());
        livro.setEdicao(TxtEdicao.getText());
        livro.setGenero(CboGenero.getValue());
        livro.setNomeLivro(TxtNomeLivro.getText());
        livro.setFixo(ChbFixo.isSelected());
        try {
            livroDao.gravar(livro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        atualizarLista();
        limparBox();
        habilitar(false);

    }

    @FXML
    private Button BtnCancelarLivro;

    @FXML
    protected void BtnCancelarLivro_Action (ActionEvent evento) {
        habilitar(false);
        limparBox();
    }

    @FXML
    private Button BtnCancelarEmp;

    @FXML
    protected void BtnCancelarEmp_Action (ActionEvent evento){
        limparEmp();
    }

    @FXML
    private  void limparEmp(){
        TxtNomeUser.setText("");
        CboTipoUser.setValue(null);
        TxtdataEmprestimo.setText("");
        TxtdataDevolucao.setText("");
        TxtCodigoEmp.setText("");
        TxtNomeEmp.setText("");
    }

    @FXML
    private void habilitar (Boolean incluir){
        TxtCodigoLivro.setDisable(!incluir);
        TxtAno.setDisable(!incluir);
        TxtAutor.setDisable(!incluir);
        TxtNomeLivro.setDisable(!incluir);
        TxtEdicao.setDisable(!incluir);
        CboGenero.setDisable(!incluir);
        BtnGravar.setDisable(!incluir);
        BtnCancelarLivro.setDisable(!incluir);
        BtnExcluir.setDisable(incluir);
        BtnIncluir.setDisable(incluir);
        LstLivros.setDisable(incluir);
        ChbFixo.setDisable(!incluir);

        TxtCodigoLivro.setEditable(!incluir);
        TxtEdicao.setEditable(incluir);
        TxtAno.setEditable(incluir);
        TxtAutor.setEditable(incluir);
        TxtNomeLivro.setEditable(incluir);


    }

    @FXML
    private void ler(Boolean incluir){
        habilitar(true);
        TxtCodigoLivro.setEditable(!incluir);
        TxtAno.setEditable(!incluir);
        TxtAutor.setEditable(!incluir);
        TxtEdicao.setEditable(!incluir);
        TxtNomeLivro.setEditable(!incluir);
        CboGenero.setDisable(incluir);
        BtnGravar.setDisable(incluir);
        BtnExcluir.setDisable(!incluir);
        LstLivros.setDisable(!incluir);
        BtnIncluir.setDisable(!incluir);
        ChbFixo.setDisable(incluir);
    }

    private void limparBox(){
        CboGenero.setValue(null);
        TxtAno.setText("");
        TxtAutor.setText("");
        TxtEdicao.setText("");
        TxtNomeLivro.setText("");
        TxtCodigoLivro.setText("");
        ChbFixo.setSelected(false);
    }

    private void atualizarLista() {
        List<Livro> livros = null;
        try {
            livros = livroDao.listar();
        } catch (Exception e) {
            livros = new ArrayList<Livro>();
        }
        ObservableList<Livro> livroOb = FXCollections.observableArrayList(livros);
        LstLivros.setItems(livroOb);
    }

    @FXML
    private void ExibirLivro (){
        Livro livro = LstLivros.getSelectionModel().getSelectedItem();
        if(livro==null) return;
        TxtNomeLivro.setText((livro.getNomeLivro()));
        TxtEdicao.setText((livro.getEdicao()));
        TxtAutor.setText((livro.getAutor()));
        TxtAno.setText((livro.getAno()));
        TxtCodigoLivro.setText((livro.getCodigoLivro().toString()));
        CboGenero.setValue(livro.getGenero());
        ChbFixo.setSelected(livro.getFixo());
    }

    @FXML
    private void ExibirEmp (){
        Emp emp = LstEmps.getSelectionModel().getSelectedItem();
        TxtNomeUser.setText((emp.getNomeUser()));
        TxtdataDevolucao.setText((emp.getDataDevolucao().toString()));
        TxtdataEmprestimo.setText((emp.getDataEmprestimo().toString()));
        CboTipoUser.setValue(emp.getUser());

    }

    @FXML
    private void CboTipoUser_Action (ActionEvent evento){
        data();
    }

    @FXML
    private void data (){

        TxtdataEmprestimo.setEditable(false);
        TxtdataEmprestimo.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        TxtdataDevolucao.setEditable(false);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar date = Calendar.getInstance();
        try {
            date.setTime(sdf.parse(TxtdataEmprestimo.getText()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(CboTipoUser.getValue().toString() == "Professor") {
            date.add(Calendar.DAY_OF_MONTH,30);
            TxtdataDevolucao.setText(sdf.format(date.getTime()));
        }else{
            date.add(Calendar.DAY_OF_MONTH,5);
            TxtdataDevolucao.setText(sdf.format(date.getTime()));
        }
    }

    @FXML
    private Button BtnEmprestimo;

    @FXML
    private void BtnEmprestimo_Action(ActionEvent evento){

            if(EmpValidacao()) {

                Emp emp = new Emp();
                emp.setNomeUser(TxtNomeUser.getText());
                emp.setUser(CboTipoUser.getValue());
                if (CboTipoUser.getValue().toString() == "Professor") {
                    emp.setPrazoDevolucao(30);
                } else {
                    emp.setPrazoDevolucao(5);
                }
                emp.setDataEmprestimo(TxtdataEmprestimo.getText());
                emp.setDataDevolucao(TxtdataDevolucao.getText());
                emp.setNomeEmp(TxtNomeEmp.getText());
                emp.setCodigoEmp(Integer.parseInt(TxtCodigoEmp.getText()));
                try {
                    empDao.gravar(emp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                atualizarListaEmp();
                limparBox();

            }else{
                System.out.println("Failed");
            }

    }


    @FXML
    private void atualizarListaEmp() {
        List<Emp> emps = null;
        try {
            emps = empDao.listar();
        } catch (Exception e) {
            emps = new ArrayList<Emp>();
        }
        ObservableList<Emp> empOb = FXCollections.observableArrayList(emps);
        LstEmps.setItems(empOb);
    }


    @FXML
    private boolean EmpValidacao() {
        Integer status = 0;
        String mensagem = "";

            if(LivroCadastrado()){
                status=status+1;
            }

            if(CopiaFixa()){
                status= status+2;
            }

            if(LimiteLoca()){
                status=status+4;
            }

            if(LivroDisp()){
                status=status+8;
            }

            if(status == 14){
                mensagem = ("Livro não Cadastrado no Sistema");
            }

            if(status == 13){
                mensagem = ("Cópia FIXA, proibido empréstimo");
            }

            if(status == 11){
                mensagem = ("Usuário com máximo de locações ativas");
            }

            if(status == 7){
                mensagem = ("Livro já locado por outro usuário");
            }

        if (status == 15) {
            return true;
        }else{
            JOptionPane.showMessageDialog(null,mensagem);
            return false;
        }

    }

    @FXML
    private boolean LivroCadastrado(){
        boolean status = false;

        try {//Verifica se o codigo e o nome do livro está cadastrado no sistema
            for (Livro l : livroDao.listar()) {
                if (l.getNomeLivro().equals(TxtNomeEmp.getText()) && l.getCodigoLivro().equals(Integer.parseInt(TxtCodigoEmp.getText()))) {
                    status=true;
                } else {
                    status = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @FXML
    private boolean CopiaFixa(){
        boolean status = false;
        try {//Verifica se a cópia a ser emprestada é fixa da biblioteca
            for (Livro l : livroDao.listar()) {
                if (l.getCodigoLivro().equals(Integer.parseInt(TxtCodigoEmp.getText())) && l.getFixo().toString().equals("false")) {
                    status = true;
                } else {
                    status = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @FXML
    private boolean LimiteLoca(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean status = false;
        Integer count = 0;
        try {//Verifica se o usuario ja atingiu o limite de locações
            for (Emp e : empDao.listar()) {
                if (e.getNomeUser().equals(TxtNomeUser.getText())&& sdf.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).compareTo(sdf.parse(e.getDataDevolucao())) < 0) {
                    count++;
                }
            }
            if (count < 5) {
                status = true;
            } else {
                status = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;

    }

    @FXML
    private boolean LivroDisp() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean status = false;

        try {
            if(empDao.listar().isEmpty()){
                status= true;
            }else {
                try {
                    for (Emp e : empDao.listar()) {
                        if ((e.getCodigoEmp()).equals(Integer.parseInt((TxtCodigoEmp.getText()))) && sdf.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).compareTo(sdf.parse(e.getDataDevolucao())) < 0) {
                            status = false;
                        } else {
                            status = true;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;

        }
    }
