package visao.menus;

import classes.Bibliotecaria;
import classes.Usuario;
import classes.Livro;
import util.ManipuladorArquivos;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuBibliotecaria extends JFrame {
    private Bibliotecaria bibliotecaria;
    private DefaultListModel<String> listaUsuariosModel;
    private DefaultListModel<String> listaLivrosModel;

    public MenuBibliotecaria(Bibliotecaria b) {
        this.bibliotecaria = b;

        setTitle("Menu da Bibliotecária - " + bibliotecaria.getNome());
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Usuários", criarPainelUsuarios());
        abas.addTab("Livros", criarPainelLivros());

        add(abas, BorderLayout.CENTER);

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {
            dispose();
            new MenuInicial();
        });

        add(btnSair, BorderLayout.SOUTH);

        setVisible(true);
    }

    // ---------------------- USUÁRIOS ----------------------
    private JPanel criarPainelUsuarios() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));

        listaUsuariosModel = new DefaultListModel<>();
        carregarUsuarios();
        JList<String> listaUsuarios = new JList<>(listaUsuariosModel);
        JScrollPane scrollUsuarios = new JScrollPane(listaUsuarios);

        JPanel form = new JPanel(new GridLayout(2, 2, 5, 5));

        JPanel botoes = new JPanel(new FlowLayout());
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnRemover = new JButton("Remover");

        JTextField txtNome = new JTextField();
        JTextField txtEmail = new JTextField();
        form.add(new JLabel("Nome:"));
        form.add(txtNome);
        form.add(new JLabel("Email:"));
        form.add(txtEmail);

        // Adicionar usuário
        btnAdicionar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String email = txtEmail.getText().trim();
            if (nome.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
                return;
            }

            int novoId = ManipuladorArquivos.proximoId("Usuario");
            Usuario novo = new Usuario(novoId, nome, email);
            ManipuladorArquivos.salvarObjeto("Usuario", novo, 3);

            listaUsuariosModel.addElement(novo.getNome() + " (ID: " + novo.getIdUsuario() + ")");
            txtNome.setText("");
            txtEmail.setText("");
        });

        // Remover usuário
        btnRemover.addActionListener(e -> {
            int idx = listaUsuarios.getSelectedIndex();
            if (idx == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um usuário para remover.");
                return;
            }

            String selecionado = listaUsuariosModel.getElementAt(idx);
            int id = Integer.parseInt(selecionado.split("ID: ")[1].replace(")", ""));

            List<String[]> registros = ManipuladorArquivos.ler("Usuario", 3);
            registros.removeIf(r -> Integer.parseInt(r[0]) == id);
            ManipuladorArquivos.salvarLista("Usuario", registros);

            listaUsuariosModel.remove(idx);
        });

        botoes.add(btnAdicionar);
        botoes.add(btnRemover);

        painel.add(form, BorderLayout.NORTH);
        painel.add(scrollUsuarios, BorderLayout.CENTER);
        painel.add(botoes, BorderLayout.SOUTH);

        return painel;
    }

    private void carregarUsuarios() {
        List<Usuario> usuarios = ManipuladorArquivos.lerUsuarios();
        for (Usuario u : usuarios) {
            listaUsuariosModel.addElement(u.getNome() + " (ID: " + u.getIdUsuario() + ")");
        }
    }

    // ---------------------- LIVROS ----------------------
    private JPanel criarPainelLivros() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));

        listaLivrosModel = new DefaultListModel<>();
        carregarLivros();
        JList<String> listaLivros = new JList<>(listaLivrosModel);
        JScrollPane scrollLivros = new JScrollPane(listaLivros);

        JPanel form = new JPanel(new GridLayout(2, 2, 5, 5));
        JTextField txtTitulo = new JTextField();
        JTextField txtAutor = new JTextField();
        form.add(new JLabel("Título:"));
        form.add(txtTitulo);
        form.add(new JLabel("Autor:"));
        form.add(txtAutor);

        JPanel botoes = new JPanel(new FlowLayout());
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnRemover = new JButton("Remover");

        // Adicionar livro
        btnAdicionar.addActionListener(e -> {
            String titulo = txtTitulo.getText().trim();
            String autor = txtAutor.getText().trim();
            if (titulo.isEmpty() || autor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
                return;
            }

            int novoId = ManipuladorArquivos.proximoId("Livro");
            Livro novo = new Livro(novoId, titulo, autor, "Disponível");
            ManipuladorArquivos.salvarObjeto("Livro", novo, 4);

            listaLivrosModel.addElement(novo.getTituloLivro() + " - " + novo.getAutorLivro() + " (ID: " + novo.getIdLivro() + ")");
            txtTitulo.setText("");
            txtAutor.setText("");
        });

        // Remover livro
        btnRemover.addActionListener(e -> {
            int idx = listaLivros.getSelectedIndex();
            if (idx == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um livro para remover.");
                return;
            }

            String selecionado = listaLivrosModel.getElementAt(idx);
            int id = Integer.parseInt(selecionado.split("ID: ")[1].replace(")", ""));

            List<String[]> registros = ManipuladorArquivos.ler("Livro", 4);
            registros.removeIf(r -> Integer.parseInt(r[0]) == id);
            ManipuladorArquivos.salvarLista("Livro", registros);

            listaLivrosModel.remove(idx);
        });

        botoes.add(btnAdicionar);
        botoes.add(btnRemover);

        painel.add(form, BorderLayout.NORTH);
        painel.add(scrollLivros, BorderLayout.CENTER);
        painel.add(botoes, BorderLayout.SOUTH);

        return painel;
    }

    private void carregarLivros() {
        List<Livro> livros = ManipuladorArquivos.lerLivros();
        for (Livro l : livros) {
            listaLivrosModel.addElement(l.getTituloLivro() + " - " + l.getAutorLivro() + " (ID: " + l.getIdLivro() + ")");
        }
    }
}
