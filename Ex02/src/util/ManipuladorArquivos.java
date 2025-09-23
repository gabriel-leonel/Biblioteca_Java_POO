package util;

import classes.*;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ManipuladorArquivos {
    private static final String DIRETORIO = "dados";

    public static void salvarObjeto(String nomeClasse, Object objeto, int camposEsperados) {
        String linhaCSV = "";

        if (objeto instanceof Bibliotecaria b) {
            linhaCSV = b.toCSV();
        } else if (objeto instanceof Usuario u) {
            linhaCSV = u.toCSV();
        } else if (objeto instanceof Livro l) {
            linhaCSV = l.toCSV();
        } else if (objeto instanceof Emprestimo e) {
            linhaCSV = e.toCSV();
        } else if (objeto instanceof Reserva r) {
            linhaCSV = r.toCSV();
        }

        salvar(nomeClasse, linhaCSV, camposEsperados);
    }

    public static void atualizarObjeto(String nomeClasse, int id, Object novoObjeto, int camposEsperados) {
        List<String[]> lista = ler(nomeClasse, camposEsperados);

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i)[0].equals(Integer.toString(id))) {
                String novaLinha = "";

                if (novoObjeto instanceof Bibliotecaria b) {
                    novaLinha = b.toCSV();
                } else if (novoObjeto instanceof Usuario u) {
                    novaLinha = u.toCSV();
                } else if (novoObjeto instanceof Livro l) {
                    novaLinha = l.toCSV();
                } else if (novoObjeto instanceof Emprestimo e) {
                    novaLinha = e.toCSV();
                } else if (novoObjeto instanceof Reserva r) {
                    novaLinha = r.toCSV();
                }

                lista.set(i, novaLinha.split(";"));
                break;
            }
        }

        salvarLista(nomeClasse, lista);
    }

    public static void salvar(String nomeClasse, String linhaCSV, int camposEsperados) {
        try {
            File dir = new File(DIRETORIO);
            if (!dir.exists()) dir.mkdir();
            FileWriter fw = new FileWriter(new File(dir, nomeClasse + ".csv"), true);
            fw.write(linhaCSV + "\n");
            fw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage());
        }
    }

    public static List<String[]> ler(String nomeClasse, int camposEsperados) {
        List<String[]> linhas = new ArrayList<>();
        File arq = new File(DIRETORIO, nomeClasse + ".csv");
        if (!arq.exists()) return linhas;
        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (camposEsperados > 0) {
                    if (partes.length == camposEsperados) {
                        linhas.add(partes);
                    }
                } else {
                    linhas.add(partes);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler: " + e.getMessage());
        }
        return linhas;
    }

    public static void salvarLista(String nomeClasse, List<String[]> lista) {
        try {
            FileWriter fw = new FileWriter(new File(DIRETORIO, nomeClasse + ".csv"), false);
            for (String[] campos : lista) {
                fw.write(String.join(";", campos) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao sobrescrever arquivo: " + e.getMessage());
        }
    }

    public static int proximoId(String nomeClasse) {
        List<String[]> lista = ler(nomeClasse, -1);
        int maiorId = 0;

        for (String[] campos : lista) {
            try {
                int id = Integer.parseInt(campos[0]);
                if (id > maiorId) maiorId = id;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro ao gerar id: " + e.getMessage());
            }
        }

        return maiorId + 1;
    }

    public static List<Bibliotecaria> lerBibliotecarias() {
        List<Bibliotecaria> lista = new ArrayList<>();
        for (String[] campos : ler("Bibliotecaria", 3)) {
            try {
                int id = Integer.parseInt(campos[0]);
                String nome = campos[1];
                String email = campos[2];
                Bibliotecaria b = new Bibliotecaria(id, nome);
                b.setEmail(email);
                lista.add(b);
            } catch (Exception e) {
                System.err.println("Erro ao ler bibliotecária: " + Arrays.toString(campos));
            }
        }
        return lista;
    }

    public static List<Usuario> lerUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        for (String[] campos : ler("Usuario", 3)) {
            try {
                int id = Integer.parseInt(campos[0]);
                String nome = campos[1];
                String email = campos[2];
                lista.add(new Usuario(id, nome, email));
            } catch (Exception e) {
                System.err.println("Erro ao ler usuário: " + Arrays.toString(campos));
            }
        }
        return lista;
    }

    public static List<Livro> lerLivros() {
        List<Livro> lista = new ArrayList<>();
        for (String[] campos : ler("Livro", 4)) {
            try {
                int id = Integer.parseInt(campos[0]);
                String titulo = campos[1];
                String autor = campos[2];
                String status = campos[3];
                lista.add(new Livro(id, titulo, autor, status));
            } catch (Exception e) {
                System.err.println("Erro ao ler livro: " + Arrays.toString(campos));
            }
        }
        return lista;
    }

    public static List<Emprestimo> lerEmprestimos() {
        List<Emprestimo> lista = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        List<Usuario> usuarios = lerUsuarios();
        List<Livro> livros = lerLivros();

        for (String[] campos : ler("Emprestimo", 5)) {
            try {
                int id = Integer.parseInt(campos[0]);

                Date dataEmprestimo = null;
                Date dataDevolucao = null;

                if (campos[1] != null && !campos[1].isBlank()) {
                    dataEmprestimo = sdf.parse(campos[1]);
                }

                if (campos[2] != null && !campos[2].isBlank()) {
                    dataDevolucao = sdf.parse(campos[2]);
                }

                int idUsuario = Integer.parseInt(campos[3]);
                int idLivro = Integer.parseInt(campos[4]);

                Usuario usuario = usuarios.stream()
                        .filter(u -> u.getIdUsuario() == idUsuario)
                        .findFirst()
                        .orElse(null);

                Livro livro = livros.stream()
                        .filter(l -> l.getIdLivro() == idLivro)
                        .findFirst()
                        .orElse(null);

                lista.add(new Emprestimo(id, dataEmprestimo, dataDevolucao, usuario, livro));
            } catch (Exception e) {
                System.err.println("Erro ao ler empréstimo: " + Arrays.toString(campos));
            }
        }
        return lista;
    }



    public static List<Reserva> lerReservas() {
        List<Reserva> lista = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (String[] campos : ler("Reserva", 3)) {
            try {
                int id = Integer.parseInt(campos[0]);
                Date dataReserva = sdf.parse(campos[1]);
                String status = campos[2];
                lista.add(new Reserva(id, dataReserva, status));
            } catch (Exception e) {
                System.err.println("Erro ao ler reserva: " + Arrays.toString(campos));
            }
        }
        return lista;
    }
}
