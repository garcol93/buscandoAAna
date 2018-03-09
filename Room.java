
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
    public String description;
    public boolean estaAna;
    public Room northExit;
    public Room southExit;
    public Room eastExit;
    public Room westExit;
    public Room southEastExit;
    public Room northEastExit;

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
     * @param oeste La salida oeste.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southEast , Room northEast) 
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
}