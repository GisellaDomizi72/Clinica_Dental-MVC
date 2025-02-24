package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.*;

public class ControladorPrincipal implements ActionListener {
    private final Menu menu;
    private final ControladorUsuarios controladorUsuarios;
    private final ControladorDentistas controladorDentistas;
    private final ControladorServicios controladorServicios;
    private final ControladorTurnos controladorTurnos;
    private final VistaUsuarios vistaUsuarios;
    private final VistaDentistas vistaDentistas;
    private final VistaServicios vistaServicios;
    private final VistaTurnos vistaTurnos;
    

    public ControladorPrincipal(Menu m, VistaUsuarios vu, VistaDentistas vd, VistaServicios vs, VistaTurnos vt) {
        this.menu = m;
        this.vistaUsuarios = vu;
        this.vistaDentistas = vd;
        this.vistaServicios = vs;
        this.vistaTurnos = vt;
        
        // Inicializar los controladores 
        this.controladorUsuarios = new ControladorUsuarios(vistaUsuarios);
        this.controladorDentistas = new ControladorDentistas(vistaDentistas);
        this.controladorServicios = new ControladorServicios(vistaServicios);
        this.controladorTurnos = new ControladorTurnos(vistaTurnos);
        
        asignarEventos();
    }
    
    private void asignarEventos() {
        // Eventos del menú
        menu.btnmGU.addActionListener(this);
        menu.btnmGD.addActionListener(this);
        menu.btnmGS.addActionListener(this);
        menu.btnmGT.addActionListener(this);
        
        // Asignar evento de "volver" a los botones correspondientes
        vistaUsuarios.btnuVolver.addActionListener(this);
        vistaDentistas.btndVolver.addActionListener(this);
        vistaServicios.btnsVolver.addActionListener(this);
        vistaTurnos.btntVolver.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // Menú principal
        if (source == menu.btnmGU) {
            menu.setVisible(false);
            vistaUsuarios.setVisible(true);
        }
        if (source == menu.btnmGD) {
            menu.setVisible(false);
            vistaDentistas.setVisible(true);
        }
        if (source == menu.btnmGS) {
            menu.setVisible(false);
            vistaServicios.setVisible(true);
        }
        if (source == menu.btnmGT) {
            menu.setVisible(false);
            vistaTurnos.setVisible(true);
        }
        
        // Botón "Volver" en las vistas
        if (source == vistaUsuarios.btnuVolver || source == vistaDentistas.btndVolver || source == vistaServicios.btnsVolver || source == vistaTurnos.btntVolver) {
            // Ocultar las vistas y mostrar el menú
            vistaUsuarios.setVisible(false);
            vistaDentistas.setVisible(false);
            vistaServicios.setVisible(false);
            vistaTurnos.setVisible(false);
            menu.setVisible(true);
        }
    }   
}
