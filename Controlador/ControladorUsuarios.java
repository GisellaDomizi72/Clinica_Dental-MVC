package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorUsuarios implements ActionListener{
    private final VistaUsuarios vistaUsuarios;
    private final UsuarioDAO usuariodao = new UsuarioDAO();
    private DefaultTableModel modeloU;
    
    // Constructor
    public ControladorUsuarios(VistaUsuarios vu) {
        this.vistaUsuarios = vu;
        asignarEventos();
    }
    
    private void asignarEventos(){
        // Eventos de la vista de Usuarios
        vistaUsuarios.btnuListar.addActionListener(this);
        vistaUsuarios.btnuGuardar.addActionListener(this);
        vistaUsuarios.btnuEditar.addActionListener(this);
        vistaUsuarios.btnuEliminar.addActionListener(this);
        vistaUsuarios.btnuOk.addActionListener(this);
        vistaUsuarios.btnuVolver.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       Object source = e.getSource();
       
       // Usuarios
        if (source == vistaUsuarios.btnuListar) listarUsuarios();
        if (source == vistaUsuarios.btnuGuardar) agregarUsuario();
        if (source == vistaUsuarios.btnuEditar) cargarDatosUsuario();
        if (source == vistaUsuarios.btnuOk) actualizarUsuario();
        if (source == vistaUsuarios.btnuEliminar) eliminarUsuario();
        if (source == vistaUsuarios.btnuVolver) {
            vistaUsuarios.setVisible(false);
        }
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
    
}
