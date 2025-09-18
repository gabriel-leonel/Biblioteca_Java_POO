package classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Emprestimo {
    private int idEmprestimo;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private int idUsuario;
    private int idLivro;

    public Emprestimo(int id, Date dEmp, Date dDev, int idUsuario, int idLivro) {
        this.idEmprestimo = id;
        this.dataEmprestimo = dEmp;
        this.dataDevolucao = dDev;
        this.idUsuario = idUsuario;
        this.idLivro = idLivro;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getIdUsuario(){ return idUsuario; }

    public void setIdUsuario(int idUsuario){ this.idUsuario = idUsuario; }

    public int getIdLivro(){ return idLivro; }

    public void setIdLivro(int idLivro){ this.idLivro = idLivro;}

    public void registrarEmprestimo(){}

    public void registrarDevolucao(){}

    @Override
    public String toString() {
        return "Emprestimo \nidEmprestimo= " + idEmprestimo + "\ndataEmprestimo= " + dataEmprestimo + "\ndataDevolucao= " + dataDevolucao + "\n";
    }

    public String toCSV() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String de = dataDevolucao != null ? sdf.format(dataDevolucao) : "";
        return idEmprestimo + ";" + (dataEmprestimo != null ? sdf.format(dataEmprestimo) : "") + ";" + de
                + ";" + idUsuario + ";" + idLivro;
    }
}
