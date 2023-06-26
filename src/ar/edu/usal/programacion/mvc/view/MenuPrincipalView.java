package ar.edu.usal.programacion.mvc.view;

import ar.edu.usal.programacion.dao.impl.ClienteDAOImpl;
import ar.edu.usal.programacion.dao.impl.VendedorDAOImpl;
import ar.edu.usal.programacion.domain.Cliente;
import ar.edu.usal.programacion.domain.Vendedor;
import ar.edu.usal.programacion.mvc.view.articulo.CrearArticuloView;
import ar.edu.usal.programacion.mvc.view.articulo.ConsultarArticulosView;
import ar.edu.usal.programacion.mvc.view.cliente.CrearClienteView;
import ar.edu.usal.programacion.mvc.view.vendedor.MenuVendedor;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class MenuPrincipalView {

    private JFrame frame;
    private JPanel panel;

    public MenuPrincipalView() {
        initialize();
    }

    private void initialize() {
        // Crear la ventana principal
        frame = new JFrame("Menú Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Crear el panel principal
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        // Crear los botones del menú
        JButton btnLogin = new JButton("Login");
        JButton btnConsultarArticulos = new JButton("Consultar Artículos");
        JButton btnRegistrarse = new JButton("Registrarse");

        // Agregar los botones al panel
        panel.add(btnLogin);
        panel.add(btnConsultarArticulos);
        panel.add(btnRegistrarse);

        // Agregar el panel a la ventana
        frame.getContentPane().add(panel);

        // Agregar ActionListener a los botones
        btnLogin.addActionListener(e -> {
            // Acción para el botón Login
            mostrarOpcionTipoCliente();
        });

        btnConsultarArticulos.addActionListener(e -> {
            // Acción para el botón Consultar Artículos
            ConsultarArticulosView consultarArticulosView;
            try {
                consultarArticulosView = new ConsultarArticulosView();
                consultarArticulosView.mostrar();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

        btnRegistrarse.addActionListener(e -> {
            // Acción para el botón Registrarse
            mostrarOpcionRegistrarse();
        });
    }

    private void mostrarOpcionTipoCliente() {
        // Crear el diálogo de opción de tipo de cliente
        JDialog tipoClienteDialog = new JDialog(frame, "Tipo de Cliente", true);
        tipoClienteDialog.setSize(300, 200);
        tipoClienteDialog.setLayout(new GridLayout(2, 1));

        // Crear los botones de opción
        JButton btnCliente = new JButton("Persona Fisica");
        JButton btnVendedor = new JButton("Empresa");

        // Agregar los botones al diálogo
        tipoClienteDialog.add(btnCliente);
        tipoClienteDialog.add(btnVendedor);

        // Configurar el botón Cliente
        btnCliente.addActionListener(e -> {
            // Acción para el botón Persona Fisica
            mostrarDialogoLoginCliente();
            tipoClienteDialog.dispose();
        });

        // Configurar el botón Vendedor
        btnVendedor.addActionListener(e -> {
            // Acción para el botón Empresa
            mostrarDialogoLoginVendedor();
            tipoClienteDialog.dispose();
        });

        // Mostrar el diálogo de opción de tipo de cliente
        tipoClienteDialog.setVisible(true);
    }

    private void mostrarDialogoLoginCliente() {
        // Crear el diálogo de login del cliente
        JDialog loginClienteDialog = new JDialog(frame, "Login Cliente", true);
        loginClienteDialog.setSize(300, 200);
        loginClienteDialog.setLayout(new GridLayout(3, 2));

        // Crear los campos de texto
        JLabel lblUsuario = new JLabel("Usuario:");
        JTextField txtUsuario = new JTextField();
        JLabel lblContraseña = new JLabel("Contraseña:");
        JPasswordField txtContraseña = new JPasswordField();
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

        // Agregar los componentes al diálogo
        loginClienteDialog.add(lblUsuario);
        loginClienteDialog.add(txtUsuario);
        loginClienteDialog.add(lblContraseña);
        loginClienteDialog.add(txtContraseña);
        loginClienteDialog.add(btnAceptar);
        loginClienteDialog.add(btnCancelar);

        // Configurar el botón Aceptar
        btnAceptar.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String contraseña = new String(txtContraseña.getPassword());

            // Obtener todos los clientes
            ClienteDAOImpl clienteDAO = new ClienteDAOImpl();
            List<Cliente> clientes = clienteDAO.obtenerTodos();

            // Validar las credenciales del cliente
            Cliente cliente = validarCredencialesCliente(clientes, usuario, contraseña);
            if (cliente != null) {
                try {
                    MenuPrincipalLoginView menuPrincipalLoginView = new MenuPrincipalLoginView();
                    menuPrincipalLoginView.mostrar();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                loginClienteDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(loginClienteDialog, "Credenciales incorrectas");
            }
        });

        // Configurar el botón Cancelar
        btnCancelar.addActionListener(e -> loginClienteDialog.dispose());

        // Mostrar el diálogo de login del cliente
        loginClienteDialog.setVisible(true);
    }

    private Cliente validarCredencialesCliente(List<Cliente> clientes, String usuario, String contraseña) {

       /*
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(usuario) && cliente.getDni().equals(contraseña)) {
                return cliente;
            }
        }
         return null;
        */
        return new Cliente("admin", "Admin", "admin123", null, null);
    }

    private void mostrarDialogoLoginVendedor() {
        // Crear el diálogo de login del vendedor
        JDialog loginVendedorDialog = new JDialog(frame, "Login Vendedor", true);
        loginVendedorDialog.setSize(300, 200);
        loginVendedorDialog.setLayout(new GridLayout(3, 2));

        // Crear los campos de texto
        JLabel lblUsuario = new JLabel("Usuario:");
        JTextField txtUsuario = new JTextField();
        JLabel lblContraseña = new JLabel("Contraseña:");
        JPasswordField txtContraseña = new JPasswordField();
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

        // Agregar los componentes al diálogo
        loginVendedorDialog.add(lblUsuario);
        loginVendedorDialog.add(txtUsuario);
        loginVendedorDialog.add(lblContraseña);
        loginVendedorDialog.add(txtContraseña);
        loginVendedorDialog.add(btnAceptar);
        loginVendedorDialog.add(btnCancelar);

        // Configurar el botón Aceptar
        btnAceptar.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String contraseña = new String(txtContraseña.getPassword());

            // Obtener todos los vendedores
            VendedorDAOImpl vendedorDAO = new VendedorDAOImpl();
            List<Vendedor> vendedores = vendedorDAO.obtenerTodosLosVendedores();

            // Validar las credenciales del vendedor
            Vendedor vendedor = validarCredencialesVendedor(vendedores, usuario, contraseña);
            if (vendedor != null) {
                // Código a ejecutar si las credenciales son válidas para un vendedor
                MenuVendedor menuVendedor = new MenuVendedor();
                CrearArticuloView articuloView = new CrearArticuloView();
                articuloView.mostrar();

          //      menuVendedor.mostrar();
                loginVendedorDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(loginVendedorDialog, "Credenciales incorrectas");
            }
        });

        // Configurar el botón Cancelar
        btnCancelar.addActionListener(e -> loginVendedorDialog.dispose());

        // Mostrar el diálogo de login del vendedor
        loginVendedorDialog.setVisible(true);
    }

    private Vendedor validarCredencialesVendedor(List<Vendedor> vendedores, String usuario, String contraseña) {

    /*
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getRazonSocial().equals(usuario) && vendedor.getCuit() == Integer.parseInt(contraseña)) {
                return vendedor;
            }
        }

     */
        return new Vendedor("3031233123","Asdasd",12345);
    }

    private void mostrarOpcionRegistrarse() {
        // Crear el diálogo de registro
        JDialog registroDialog = new JDialog(frame, "Registrarse", true);
        registroDialog.setSize(300, 200);
        registroDialog.setLayout(new GridLayout(3, 1));

        // Crear los botones de opción
        JButton btnCliente = new JButton("Persona Fisica");
        JButton btnVendedor = new JButton("Empresa");

        // Agregar los botones al diálogo
        registroDialog.add(btnCliente);
        registroDialog.add(btnVendedor);

        // Configurar el botón Cliente
        btnCliente.addActionListener(e -> {
            CrearClienteView crearClienteView = new CrearClienteView();
            crearClienteView.mostrar();
            registroDialog.dispose();
        });

        // Configurar el botón Vendedor
        btnVendedor.addActionListener(e -> {
            MenuVendedor crearVendedor = new MenuVendedor();
            crearVendedor.mostrar();
            registroDialog.dispose();
        });

        // Mostrar el diálogo de registro
        registroDialog.setVisible(true);
    }

    public void mostrar() {
        // Mostrar la ventana
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipalView().mostrar());
    }
}
