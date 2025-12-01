package Grafica;

import Dados.Biblioteca;
import Dados.Obra;
import Dados.Avaliacao;
import Dados.Usuario;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class CSVExporter {

    public static void exportarBibliotecaParaCSV(JFrame parent, Biblioteca biblioteca) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Salvar biblioteca como CSV");
        int escolha = chooser.showSaveDialog(parent);
        if (escolha != JFileChooser.APPROVE_OPTION) return;

        File arquivo = chooser.getSelectedFile();
        if (!arquivo.getName().toLowerCase().endsWith(".csv")) {
            arquivo = new File(arquivo.getAbsolutePath() + ".csv");
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(arquivo))) {
            pw.println("Tipo,Titulo,Autor,Ano,Unidades"); // cabeçalho obras
            for (Obra o : biblioteca.getObras()) {
                pw.printf("%s,%s,%s,%d,%d%n",
                        escape(o.getTipo()),
                        escape(o.getTitulo()),
                        escape(o.getAutor()),
                        o.getAno(),
                        o.getNumeroUnidades());
            }

            pw.println();
            pw.println("Usuário,CPF,Avaliação (Obra),Nota,Comentario");
            for (Usuario u : biblioteca.getUsuarios()) {
                Map<Obra, List<Avaliacao>> mapa = u.getAvaliacoes();
                for (Map.Entry<Obra, List<Avaliacao>> en : mapa.entrySet()) {
                    Obra ob = en.getKey();
                    for (Avaliacao a : en.getValue()) {
                        pw.printf("%s,%s,%s,%d,%s%n",
                                escape(u.getNome()),
                                escape(u.getCpf()),
                                escape(ob.getTitulo()),
                                a.getNota(),
                                escape(a.getComentario()));
                    }
                }
            }
            pw.flush();
            JOptionPane.showMessageDialog(parent, "Exportação concluída: " + arquivo.getAbsolutePath(), "Exportar CSV", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parent, "Erro ao exportar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String escape(String s) {
        if (s == null) return "";
        String r = s.replace("\"", "\"\"");
        if (r.contains(",") || r.contains("\"") || r.contains("\n")) {
            return "\"" + r + "\"";
        }
        return r;
    }
}
