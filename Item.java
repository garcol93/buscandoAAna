
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String descripcionItem;
    private int peso;

    /**
     * Crea un Item descrito "descripcion" con "peso". Inicialmente, 
     * @param description Descripcion del Item.
     * @param peso Peso del Item.
     */
    public Item(String descripcion , int peso )
    {
        descripcionItem = descripcion;
        this.peso = peso;
    }

    /**
     * @return descripcion del Item.
     */
    public String getDescriptionItem()
    {
        return descripcionItem;
    }

    /**
     * @return peso del Item.
     */
    public int getPesoItem()
    {
        return peso;
    }

    /**
     * @return info del Item.
     */
    public String getInfoItem()
    { 
        return "Hay " + getDescriptionItem() + "su peso es de " + getPesoItem() + "gramos";
    }
}
