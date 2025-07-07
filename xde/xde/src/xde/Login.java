
package xde;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {

    private final JTextField campoUsuarioLogin = new JTextField(15);
    private final JPasswordField campoClaveLogin = new JPasswordField(15);

    public Login() {
        super("Login");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 220);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Usuario:"), gbc);
        gbc.gridx = 1;
        panel.add(campoUsuarioLogin, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        panel.add(campoClaveLogin, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        JButton btnLogin = new JButton("Ingresar");
        panel.add(btnLogin, gbc);

        gbc.gridy = 3;
        JLabel lblCrearUsuario = new JLabel("Crear nuevo usuario");
        lblCrearUsuario.setForeground(new Color(0, 102, 204));
        lblCrearUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(lblCrearUsuario, gbc);

        setContentPane(panel);

        btnLogin.addActionListener(e -> validarLogin());
        lblCrearUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirCrearUsuario();
            }
        });
        setVisible(true);
    }

    private void validarLogin() {
        String usuario = campoUsuarioLogin.getText().trim();
        String clave = new String(campoClaveLogin.getPassword());

        if (usuario.equals("user01") && clave.equals("123456")) {
            JOptionPane.showMessageDialog(this, "Login exitoso. Bienvenido " + usuario + "!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            new MenuPrincipal().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirCrearUsuario() {
        JDialog dialog = new JDialog(this, "Crear Nuevo Usuario", true);
        dialog.setSize(350, 220);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Usuario:"), gbc);
        JTextField campoUsuarioNuevo = new JTextField(15);
        gbc.gridx = 1;
        panel.add(campoUsuarioNuevo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Contraseña:"), gbc);
        JPasswordField campoClaveNueva = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(campoClaveNueva, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        JButton btnCrear = new JButton("Crear Usuario");
        panel.add(btnCrear, gbc);

        dialog.setContentPane(panel);

        btnCrear.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialog, "El usuario ha sido registradoe exitosamente.", "Info", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
        });
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        SwingUtilities.invokeLater(Login::new);
    }
}