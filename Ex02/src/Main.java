import classes.*;
import util.ManipuladorArquivos;
import visao.menus.MenuInicial;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
        new MenuInicial();
    }
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//
//        Bibliotecaria b1 = new Bibliotecaria(1, "Maria");
//        b1.setEmail("maria@biblioteca.com");
//        Bibliotecaria b2 = new Bibliotecaria(2, "Joana");
//        b2.setEmail("joana@biblioteca.com");
//
//        Usuario u1 = new Usuario(1, "Gabriel Leonel", "gabriel.leonel2016@gmail.com");
//        Usuario u2 = new Usuario(2, "Carlos Silva", "carlos.silva@gmail.com");
//
//        Livro l1 = new Livro(1, "Dom Casmurro", "Machado de Assis", "Disponível");
//        Livro l2 = new Livro(2, "Memórias Póstumas de Brás Cubas", "Machado de Assis", "Emprestado");
//
//        Emprestimo e1 = new Emprestimo(1, sdf.parse("20/08/2025"), sdf.parse("25/08/2025"));
//        Emprestimo e2 = new Emprestimo(2, sdf.parse("22/08/2025"), sdf.parse("28/08/2025"));
//
//        Reserva r1 = new Reserva(1, sdf.parse("26/08/2025"), "Agendada");
//        Reserva r2 = new Reserva(2, sdf.parse("27/08/2025"), "Confirmada");
//
//        ManipuladorArquivos.salvarObjeto("Bibliotecaria", b1, 3);
//        ManipuladorArquivos.salvarObjeto("Bibliotecaria", b2, 3);
//
//        ManipuladorArquivos.salvarObjeto("Usuario", u1, 3);
//        ManipuladorArquivos.salvarObjeto("Usuario", u2, 3);
//
//        ManipuladorArquivos.salvarObjeto("Livro", l1, 4);
//        ManipuladorArquivos.salvarObjeto("Livro", l2, 4);
//
//        ManipuladorArquivos.salvarObjeto("Emprestimo", e1, 3);
//        ManipuladorArquivos.salvarObjeto("Emprestimo", e2, 3);
//
//        ManipuladorArquivos.salvarObjeto("Reserva", r1, 3);
//        ManipuladorArquivos.salvarObjeto("Reserva", r2, 3);
//
//        System.out.println("-----Objetos gerados em CSV-----");
//
//        imprimirLista("Bibliotecaria", 3);
//        imprimirLista("Usuario", 3);
//        imprimirLista("Livro", 4);
//        imprimirLista("Emprestimo", 3);
//        imprimirLista("Reserva", 3);
//    }
//
//    private static void imprimirLista(String nomeClasse, int camposEsperados) {
//        List<String[]> lista = ManipuladorArquivos.ler(nomeClasse, camposEsperados);
//        System.out.println(">>> " + nomeClasse);
//        for (String[] linha : lista) {
//            System.out.println(String.join(" | ", linha));
//        }
//        System.out.println();
//    }
}
