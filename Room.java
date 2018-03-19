import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
/**
 * class Room: una habitacion en un juego de aventuras.
 *
 * Esta clase es parte de la aplicacion "Buscando a Ana".
 * "Buscando a Ana" es un juego de aventura basado en texto muy simple.
 *
 * Una "Habitacion" representa una ubicacion en el escenario del juego. Es
 * conectado a otras habitaciones a travÃ©s de salidas. Las salidas estÃ¡n etiquetadas al norte,
 * este, sur, oeste. Para cada direccion, la habitacion almacena una referencia
 * a la habitacion vecina, o null si no hay salida en esa direccion.
 *
 * Esta habitacion contendra un variable booleana que especificara si ahi esta Ana. 
 * @author (garcol93) 
 * @version (18.03.07)
 */
public class Room 
{
    private String description;
    private boolean estaAna;
    private ArrayList<Item> items;
    private HashMap<String , Room> exits;
    /**
     * Crea una habitacion que describa "descripcion". Inicialmente, tiene
     * sin salidas. "descripcion" es algo asi como "una cocina" o
     * "un patio abierto"
     * tendra un boolean que indica si esta Ana.
     * @param description Descripcion de la sala.
     */
    public Room(String description) 
    {
        this.description = description;
        estaAna = false;
        exits = new HashMap<>();
        items = new ArrayList<>();
    }

    /**
     * Definir una salida de esta sala.
     * @param direction La dirección de la salida.
     * @param nextRoom La habitación en la dirección dada.
     */
    public void setExit(String direction, Room nextRoom)
    {
        exits.put(direction, nextRoom);
    }

    /**
     * Definir objetos de una sala.
     * @param item que se quiere añadir a la habitacion
     */
    public void addItem(Item item)
    {
        items.add(item);
    }

    /**
     * eliminar objetos de una sala.
     * @param posicion del item que se quiere eliminar de la habitacion
     */
    public void removeItem(int posicion)
    {
        items.remove(posicion);
    }
    
    /**
     * @return descripcion de la habitacion.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return true si esta Ana.
     * y false si no esta.
     */
    public boolean getEstaAna()
    {
        return estaAna;
    }

    /**
     * @return ArrayList items
     */
    public ArrayList getArrayListItems()
    {
        return items;
    }
    
    /**
     * cambia el valor de esta Ana.
     */
    public void setEstaAna(boolean estaAqui)
    {
        estaAna = estaAqui;
    }

    /**
     * 
     * @return salida
     */
    public Room getExit(String direction)
    { 
        return exits.get(direction);
    }

    /**
     * Devuelve una descripción de las salidas de la habitación.
     * Por ejemplo: "posibles salidas: norte este oeste"
     *
     * @ return Una descripción de las salidas disponibles.
     */
    public String getExitString()
    {   
        Set<String> posiblesSalidas = exits.keySet();
        String aDevolver ="Posibles salidas: ";
        for(String salida : posiblesSalidas){
            aDevolver += salida + " ";
        }          
        if(aDevolver.equals("Posibles salidas: ")){
            aDevolver+= "No hay salida. Tu pierdes";
        }
        return aDevolver;
    }

    /**
     * Devuelve una descripción larga de esta sala, de la forma:
     * Estás en el 'nombre de la habitación'
     * Salidas: noroeste noroeste
     * @return Una descripción de la sala, incluidas las salidas.
     */
    public String getLongDescription()
    {
        String aDevolver = "Usted esta " + description + "\n" ;
        if(items != null){
            for(Item itemActual : items){
                aDevolver += itemActual.getInfoItem() + "\n";
            }
        }
        //comprueba si esta Ana
        if(estaAna){
            aDevolver += "Por fin has encontrado Ana" + "\n" + "Has Ganado!!!";
        }
        else{
            aDevolver += getExitString();
        }
        return aDevolver;
    }
}

