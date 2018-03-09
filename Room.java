
/**
 * class Room: una habitacion en un juego de aventuras.
 *
 * Esta clase es parte de la aplicacion "Buscando a Ana".
 * "Buscando a Ana" es un juego de aventura basado en texto muy simple.
 *
 * Una "Habitacion" representa una ubicacion en el escenario del juego. Es
 * conectado a otras habitaciones a través de salidas. Las salidas están etiquetadas al norte,
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
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room southEastExit;
    private Room northEastExit;
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
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(southEast !=null)
            southEastExit = southEast;
        if(northEast !=null)
        northEastExit = northEast; 
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
            aDevolver= northExit;          
        }
        if(direction.equals("este")) {
            aDevolver = eastExit;
        }
        if(direction.equals("sur")) {
            aDevolver = southExit;
        }
        if(direction.equals("oeste")) {
            aDevolver = westExit;
        }
        if(direction.equals("sureste")){
            aDevolver = southEastExit;
        }        
        if(direction.equals("noreste")){
            aDevolver = northEastExit;
        }
        return aDevolver;
    }

    /**
     * Devuelve una descripci�n de las salidas de la habitaci�n.
     * Por ejemplo: "posibles salidas: norte este oeste"
     *
     * @ return Una descripci�n de las salidas disponibles.
     */
    public String getExitString(){   
            System.out.print("Posibles salidas: ");
            String aDevolver = "";            
            if(northExit != null) {
                aDevolver+="norte ";
            }
            if(eastExit != null) {
                aDevolver+="este ";
            }
            if(southExit != null) {
                aDevolver+="sur ";
            }
            if(westExit != null) {
                aDevolver+="oeste ";
            }
            if(southEastExit != null){
                aDevolver+="sureste ";
            }     
            if(northEastExit != null){
                aDevolver+="noreste ";
            }    
            if(aDevolver.equals("")){
            aDevolver+= "No hay salida. Tu pierdes";
            }
            return aDevolver;
        }
    }

