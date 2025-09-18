package visao.menus;

import classes.Usuario;
import classes.Livro;
import classes.Emprestimo;
import util.ManipuladorArquivos;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MenuUsuario extends JFrame {
    private Usuario usuario;

    public MenuUsuario(Usuario u) {
        this.usuario = u;

        setTitle("Menu Usuário - " + usuario.getNome());
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(4, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnAtualizar = new JButton("Atualizar Contato");
        JButton btnEmprestimos = new JButton("Emprestimos");
        JButton btnCancelaEmprestimos = new JButton("Cancelar Empréstimo");
        JButton btnSair = new JButton("Sair");

        // ---------------- Atualizar contato ----------------
        btnAtualizar.addActionListener(e -> {
            String novoNome = JOptionPane.showInputDialog(this, "Nome:", usuario.getNome());
            String novoEmail = JOptionPane.showInputDialog(this, "Email:", usuario.getEmail());

            if (novoNome != null && novoEmail != null) {
                usuario.setNome(novoNome);
                usuario.setEmail(novoEmail);
                ManipuladorArquivos.atualizarObjeto("Usuario", usuario.getIdUsuario(), usuario, 3);
                JOptionPane.showMessageDialog(this, "Dados atualizados com sucesso!");
            }
        });

        // ---------------- Fazer empréstimos ----------------
        btnEmprestimos.addActionListener(e -> {
            List<Livro> livros = ManipuladorArquivos.lerLivros();
            // Filtra apenas livros disponíveis
            List<Livro> disponiveis = livros.stream()
                    .filter(l -> l.getStatusLivro().equalsIgnoreCase("Disponível"))
                    .toList();

            if (disponiveis.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum livro disponível no momento.");
                return;
            }

            String[] opcoes = disponiveis.stream()
                    .map(l -> l.getTituloLivro() + " - " + l.getAutorLivro() + " (ID: " + l.getIdLivro() + ")")
                    .toArray(String[]::new);

            String escolha = (String) JOptionPane.showInputDialog(
                    this,
                    "Selecione um livro para empréstimo:",
                    "Empréstimos",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            if (escolha != null) {
                int idLivro = Integer.parseInt(escolha.split("ID: ")[1].replace(")", ""));
                Livro livroSelecionado = disponiveis.stream()
                        .filter(l -> l.getIdLivro() == idLivro)
                        .findFirst()
                        .orElse(null);

                if (livroSelecionado != null) {
                    livroSelecionado.setStatusLivro("Emprestado");
                    ManipuladorArquivos.atualizarObjeto("Livro", livroSelecionado.getIdLivro(), livroSelecionado, 4);

                    // Cria empréstimo
                    int idEmprestimo = ManipuladorArquivos.proximoId("Emprestimo");
                    Emprestimo e1 = new Emprestimo(idEmprestimo, new Date(), null, usuario.getIdUsuario(), idLivro);
                    ManipuladorArquivos.salvarObjeto("Emprestimo", e1, 5);

                    JOptionPane.showMessageDialog(this, "Empréstimo realizado com sucesso!");
                }
            }
        });

        // ---------------- Cancelar empréstimo ----------------
        btnCancelaEmprestimos.addActionListener(e -> {
            List<Emprestimo> emprestimos = ManipuladorArquivos.lerEmprestimos();

            // filtra apenas os empréstimos do usuário logado
            List<Emprestimo> meusEmprestimos = emprestimos.stream()
                    .filter(em -> em.getIdUsuario() == usuario.getIdUsuario())
                    .toList();

            if (meusEmprestimos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum empréstimo ativo para este usuário.");
                return;
            }

            String[] opcoes = meusEmprestimos.stream()
                    .map(em -> "Empréstimo ID: " + em.getIdEmprestimo() + " - Livro ID: " + em.getIdLivro())
                    .toArray(String[]::new);

            String escolha = (String) JOptionPane.showInputDialog(
                    this,
                    "Selecione um empréstimo para cancelar:",
                    "Cancelar Empréstimo",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            if (escolha != null) {
                int idEmprestimo = Integer.parseInt(escolha.split("ID: ")[1].split(" - ")[0].trim());
                int idLivro = Integer.parseInt(escolha.split("Livro ID: ")[1].trim());

                // remove o empréstimo
                emprestimos.removeIf(em -> em.getIdEmprestimo() == idEmprestimo);

                // salva novamente todos os empréstimos
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                ManipuladorArquivos.salvarLista("Emprestimo", emprestimos.stream()
                        .map(em -> new String[]{
                                String.valueOf(em.getIdEmprestimo()),
                                em.getDataEmprestimo() != null ? sdf.format(em.getDataEmprestimo()) : "",
                                em.getDataDevolucao() != null ? sdf.format(em.getDataDevolucao()) : "",
                                String.valueOf(em.getIdUsuario()),
                                String.valueOf(em.getIdLivro())
                        })
                        .toList()
                );

                // Atualiza o livro para "Disponível"
                List<String[]> livros = ManipuladorArquivos.ler("Livro", 4);
                for (String[] campos : livros) {
                    if (Integer.parseInt(campos[0]) == idLivro) {
                        campos[3] = "Disponível"; // altera status
                        break;
                    }
                }
                ManipuladorArquivos.salvarLista("Livro", livros);

                JOptionPane.showMessageDialog(this, "Empréstimo cancelado com sucesso!");
            }
        });

        btnSair.addActionListener(e -> {
            dispose();
            new MenuInicial();
        });

        painel.add(btnAtualizar);
        painel.add(btnEmprestimos);
        painel.add(btnCancelaEmprestimos);
        painel.add(btnSair);

        add(painel);
        setVisible(true);
    }
}
