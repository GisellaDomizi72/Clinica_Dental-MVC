package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorDentistas implements ActionListener{
    private final VistaDentistas vistaDentistas;
    private final DentistaDAO dentistadao = new DentistaDAO();
    private DefaultTableModel modeloD;
    
    // Constructor
    public ControladorDentistas(VistaDentistas vd) {
        this.vistaDentistas = vd;

        asignarEventos();
    }
    
    private void asignarEventos() {
        // Eventos de la vista de Dentistas
        vistaDentistas.btndListar.addActionListener(this);
        vistaDentistas.btndGuardar.addActionListener(this);
        vistaDentistas.btndEditar.addActionListener(this);
        vistaDentistas.btndEliminar.addActionListener(this);
        vistaDentistas.btndOk.addActionListener(this);
        vistaDentistas.btndVolver.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        // Dentistas
        if (source == vistaDentistas.btndListar) listarDentistas();
        if (source == vistaDentistas.btndGuardar) agregarDentista();
        if (source == vistaDentistas.btndEditar) cargarDatosDentista();
        if (source == vistaDentistas.btndOk) actualizarDentista();
        if (source == vistaDentistas.btndEliminar) eliminarDentista();
        if (source == vistaDentistas.btndVolver) {
            vistaDentistas.setVisible(false);
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
}
