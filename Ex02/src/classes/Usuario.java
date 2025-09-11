package classes;

public class Usuario {
    private int idUsuario;
    private String nome;
    private String email;

    public Usuario(int idUsuario, String nome, String email) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public void cadastrarUsuario(){

    }

    public void listarEmprestimos(){}

    @Override
    public String toString() {
        return "Usuario \nnome= " + nome + "\nID= " + idUsuario + "\nemail= " + email + "\n";
    }

    public String toCSV() {
        return idUsuario + ";" +
                (nome != null ? nome : "") + ";" +
                (email != null ? email : "");
    }
}
