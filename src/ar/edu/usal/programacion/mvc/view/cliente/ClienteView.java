package ar.edu.usal.programacion.mvc.view.cliente;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteView {

    private JFrame frame;
    private JPanel panel;

    public ClienteView() {
        initialize();
    }
    public void mostrar() {
        frame.setVisible(true);
    }
    private void initialize() {
        // Crear la ventana del menú de clientes
        frame = new JFrame("Menú de Clientes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Crear el panel principal
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear el botón de crear cliente
        JButton btnCrearCliente = new JButton("Crear Cliente");
        btnCrearCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para abrir la ventana de creación de cliente
                CrearClienteView crearClienteView = new CrearClienteView();
                crearClienteView.mostrar();
            }
        });

        // Agregar el botón de crear cliente al panel
        panel.add(btnCrearCliente, BorderLayout.CENTER);

        // Agregar el panel a la ventana
        frame.getContentPane().add(panel);

        // Mostrar la ventana
        frame.setVisible(true);
    }


}
