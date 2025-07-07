package xde;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        super("MENU PRINCIPAL");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel titulo = new JLabel("COFFE AND PEACE");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        panel.add(titulo);

        JButton btnAgregar = crearBoton("Agregar libro");
        JButton btnHistorialAgregados = crearBoton("Historial de libros agregados");
        JButton btnHistorialEliminados = crearBoton("Historial de libros eliminados");
        JButton btnCerrarSesion = crearBoton("Cerrar sesion");

        panel.add(btnAgregar);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnHistorialAgregados);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnHistorialEliminados);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnCerrarSesion);

        btnAgregar.addActionListener(e -> {
            SistemaLibros sistema = new SistemaLibros(this);
            sistema.setVisible(true);
            setVisible(false);
        });

        btnHistorialAgregados.addActionListener(e -> HistorialLibros.mostrarHistorial(HistorialLibros.getAgregados(), "Historial de libros agregados"));
        btnHistorialEliminados.addActionListener(e -> HistorialLibros.mostrarHistorial(HistorialLibros.getEliminados(), "Historial de libros eliminados"));
        btnCerrarSesion.addActionListener(e -> {
            new Login().setVisible(true);
            dispose();
        });

        getContentPane().add(panel);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setMaximumSize(new Dimension(280, 40));
        boton.setFocusPainted(false);
        boton.setBackground(Color.BLACK);
        boton.setForeground(Color.RED);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }
}
