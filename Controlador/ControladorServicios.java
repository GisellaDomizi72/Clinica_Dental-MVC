package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorServicios implements ActionListener{
    private final VistaServicios vistaServicios;
    private final ServicioDAO serviciodao = new ServicioDAO();
    private DefaultTableModel modeloS;

    // Constructor
    public ControladorServicios(VistaServicios vs) {
        this.vistaServicios = vs;

        asignarEventos();
    }
    
    private void asignarEventos() {
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
        // Servicios
        if (source == vistaServicios.btnsListar) listarServicios();
        if (source == vistaServicios.btnsGuardar) agregarServicio();
        if (source == vistaServicios.btnsEditar) cargarDatosServicio();
        if (source == vistaServicios.btnsOk) actualizarServicio();
        if (source == vistaServicios.btnsEliminar) eliminarServicio();
        if (source == vistaServicios.btnsVolver){
            vistaServicios.setVisible(false);
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
