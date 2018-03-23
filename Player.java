import java.util.Stack;
import java.util.ArrayList;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private Room currentRoom;
    private Stack<Room> backsRoom;
    private static final int PESO_MAX = 800;
    private int pesoActual;
    private ArrayList<Item> mochila;
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        backsRoom = new Stack<>();
        mochila = new ArrayList<>();
        pesoActual = 0; 
    }

    /**
     * set currentRoom
     */
    public void setCurrentRoom(Room room)
    {
        currentRoom = room ;
    }

    /**
     * devuelve posicion currentRoom 
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /** 
     * Intenta ir en una direccion. Si hay una salida, ingrese
     * la nueva sala; de lo contrario, imprima un mensaje de error.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // si no hay una segunda palabra, no sabemos a donde ir ...
            System.out.println("A donde vamos?");
            return;
        }

        String direction = command.getSecondWord();

        // Intenta salir de la habitacion actual.         
        if (currentRoom.getExit(direction)== null) {
            System.out.println("No hay puerta!!!");
        }
        else { 
            backsRoom.push(currentRoom);
            currentRoom = currentRoom.getExit(direction);
            look();
        }

    }

    /**
     *este metodo imprime por pantalla la posicion actual y las posibles direcciones   
     */
    public void look() 
    {
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
    }

    /**
     *este metodo imprime por pantalla que se ha comido   
     */
    public void eat() 
    {
        System.out.println("Has comido ahora y ya no tienes hambre");
        System.out.println();
    }

    /**
     *este metodo le hace volver a la sala anterior 
     */
    public void back() 
    {       
        if(backsRoom.empty()){
            System.out.println("Lo siento no puedes volver");
        }
        else{
            currentRoom = backsRoom.pop();
            look();
        }
    }

    /**
     * @param item que se quiere a?adir a la mochila
     */
    public void addItemMochila(Item item)
    {
        mochila.add(item);
    }  

    /**
     *este metodo le permite coger objetos
     *@param item que desea recoger
     */
    public void take(String nombre) 
    {               
        Item item = currentRoom.getObjetos(nombre);       
        if(item.getPuedoCoger() && item != null){                                
            mochila.add(item);
            currentRoom.removeItem(item);
            System.out.println("Se añadio a la mochila " + nombre); 
        }
        else{
            System.out.println("Lo siento no puedes coger este objeto");
        }
    }
}
