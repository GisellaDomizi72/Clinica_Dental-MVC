package Principal;

import Controlador.Controlador;
import Vista.Menu;
import Vista.VistaUsuarios;
import Vista.VistaDentistas;
import Vista.VistaServicios;

public class Main {
    public static void main(String[] args) {
        // Instanciamos todas las vistas
        Menu menu = new Menu();
        VistaUsuarios vistaUsuarios = new VistaUsuarios();
        VistaDentistas vistaDentistas = new VistaDentistas();
        VistaServicios vistaServicios = new VistaServicios();

        // Creamos el controlador pasándole las vistas
        Controlador controlador = new Controlador(menu, vistaUsuarios, vistaDentistas, vistaServicios);

        // Hacemos visible el menú principal
        menu.setVisible(true);
    }
}
