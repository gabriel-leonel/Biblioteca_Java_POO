package visao.menus;

import classes.Bibliotecaria;
import classes.Usuario;
import util.ManipuladorArquivos;
import visao.telas.TelaBibliotecaria;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuInicial extends JFrame {

    public MenuInicial() {
        setTitle("Seleção de Perfil");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] perfis = {"Bibliotecária", "Usuário"};

        JPanel painel = new JPanel(new GridLayout(4, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lbl = new JLabel("Selecione o perfil de acesso:");
        JComboBox<String> comboPerfil = new JComboBox<>(perfis);
        JButton btnEntrar = new JButton("Entrar");

        btnEntrar.addActionListener(e -> {
            String perfilSelecionado = (String) comboPerfil.getSelectedItem();
            dispose();

            if ("Bibliotecária".equals(perfilSelecionado)) {
                Bibliotecaria b = TelaBibliotecaria.exibir();
                if (b != null) {
                    new MenuBibliotecaria(b);
                }
            } else if ("Usuário".equals(perfilSelecionado)) {
                // Seleção do usuário
                List<Usuario> usuarios = ManipuladorArquivos.lerUsuarios();
                if (usuarios.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum usuário encontrado.");
                    new MenuInicial();
                    return;
                }

                String[] opcoes = usuarios.stream()
                        .map(u -> u.getNome() + " (ID: " + u.getIdUsuario() + ")")
                        .toArray(String[]::new);

                String escolha = (String) JOptionPane.showInputDialog(
                        null,
                        "Selecione o(a) usuário(a):",
                        "Login Usuário",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        opcoes,
                        opcoes[0]
                );

                if (escolha == null) {
                    new MenuInicial();
                    return;
                }

                int idEscolhido = Integer.parseInt(escolha.split("ID: ")[1].replace(")", ""));
                Usuario u = usuarios.stream()
                        .filter(user -> user.getIdUsuario() == idEscolhido)
                        .findFirst()
                        .orElse(null);

                if (u != null) {
                    new MenuUsuario(u); // abre a tela do usuário
                }
            }
        });

        painel.add(lbl);
        painel.add(comboPerfil);
        painel.add(btnEntrar);

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> dispose());
        painel.add(btnSair);

        add(painel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MenuInicial();
    }
}
