package classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Emprestimo {
    private int idEmprestimo;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private Usuario usuario;
    private Livro livro;

    public Emprestimo(int id, Date dEmp, Date dDev, Usuario usuario, Livro livro) {
        this.idEmprestimo = id;
        this.dataEmprestimo = dEmp;
        this.dataDevolucao = dDev;
        this.usuario = usuario;
        this.livro = livro;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public void registrarEmprestimo() {}

    public void registrarDevolucao() {}

    @Override
    public String toString() {
        return "Emprestimo \n" +
                "idEmprestimo= " + idEmprestimo +
                "\ndataEmprestimo= " + dataEmprestimo +
                "\ndataDevolucao= " + dataDevolucao +
                "\nusuario= " + (usuario != null ? usuario.getNome() + " (ID:" + usuario.getIdUsuario() + ")" : "null") +
                "\nlivro= " + (livro != null ? livro.getTituloLivro() + " (ID:" + livro.getIdLivro() + ")" : "null") +
                "\n";
    }

    public String toCSV() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String de = dataDevolucao != null ? sdf.format(dataDevolucao) : "";
        return idEmprestimo + ";" +
                (dataEmprestimo != null ? sdf.format(dataEmprestimo) : "") + ";" +
                de + ";" +
                (usuario != null ? usuario.getIdUsuario() : "") + ";" +
                (livro != null ? livro.getIdLivro() : "");
    }
}
