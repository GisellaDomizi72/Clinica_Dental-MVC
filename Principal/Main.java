package Principal;

import Controlador.ControladorPrincipal;
import Vista.*;

public class Main {
    public static void main(String[] args) {
        // Instanciamos todas las vistas
        Menu menu = new Menu();
        VistaUsuarios vistaUsuarios = new VistaUsuarios();
        VistaDentistas vistaDentistas = new VistaDentistas();
        VistaServicios vistaServicios = new VistaServicios();
        VistaTurnos vistaTurnos = new VistaTurnos();

        // Creamos el controlador pasándole las vistas
        ControladorPrincipal controlador = new ControladorPrincipal(menu, vistaUsuarios, vistaDentistas, vistaServicios, vistaTurnos);

        // Hacemos visible el menú principal
        menu.setVisible(true);
    }
}
