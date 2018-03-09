import java.util.HashMap;
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
    }

    /**
     * Definir las salidas de esta sala. Todas las direcciones conducen
     * a otra habitacion o es nulo (no hay salida alli).
     * @param norte La salida norte.
     * @param este El este este.
     * @param sur La salida sur.   
     * @param sureste La salida sureste.
     * @param noreste La salida noreste.   
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southEast, Room northEast) 
    {
        if(north != null){
            exits.put("norte", north);
        }
        if(east != null){
            exits.put("este", east);
        }
        if(south != null){
            exits.put("sur", south);
        }
        if(west != null){
            exits.put("oeste", west);
        }
        if(southEast !=null){
            exits.put("sureste", southEast);
        }
        if(northEast !=null){
            exits.put("noreste", northEast);
        }
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
     * cambia el valor de esta Ana.
     */
    public void setEstaAna(boolean estaAqui)
    {
        estaAna = estaAqui;
    }

    /**
     * 
     * @return  
     */
    public Room getExit(String direction)
    {   Room aDevolver= null;
        if (direction.equals("norte")){
            aDevolver= exits.get("norte");          
        }
        if(direction.equals("este")) {
            aDevolver = exits.get("este");
        }
        if(direction.equals("sur")) {
            aDevolver = exits.get("sur");
        }
        if(direction.equals("oeste")) {
            aDevolver = exits.get("oeste");
        }
        if(direction.equals("sureste")){
            aDevolver = exits.get("sureste");
        }        
        if(direction.equals("noreste")){
            aDevolver = exits.get("noreste");
        }
        return aDevolver;
    }

    /**
     * Devuelve una descripción de las salidas de la habitación.
     * Por ejemplo: "posibles salidas: norte este oeste"
     *
     * @ return Una descripción de las salidas disponibles.
     */
    public String getExitString(){   
        System.out.print("Posibles salidas: ");
        String aDevolver = "";            
        if(exits.get("norte")!= null) {
            aDevolver+="norte ";
        }
        if(exits.get("este") != null) {
            aDevolver+="este ";
        }
        if(exits.get("sur") != null) {
            aDevolver+="sur ";
        }
        if(exits.get("oeste") != null) {
            aDevolver+="oeste ";
        }
        if(exits.get("sureste")!= null){
            aDevolver+="sureste ";
        }     
        if(exits.get("noreste") != null){
            aDevolver+="noreste ";
        }    
        if(aDevolver.equals("")){
            aDevolver+= "No hay salida. Tu pierdes";
        }
        return aDevolver;
    }
}

