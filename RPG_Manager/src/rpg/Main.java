package rpg;

import rpg.controller.RpgController;
import rpg.model.PersonajeDAO;
import rpg.view.RpgView;

public class Main {
    public static void main(String[] args) {
        // Se instancian los componentes de la arquitectura
        RpgView vista = new RpgView();
        PersonajeDAO modeloDAO = new PersonajeDAO();

        // El controlador amarra la vista con el modelo
        RpgController controlador = new RpgController(vista, modeloDAO);

        // Se arranca la aplicación
        controlador.iniciar();
    }
}