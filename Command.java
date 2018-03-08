
/**
 * Esta clase es parte de la aplicación "Buscando a Ana".
 * "Buscando a Ana" es un juego de aventura basado en texto muy simple.
 *
 * Esta clase contiene información sobre un comando emitido por el usuario.
 * Un comando actualmente consta de dos cadenas: una palabra de comando y una segunda
 * palabra (por ejemplo, si el comando era "tomar mapa", entonces las dos cuerdas
 * obviamente son "tomar" y "mapa").
 *
 * La forma en que se usa es: los comandos ya están marcados para ser válidos
 * palabras de comando. Si el usuario ingresó un comando inválido (una palabra que no es
 * conocido), entonces la palabra de comando es <null>.
 *
 * Si el comando tenía solo una palabra, entonces la segunda palabra es <null>.
 *
 * @author (garcol93) 
 * @version (18.03.07)
 */
public class Command
{
    private String commandWord;
    private String secondWord;

    /**   
     * Crea un objeto de comando. La primera y la segunda palabra deben ser entregadas, pero
     * cualquiera de los dos (o ambos) puede ser nulo.
     * @param firstWord La primera palabra del comando. Nulo si el comando
     * no fue reconocido.
     * @param secondWord La segunda palabra del comando.
     */
    public Command(String firstWord, String secondWord)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
    }

    /**
     * Devuelve la palabra de comando (la primera palabra) de este comando. Si el
     * el comando no se entendió, el resultado es null.
     * @return La palabra de comando.
     */
    public String getCommandWord()
    {
        return commandWord;
    }

    /**
     * @return La segunda palabra de este comando. Devuelve nulo si no hubo
     * segunda palabra.
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * @return true si este comando no fue entendido.
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    /**
     * @return true si el comando tiene una segunda palabra.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

