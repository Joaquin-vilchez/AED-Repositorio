package xde;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class SistemaLibros extends JFrame {

    private final ArrayList<Libro> libros = new ArrayList<>();
    private final DefaultTableModel modeloTabla;
    private final JTable tablaLibros;
    private final JTextField campoTitulo = new JTextField(20);
    private final JTextField campoAutor = new JTextField(20);
    private final JTextField campoAño = new JTextField(5);
    private final JTextField campoGenero = new JTextField(20); // Nuevo campo para género
    private final JFrame ventanaMenu;

    public SistemaLibros(JFrame menuPrincipal) {
        super("COFFE AND PEACE");
        this.ventanaMenu = menuPrincipal;

        setSize(600, 450); // Aumentar el tamaño de la ventana para acomodar el nuevo campo
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(panelPrincipal);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Agregar nuevo libro"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(new JLabel("Titulo:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(campoTitulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(new JLabel("Autor:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(campoAutor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulario.add(new JLabel("Año:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(campoAño, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3; // Nueva fila para el género
        panelFormulario.add(new JLabel("Genero:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(campoGenero, gbc);

        JButton btnAgregar = new JButton("Agregar Libro");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(btnAgregar, gbc);

        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new String[]{"Título", "Autor", "Año", "Género"}, 0) { // Añadir columna de género
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaLibros = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaLibros);
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        JButton btnEliminar = new JButton("Eliminar Libro Seleccionado");
        JButton btnVolver = new JButton("Volver al Menú");
        JButton btnFiltrarTitulo = new JButton("Filtrar por Título"); // Nuevo botón para filtrar por título
        panelInferior.add(btnEliminar);
        panelInferior.add(btnVolver);
        panelInferior.add(btnFiltrarTitulo);

        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> agregarLibro());
        btnEliminar.addActionListener(e -> eliminarLibro());
        btnVolver.addActionListener(e -> volverAlMenu());
        btnFiltrarTitulo.addActionListener(e -> filtrarPorTitulo()); // Añadir listener para el botón de filtrar
    }

    private void agregarLibro() {
        String titulo = campoTitulo.getText().trim();
        String autor = campoAutor.getText().trim();
        String añoStr = campoAño.getText().trim();
        String genero = campoGenero.getText().trim(); // Obtener el género

        if (titulo.isEmpty() || autor.isEmpty() || añoStr.isEmpty() || genero.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int año;
        try {
            año = Integer.parseInt(añoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Año debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Libro libro = new Libro(titulo, autor, año, genero);
        libros.add(libro);
        modeloTabla.addRow(new Object[]{libro.getTitulo(), libro.getAutor(), libro.getAño(), libro.getGenero()});
        HistorialLibros.agregarLibro(libro);

        campoTitulo.setText("");
        campoAutor.setText("");
        campoAño.setText("");
        campoGenero.setText("");
        campoTitulo.requestFocus();
    }

    private void eliminarLibro() {
        int filaSeleccionada = tablaLibros.getSelectedRow();
        if (filaSeleccionada != -1) {
            Libro libro = libros.get(filaSeleccionada);
            libros.remove(filaSeleccionada);
            modeloTabla.removeRow(filaSeleccionada);
            HistorialLibros.eliminarLibro(libro);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un libro para eliminar.", "ERROR!", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void volverAlMenu() {
        this.dispose();
        ventanaMenu.setVisible(true);
    }

    private void filtrarPorTitulo() {
        String opcion = JOptionPane.showInputDialog(this, "Selecciona el orden:\n1. A-Z\n2. Z-A");
        if (opcion != null && !opcion.trim().isEmpty()) {
            int orden;
            try {
                orden = Integer.parseInt(opcion);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Opcion invalida. Introduce 1 o 2.", "Opps!", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ArrayList<Libro> librosFiltrados = new ArrayList<>(libros);

            if (orden == 1) {
                librosFiltrados.sort(Comparator.comparing(Libro::getTitulo));
            } else if (orden == 2) {
                librosFiltrados.sort(Comparator.comparing(Libro::getTitulo).reversed());
            } else {
                JOptionPane.showMessageDialog(this, "Opción no válida. Introduce 1 o 2.", "ERROR!", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DefaultTableModel modeloFiltrado = new DefaultTableModel(new String[]{"Titulo", "Autor", "Año", "Genero"}, 0);
            for (Libro libro : librosFiltrados) {
                modeloFiltrado.addRow(new Object[]{libro.getTitulo(), libro.getAutor(), libro.getAño(), libro.getGenero()});
            }
            tablaLibros.setModel(modeloFiltrado);
        } else {
            JOptionPane.showMessageDialog(this, "Opcion invalida.", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
