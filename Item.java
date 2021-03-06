/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String id;
    private String descripcionItem;
    private int peso;
    private boolean puedoCoger;
    /**
     * Crea un Item descrito "descripcion" con "peso". Inicialmente, 
     * @param description Descripcion del Item.
     * @param peso Peso del Item.
     * @param puedoCojer true se puede cojer .
     */
    public Item(String nombre ,String descripcion,boolean puedoCoger, int peso)
    {
        id = nombre;
        descripcionItem = descripcion;
        this.puedoCoger = puedoCoger;
        this.peso = peso;
    }

    /**
     * @return nombre del Item.
     */
    public String getId()
    {
        return id;
    }

    /**
     * @return descripcion del Item.
     */
    public String getDescriptionItem()
    {
        return descripcionItem;
    }
   
    /**
     * @return info del Item.
     */
    public String getInfoItem()
    { 
        return "Hay " + getDescriptionItem();
    }

    /**
     * @return boolean true si se puede coger false si no
     */
    public boolean getPuedoCoger()
    {
        return puedoCoger;
    }
    
    /**
     * @return peso del Item.
     */
    public int getPesoItem()
    {
        return peso;
    }
}