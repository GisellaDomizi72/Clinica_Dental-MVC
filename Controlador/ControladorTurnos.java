package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorTurnos implements ActionListener{
    private final VistaTurnos vistaTurnos;
    private final TurnoDAO turnodao = new TurnoDAO();
    private DefaultTableModel modeloT;

    public ControladorTurnos(VistaTurnos vt) {
        this.vistaTurnos = vt;
        asignarEventos();
    }
    
    private void asignarEventos(){
        // Eventos de la vista de Turnos
        vistaTurnos.btntListar.addActionListener(this);
        vistaTurnos.btntEliminar.addActionListener(this);
        vistaTurnos.btntVolver.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       Object source = e.getSource();
       
       //Turnos
       if (source == vistaTurnos.btntListar) listarTurnos();
       if (source == vistaTurnos.btntEliminar) eliminarTurno();
        if (source == vistaTurnos.btntVolver) {
            vistaTurnos.setVisible(false);
        }
    }
    
    // ---------------- MÉTODOS PARA TURNOS ----------------
    private void listarTurnos() {
        modeloT = (DefaultTableModel) vistaTurnos.tablat.getModel();
        modeloT.setRowCount(0);
        for (Turno t : turnodao.listarTurno()) {
            modeloT.addRow(new Object[]{t.getId_turno(), t.getHora(), t.getFecha(), t.getNombre_paciente(), t.getNombre_servicio()});
        }
    }
    
    private void eliminarTurno() {
        int fila = vistaTurnos.tablat.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaTurnos, "Debe seleccionar un registro.");
            return;
        }
        int id_turno = Integer.parseInt(vistaTurnos.tablat.getValueAt(fila, 0).toString());
        if (turnodao.eliminarTurno(id_turno)) {
            JOptionPane.showMessageDialog(vistaTurnos, "Turno eliminado.");
            listarTurnos();
        } else {
            JOptionPane.showMessageDialog(vistaTurnos, "Error al eliminar Turno.");
        }
    }
}
