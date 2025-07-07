
package xde;

import java.util.ArrayList;
import javax.swing.*;

public class HistorialLibros {
    private static final ArrayList<Libro> agregados = new ArrayList<>();
    private static final ArrayList<Libro> eliminados = new ArrayList<>();

    public static void agregarLibro(Libro libro) {
        agregados.add(libro);
    }

    public static void eliminarLibro(Libro libro) {
        eliminados.add(libro);
    }

    public static ArrayList<Libro> getAgregados() {
        return agregados;
    }

    public static ArrayList<Libro> getEliminados() {
        return eliminados;
    }

    public static void mostrarHistorial(ArrayList<Libro> lista, String titulo) {
        StringBuilder contenido = new StringBuilder();
        if (lista.isEmpty()) {
            contenido.append("No hay libros en el historial.");
        } else {
            for (Libro libro : lista) {
                contenido.append("- ").append(libro.getTitulo())
                         .append(" | ").append(libro.getAutor())
                         .append(" | ").append(libro.getAño()).append("\n");
            }
        }
        JTextArea areaTexto = new JTextArea(contenido.toString());
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setPreferredSize(new java.awt.Dimension(350, 200));
        JOptionPane.showMessageDialog(null, scroll, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}