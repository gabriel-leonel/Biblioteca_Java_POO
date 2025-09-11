package classes;

public class Livro {
    private int idLivro;
    private String tituloLivro;
    private String autorLivro;
    private String statusLivro;

    public Livro(int idLivro, String tituloLivro, String autorLivro, String statusLivro) {
        this.idLivro = idLivro;
        this.tituloLivro = tituloLivro;
        this.autorLivro = autorLivro;
        this.statusLivro = statusLivro;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public String getAutorLivro() {
        return autorLivro;
    }

    public void setAutorLivro(String autorLivro) {
        this.autorLivro = autorLivro;
    }

    public String getStatusLivro() {
        return statusLivro;
    }

    public void setStatusLivro(String statusLivro) {
        this.statusLivro = statusLivro;
    }

    public void cadastrarLivro(){

    }

    public void exibirInformações(){

    }

    @Override
    public String toString() {
        return "Livro \nidLivro= " + idLivro + "\ntituloLivro= " + tituloLivro + "\nautorLivro= " + autorLivro + "\nstatusLivro= " + statusLivro + "\n";
    }

    public String toCSV() {
        return idLivro + ";" +
                (tituloLivro != null ? tituloLivro : "") + ";" +
                (autorLivro != null ? autorLivro : "") + ";" +
                (statusLivro != null ? statusLivro : "");
    }
}
