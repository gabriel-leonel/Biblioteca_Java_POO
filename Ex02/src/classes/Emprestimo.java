package classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Emprestimo {
    private int idEmprestimo;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public Emprestimo(int idEmprestimo, Date dataEmprestimo, Date dataDevolucao) {
        this.idEmprestimo = idEmprestimo;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
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

    public void registrarEmprestimo(){}

    public void registrarDevolucao(){}

    @Override
    public String toString() {
        return "Emprestimo \nidEmprestimo= " + idEmprestimo + "\ndataEmprestimo= " + dataEmprestimo + "\ndataDevolucao= " + dataDevolucao + "\n";
    }

    public String toCSV() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return idEmprestimo + ";" +
                (dataEmprestimo != null ? sdf.format(dataEmprestimo) : "") + ";" +
                (dataDevolucao != null ? sdf.format(dataDevolucao) : "");
    }
}
