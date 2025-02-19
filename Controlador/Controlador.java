package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Controlador implements ActionListener {
    private final Menu menu;
    private final VistaUsuarios vistaUsuarios;
    private final VistaDentistas vistaDentistas;
    private final VistaServicios vistaServicios;

    private final UsuarioDAO usuariodao = new UsuarioDAO();
    private final DentistaDAO dentistadao = new DentistaDAO();
    private final ServicioDAO serviciodao = new ServicioDAO();

    private DefaultTableModel modeloU, modeloD, modeloS;

    // Constructor
    public Controlador(Menu m, VistaUsuarios vu, VistaDentistas vd, VistaServicios vs) {
        this.menu = m;
        this.vistaUsuarios = vu;
        this.vistaDentistas = vd;
        this.vistaServicios = vs;

        asignarEventos();
    }

    private void asignarEventos() {
        // Eventos del menú
        menu.btnmGU.addActionListener(this);
        menu.btnmGD.addActionListener(this);
        menu.btnmGS.addActionListener(this);

        // Eventos de la vista de Usuarios
        vistaUsuarios.btnuListar.addActionListener(this);
        vistaUsuarios.btnuGuardar.addActionListener(this);
        vistaUsuarios.btnuEditar.addActionListener(this);
        vistaUsuarios.btnuEliminar.addActionListener(this);
        vistaUsuarios.btnuOk.addActionListener(this);
        vistaUsuarios.btnuVolver.addActionListener(this);

        // Eventos de la vista de Dentistas
        vistaDentistas.btndListar.addActionListener(this);
        vistaDentistas.btndGuardar.addActionListener(this);
        vistaDentistas.btndEditar.addActionListener(this);
        vistaDentistas.btndEliminar.addActionListener(this);
        vistaDentistas.btndOk.addActionListener(this);
        vistaDentistas.btndVolver.addActionListener(this);

        // Eventos de la vista de Servicios
        vistaServicios.btnsListar.addActionListener(this);
        vistaServicios.btnsGuardar.addActionListener(this);
        vistaServicios.btnsEditar.addActionListener(this);
        vistaServicios.btnsEliminar.addActionListener(this);
        vistaServicios.btnsOk.addActionListener(this);
        vistaServicios.btnsVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // Menú principal
        if (source == menu.btnmGU) mostrarVista(vistaUsuarios);
        if (source == menu.btnmGD) mostrarVista(vistaDentistas);
        if (source == menu.btnmGS) mostrarVista(vistaServicios);

        // Usuarios
        if (source == vistaUsuarios.btnuListar) listarUsuarios();
        if (source == vistaUsuarios.btnuGuardar) agregarUsuario();
        if (source == vistaUsuarios.btnuEditar) cargarDatosUsuario();
        if (source == vistaUsuarios.btnuOk) actualizarUsuario();
        if (source == vistaUsuarios.btnuEliminar) eliminarUsuario();
        if (source == vistaUsuarios.btnuVolver) ocultarVista(vistaUsuarios);

        // Dentistas
        if (source == vistaDentistas.btndListar) listarDentistas();
        if (source == vistaDentistas.btndGuardar) agregarDentista();
        if (source == vistaDentistas.btndEditar) cargarDatosDentista();
        if (source == vistaDentistas.btndOk) actualizarDentista();
        if (source == vistaDentistas.btndEliminar) eliminarDentista();
        if (source == vistaDentistas.btndVolver) ocultarVista(vistaDentistas);

        // Servicios
        if (source == vistaServicios.btnsListar) listarServicios();
        if (source == vistaServicios.btnsGuardar) agregarServicio();
        if (source == vistaServicios.btnsEditar) cargarDatosServicio();
        if (source == vistaServicios.btnsOk) actualizarServicio();
        if (source == vistaServicios.btnsEliminar) eliminarServicio();
        if (source == vistaServicios.btnsVolver) ocultarVista(vistaServicios);
    }

    // Métodos para manejar las vistas
    private void mostrarVista(javax.swing.JFrame vista) {
        vista.setVisible(true);
    }

    private void ocultarVista(javax.swing.JFrame vista) {
        vista.setVisible(false);
        menu.setVisible(true);
    }

    // ---------------- MÉTODOS PARA USUARIOS ----------------
    private void listarUsuarios() {
        modeloU = (DefaultTableModel) vistaUsuarios.tablau.getModel();
        modeloU.setRowCount(0);
        for (Usuario u : usuariodao.listarUsuarios()) {
            modeloU.addRow(new Object[]{u.getId_usuario(), u.getNombre(), u.getContrasena(), u.getRol()});
        }
    }

    private void agregarUsuario() {
        Usuario u = new Usuario();
        u.setNombre(vistaUsuarios.txtusuario.getText());
        u.setContrasena(vistaUsuarios.txtcontrasena.getText());
        if (usuariodao.agregar(u) == 1) {
            JOptionPane.showMessageDialog(vistaUsuarios, "Usuario agregado con éxito!");
            listarUsuarios();
        } else {
            JOptionPane.showMessageDialog(vistaUsuarios, "Error al agregar usuario.");
        }
    }

    private void cargarDatosUsuario() {
        int fila = vistaUsuarios.tablau.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaUsuarios, "Debe seleccionar un registro.");
            return;
        }
        vistaUsuarios.txtuIdu.setText(vistaUsuarios.tablau.getValueAt(fila, 0).toString());
        vistaUsuarios.txtusuario.setText(vistaUsuarios.tablau.getValueAt(fila, 1).toString());
        vistaUsuarios.txtcontrasena.setText(vistaUsuarios.tablau.getValueAt(fila, 2).toString());
        vistaUsuarios.txtuRol.setText(vistaUsuarios.tablau.getValueAt(fila, 3).toString());
    }

    private void actualizarUsuario() {
        Usuario u = new Usuario();
        u.setId_usuario(Integer.parseInt(vistaUsuarios.txtuIdu.getText()));
        u.setNombre(vistaUsuarios.txtusuario.getText());
        u.setContrasena(vistaUsuarios.txtcontrasena.getText());
        u.setRol(Integer.parseInt(vistaUsuarios.txtuRol.getText()));

        if (usuariodao.actualizar(u) == 1) {
            JOptionPane.showMessageDialog(vistaUsuarios, "Usuario actualizado con éxito!");
            listarUsuarios();
        } else {
            JOptionPane.showMessageDialog(vistaUsuarios, "Error al actualizar el usuario.");
        }
    }

    private void eliminarUsuario() {
        int fila = vistaUsuarios.tablau.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaUsuarios, "Debe seleccionar un registro.");
            return;
        }
        int id_usuario = Integer.parseInt(vistaUsuarios.tablau.getValueAt(fila, 0).toString());
        if (usuariodao.eliminarUsuario(id_usuario)) {
            JOptionPane.showMessageDialog(vistaUsuarios, "Usuario eliminado.");
            listarUsuarios();
        } else {
            JOptionPane.showMessageDialog(vistaUsuarios, "Error al eliminar usuario.");
        }
    }

    // ---------------- MÉTODOS PARA DENTISTAS ----------------
     private void listarDentistas() {
        modeloD = (DefaultTableModel) vistaDentistas.tablad.getModel();
        modeloD.setRowCount(0);
        for (Dentista d : dentistadao.listarDentistas()) {
            modeloD.addRow(new Object[]{d.getId_dentista(),d.getNombre(), d.getApellido() , d.getTelefono(), d.getEmail(), d.getFoto(), d.getExp(), d.getId_usuario()});
        }
    }

    private void agregarDentista() {
        // Validar que los campos no estén vacíos
        if (vistaDentistas.txtdNombre.getText().trim().isEmpty() ||
            vistaDentistas.txtdApellido.getText().trim().isEmpty() ||
            vistaDentistas.txtdTelefono.getText().trim().isEmpty() ||
            vistaDentistas.txtdEmail.getText().trim().isEmpty() ||
            vistaDentistas.txtdFoto.getText().trim().isEmpty() ||
            vistaDentistas.txtdExperiencia.getText().trim().isEmpty() ||
            vistaDentistas.txtdIdu.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(vistaDentistas, "Todos los campos son obligatorios.");
            return;
        }
        Dentista d = new Dentista();
        d.setNombre(vistaDentistas.txtdNombre.getText());
        d.setApellido(vistaDentistas.txtdApellido.getText());
        d.setTelefono(vistaDentistas.txtdTelefono.getText());
        d.setEmail(vistaDentistas.txtdEmail.getText());
        d.setFoto(vistaDentistas.txtdFoto.getText());
        d.setExp(vistaDentistas.txtdExperiencia.getText());
        // Convertir el valor de txtdIdu (String) a int
        try {
            int id_usuario = Integer.parseInt(vistaDentistas.txtdIdu.getText());
            d.setId_usuario(id_usuario);
        } catch (NumberFormatException e) {
            // Si no es un número válido, mostrar un mensaje de error
            JOptionPane.showMessageDialog(vistaDentistas, "El ID de dentista debe ser un número válido.");
            return;  // Salir del método si hay un error en la conversión
        }
        // Insertar en la base de datos
        if (dentistadao.agregar(d) == 1) {
            JOptionPane.showMessageDialog(vistaDentistas, "Dentista agregado con éxito!");
            listarDentistas();
        } else {
            JOptionPane.showMessageDialog(vistaDentistas, "Error al agregar dentista.");
        }
    }

    private void cargarDatosDentista() {
        int fila = vistaDentistas.tablad.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaDentistas, "Debe seleccionar un registro.");
            return;
        }
        vistaDentistas.txtdIdd.setText(vistaDentistas.tablad.getValueAt(fila, 0).toString());
        vistaDentistas.txtdNombre.setText(vistaDentistas.tablad.getValueAt(fila, 1).toString());
        vistaDentistas.txtdApellido.setText(vistaDentistas.tablad.getValueAt(fila, 2).toString());
        vistaDentistas.txtdTelefono.setText(vistaDentistas.tablad.getValueAt(fila, 3).toString());
        vistaDentistas.txtdEmail.setText(vistaDentistas.tablad.getValueAt(fila, 4).toString());
        vistaDentistas.txtdFoto.setText(vistaDentistas.tablad.getValueAt(fila, 5).toString());
        vistaDentistas.txtdExperiencia.setText(vistaDentistas.tablad.getValueAt(fila, 6).toString());
        vistaDentistas.txtdIdu.setText(vistaDentistas.tablad.getValueAt(fila, 7).toString());
        
    }

    private void actualizarDentista() {
        Dentista d = new Dentista();
        d.setId_dentista(Integer.parseInt(vistaDentistas.txtdIdd.getText()));
        d.setNombre(vistaDentistas.txtdNombre.getText());
        d.setApellido(vistaDentistas.txtdApellido.getText());
        d.setTelefono(vistaDentistas.txtdTelefono.getText());
        d.setEmail(vistaDentistas.txtdEmail.getText());
        d.setFoto(vistaDentistas.txtdFoto.getText());
        d.setExp(vistaDentistas.txtdExperiencia.getText());
        d.setId_usuario(Integer.parseInt(vistaDentistas.txtdIdu.getText()));

        if (dentistadao.actualizar(d) == 1) {
            JOptionPane.showMessageDialog(vistaDentistas, "Dentista actualizado con éxito!");
            listarDentistas();
        } else {
            JOptionPane.showMessageDialog(vistaDentistas, "Error al actualizar el dentista.");
        }
    }

    private void eliminarDentista() {
        int fila = vistaDentistas.tablad.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaDentistas, "Debe seleccionar un registro.");
            return;
        }
        int id_dentista = Integer.parseInt(vistaDentistas.tablad.getValueAt(fila, 0).toString());
        if (dentistadao.eliminarDentista(id_dentista)) {
            JOptionPane.showMessageDialog(vistaDentistas, "Dentista eliminado.");
            listarDentistas();
        } else {
            JOptionPane.showMessageDialog(vistaDentistas, "Error al eliminar dentista.");
        }
    }
    
    // ---------------- MÉTODOS PARA DSERVICIOS ----------------
     private void listarServicios() {
        modeloS = (DefaultTableModel) vistaServicios.tablas.getModel();
        modeloS.setRowCount(0);
        for (Servicio s : serviciodao.listarServicios()) {
            modeloS.addRow(new Object[]{s.getId_servicio(),s.getNombre(),s.getPrecio(),s.getDescripcion(),s.getId_dentista()});
        }
    }

    private void agregarServicio() {
        // Validar que los campos no estén vacíos
        if (vistaServicios.txtsNombre.getText().trim().isEmpty() ||
            vistaServicios.txtsDescripcion.getText().trim().isEmpty() ||
            vistaServicios.txtsPrecio.getText().trim().isEmpty() ||
            vistaServicios.txtsIddentista.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(vistaServicios, "Todos los campos son obligatorios.");
            return;
        }
        
        Servicio s = new Servicio();
        s.setNombre(vistaServicios.txtsNombre.getText());
        s.setDescripcion(vistaServicios.txtsDescripcion.getText());
        
        // Convertir el precio y el id_dentista a enteros
        try {
            int precio_s = Integer.parseInt(vistaServicios.txtsPrecio.getText());
            if (precio_s < 0) {
                JOptionPane.showMessageDialog(vistaServicios, "El precio no puede ser negativo.");
                return;
            }
            s.setPrecio(precio_s);

            int id_dentista = Integer.parseInt(vistaServicios.txtsIddentista.getText());
            if (id_dentista <= 0) {
                JOptionPane.showMessageDialog(vistaServicios, "El ID del dentista debe ser un número positivo.");
                return;
            }
            s.setId_dentista(id_dentista);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaServicios, "El Precio o el ID del dentista debe ser un número válido.");
            return;  // Salir del método si hay un error en la conversión
        }
        
        // Insertar en la base de datos
        if (serviciodao.agregar(s) == 1) {
            JOptionPane.showMessageDialog(vistaServicios, "Servicio agregado con éxito!");
            listarServicios();
        } else {
            JOptionPane.showMessageDialog(vistaServicios, "Error al agregar servicio.");
        }
    }

    private void cargarDatosServicio() {
        int fila = vistaServicios.tablas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaServicios, "Debe seleccionar un registro.");
            return;
        }
        vistaServicios.txtsIds.setText(vistaServicios.tablas.getValueAt(fila, 0).toString());
        vistaServicios.txtsNombre.setText(vistaServicios.tablas.getValueAt(fila, 1).toString());
        vistaServicios.txtsPrecio.setText(vistaServicios.tablas.getValueAt(fila, 2).toString());
        vistaServicios.txtsDescripcion.setText(vistaServicios.tablas.getValueAt(fila, 3).toString());
        vistaServicios.txtsIddentista.setText(vistaServicios.tablas.getValueAt(fila, 4).toString());
        
    }

    private void actualizarServicio() {
        Servicio s = new Servicio();
        s.setId_servicio(Integer.parseInt(vistaServicios.txtsIds.getText()));
        s.setNombre(vistaServicios.txtsNombre.getText());
        s.setPrecio(Integer.parseInt(vistaServicios.txtsPrecio.getText()));
        s.setDescripcion(vistaServicios.txtsDescripcion.getText());
        s.setId_dentista(Integer.parseInt(vistaServicios.txtsIddentista.getText()));
        
        if (serviciodao.actualizar(s) == 1) {
            JOptionPane.showMessageDialog(vistaServicios, "Servicio actualizado con éxito!");
            listarServicios();
        } else {
            JOptionPane.showMessageDialog(vistaServicios, "Error al actualizar el servicio.");
        }
    }

    private void eliminarServicio() {
        int fila = vistaServicios.tablas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaServicios, "Debe seleccionar un registro.");
            return;
        }
        int id_servicio = Integer.parseInt(vistaServicios.tablas.getValueAt(fila, 0).toString());
        if (serviciodao.eliminarServicio(id_servicio)){
            JOptionPane.showMessageDialog(vistaServicios, "Servicio eliminado.");
            listarServicios();
        } else {
            JOptionPane.showMessageDialog(vistaServicios, "Error al eliminar servicio.");
        }
    }
}