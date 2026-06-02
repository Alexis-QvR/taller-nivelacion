package rpg.controller;

import rpg.model.Personaje;
import rpg.model.PersonajeDAO;
import rpg.view.RpgView;
import java.util.List;

public class RpgController {
    private RpgView vista;
    private PersonajeDAO modeloDAO;

    public RpgController(RpgView vista, PersonajeDAO modeloDAO) {
        this.vista = vista;
        this.modeloDAO = modeloDAO;
    }

    public void iniciar() {
        int opcion;
        do {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1: 
                    String nombre = vista.pedirTexto("Ingresa el nombre del héroe: ");
                    String tipo = vista.pedirTexto("Ingresa la clase (Guerrero, Mago, Arquero): ");
                    int nivel = vista.pedirNumero("Ingresa el nivel inicial: ");
                    
                    Personaje nuevo = new Personaje(0, nombre, tipo, nivel);
                    if (modeloDAO.insertar(nuevo)) {
                        vista.mostrarMensaje("Personaje creado con éxito");
                    } else {
                        vista.mostrarMensaje("Error al crear el personaje.");
                    }
                    break;

                case 2: 
                    List<Personaje> todos = modeloDAO.listar();
                    vista.mostrarPersonajes(todos);
                    break;

                case 3: 
                    String nombreBuscar = vista.pedirTexto("Ingresa el nombre a buscar: ");
                    List<Personaje> encontrados = modeloDAO.buscarPorNombre(nombreBuscar);
                    vista.mostrarPersonajes(encontrados);
                    break;

                case 4: 
                    int idActualizar = vista.pedirNumero("Ingresa el ID del personaje: ");
                    int nuevoNivel = vista.pedirNumero("Ingresa el nuevo nivel: ");
                    
                    if (modeloDAO.actualizarNivel(idActualizar, nuevoNivel)) {
                        vista.mostrarMensaje("Nivel actualizado con éxito");
                    } else {
                        vista.mostrarMensaje("No se encontró el ID o no se pudo actualizar.");
                    }
                    break;

                case 5: 
                    int idEliminar = vista.pedirNumero("Ingresa el ID del personaje a eliminar: ");
                    if (modeloDAO.eliminar(idEliminar)) {
                        vista.mostrarMensaje("️ Personaje eliminado con éxito de MySQL");
                    } else {
                        vista.mostrarMensaje(" No se pudo eliminar. Verifica si el ID existe.");
                    }
                    break;

                case 6:
                    vista.mostrarMensaje("Cerrando");
                    break;

                default:
                    vista.mostrarMensaje(" Opción incorrecta. Intenta de nuevo.");
            }
        } while (opcion != 6); 
    }
}