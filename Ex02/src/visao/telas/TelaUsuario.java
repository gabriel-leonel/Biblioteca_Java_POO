package visao.telas;

import classes.Usuario;
import util.ManipuladorArquivos;

import javax.swing.*;
import java.util.List;

public class TelaUsuario {

    public static Usuario exibir() {
        List<Usuario> usuarios = ManipuladorArquivos.lerUsuarios();

        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum usuário encontrado.");
            System.exit(0);
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
            JOptionPane.showMessageDialog(null, "Acesso cancelado.");
            System.exit(0);
        }

        int idEscolhido = Integer.parseInt(escolha.split("ID: ")[1].replace(")", ""));

        return usuarios.stream()
                .filter(u -> u.getIdUsuario() == idEscolhido)
                .findFirst()
                .orElse(null);
    }
}
