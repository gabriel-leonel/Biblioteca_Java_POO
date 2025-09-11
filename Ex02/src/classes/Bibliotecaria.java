package classes;

public class Bibliotecaria {
    private int idBibliotecaria;
    private String nome;
    private String email;

    public Bibliotecaria(int id, String nome) {
        this.idBibliotecaria = id;
        this.nome = nome;
    }

    public int getIdBibliotecaria() {
        return idBibliotecaria;
    }

    public void setIdBibliotecaria(int idBibliotecaria) {
        this.idBibliotecaria = idBibliotecaria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void cadastrarLivro(Livro livro){

    }

    public void cadastrarUsuario(Usuario usuario){

    }

    public void registrarEmprestimo(Emprestimo emprestimo){

    }

    public void registrarDevolucao(Emprestimo emprestimo){

    }

    public void registrarReserva(Reserva reserva){

    }

    public void gerenciarLivros(){

    }

    public void gerenciarUsuario(){

    }

    @Override
    public String toString(){
        return "Bibliotecaria \nidBibliotecaria= "+idBibliotecaria+"\nNome= "+nome+"\nE-mail= "+email + "\n";
    }

    public String toCSV() {
        return idBibliotecaria + ";" +
                (nome != null ? nome : "") + ";" +
                (email != null ? email : "");
    }
}