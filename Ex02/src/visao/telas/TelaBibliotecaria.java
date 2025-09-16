package visao.telas;

import classes.Bibliotecaria;
import util.ManipuladorArquivos;

import javax.swing.*;
import java.util.List;

public class TelaBibliotecaria {

    public static Bibliotecaria exibir() {
        List<Bibliotecaria> bibliotecarias = ManipuladorArquivos.lerBibliotecarias();

        if (bibliotecarias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma bibliotecária encontrada.");
            System.exit(0);
        }

        // Monta opções de exibição
        String[] opcoes = bibliotecarias.stream()
                .map(b -> b.getNome() + " (ID: " + b.getIdBibliotecaria() + ")")
                .toArray(String[]::new);

        // Tela de seleção
        String escolha = (String) JOptionPane.showInputDialog(
                null,
                "Selecione a bibliotecária:",
                "Login Bibliotecária",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        if (escolha == null) {
            JOptionPane.showMessageDialog(null, "Acesso cancelado.");
            System.exit(0);
        }

        // Extrai o ID da escolha
        int idEscolhido = Integer.parseInt(
                escolha.split("ID: ")[1].replace(")", "")
        );

        // Busca a instância correspondente
        return bibliotecarias.stream()
                .filter(b -> b.getIdBibliotecaria() == idEscolhido)
                .findFirst()
                .orElse(null);
    }
}
