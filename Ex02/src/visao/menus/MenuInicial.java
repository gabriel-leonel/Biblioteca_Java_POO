package visao.menus;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import util.ManipuladorArquivos;

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
//                new MenuBibliotecaria();
            } else {
                selecionarUsuario(perfilSelecionado);
            }
        });

        painel.add(lbl);
        painel.add(comboPerfil);
        painel.add(btnEntrar);
        painel.add(new JButton("Sair") {{
            addActionListener(e -> dispose());
        }});

        add(painel);
        setVisible(true);
    }

    private void selecionarUsuario(String perfil) {
        String arquivo = switch (perfil) {
            case "Bibliotecária" -> "Bibliotecaria";
            case "Usuário" -> "Usuario";
            default -> null;
        };

        if (arquivo == null) {
            JOptionPane.showMessageDialog(null, "Perfil inválido.");
            new MenuInicial();
            return;
        }

        List<String[]> registros = ManipuladorArquivos.ler(arquivo, getCamposEsperados(arquivo));
        if (registros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado para " + perfil + ".");
            new MenuInicial();
            return;
        }

        String[] opcoes = registros.stream()
                .map(r -> r[1] + " (ID: " + r[0] + ")")
                .toArray(String[]::new);

        String escolha = (String) JOptionPane.showInputDialog(
                null,
                "Selecione o(a) " + perfil + ":",
                "Login",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        if (escolha == null) {
            new MenuInicial();
            return;
        }

        int id = Integer.parseInt(escolha.split("ID: ")[1].replace(")", ""));
        switch (perfil) {
//            case "Bibliotecária" -> new MenuBibliotecaria();
//            case "Usuário" -> new MenuUsuario(id);
        }
    }

    private int getCamposEsperados(String nomeClasse) {
        return switch (nomeClasse) {
            case "Bibliotecária" -> 3;
            case "Usuário" -> 3;
            default -> 0;
        };
    }
}

