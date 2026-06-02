package rpg.view;

import java.util.Scanner;
import rpg.model.Personaje;
import java.util.List;

public class RpgView {
    private Scanner leer;

    public RpgView() {
        this.leer = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n=======================================");
        System.out.println("   SISTEMA RPG MANAGER  ");
        System.out.println("=======================================");
        System.out.println("1. Crear un nuevo personaje");
        System.out.println("2. Listar todos los personajes");
        System.out.println("3. Buscar personaje por nombre");
        System.out.println("4. Actualizar nivel de un personaje");
        System.out.println("5. Eliminar un personaje por ID");
        System.out.println("6. Salir del programa");
        System.out.print("👉 Selecciona una opciOn: ");
        int opcion = leer.nextInt();
        leer.nextLine(); // Limpiar buffer después de leer entero
        return opcion;
    }

    public String pedirTexto(String mensaje) {
        System.out.print(mensaje);
        return leer.nextLine();
    }

    public int pedirNumero(String mensaje) {
        System.out.print(mensaje);
        int numero = leer.nextInt();
        leer.nextLine(); 
        return numero;
    }

    public void mostrarPersonajes(List<Personaje> personajes) {
        if (personajes.isEmpty()) {
            System.out.println("️ No se encontraron personajes.");
        } else {
            System.out.println("\n--- HEROES REGISTRADOS ---");
            for (Personaje p : personajes) {
                System.out.println("ID: " + p.getId() + " | Nombre: " + p.getNombre() + " | Clase: " + p.getTipo() + " | Nivel: " + p.getNivel());
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}