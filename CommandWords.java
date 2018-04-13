import java.util.HashMap;
/**
 * Esta clase es parte de la aplicaci√≥n "Buscando a Ana".
 * "Buscando a Ana" es un juego de aventura basado en texto muy simple.
 * 
 * Esta clase contiene una enumeraci√≥n de todas las palabras de comando conocidas por el juego.
 * Se usa para reconocer comandos a medida que se escriben.
 * 
 * @author (garcol93) 
 * @version (18.03.07)
 */
public class CommandWords
{   
    private HashMap<String, CommandWord> validCommands;
    /**
     * Constructor - inicializa las palabras de comando.
     */
    public CommandWords()
    {
        validCommands = new HashMap<>();
        validCommands.put("ir", CommandWord.IR);
        validCommands.put("ayuda", CommandWord.AYUDA);
        validCommands.put("salir", CommandWord.SALIR);
        validCommands.put("mirar", CommandWord.MIRAR);
        validCommands.put("comer", CommandWord.COMER);
        validCommands.put("volver", CommandWord.VOLVER);
        validCommands.put("coger", CommandWord.COGER);
        validCommands.put("mochila", CommandWord.MOCHILA);
        validCommands.put("dejar", CommandWord.DEJAR);
        validCommands.put("pinchar", CommandWord.PINCHAR);
    }

    /**
    †  Devuelve la CommandWord asociada con una palabra.
    †  @param commandWord La palabra para buscar (como una cadena).
    †  @return The CommandWord correspondiente a la palabra de comando String, o UNKNOWN
    †  si no es una palabra de comando v·lida.
    †  */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord aDevolver = CommandWord.UNKNOWN;
        if(isCommand(commandWord))
        {
            aDevolver = validCommands.get(commandWord);
        }
        return aDevolver;
    }

    /**
     * Verifique si una cadena dada es una palabra de comando v√°lida.
     * @return true si una cadena dada es un comando v√°lido,
     * false si no lo es.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.keySet().contains(aString);
    }

    /**
     * Imprime por pantalla todos los comandos v·lidos
     */
    public String getCommandList()
    {   String aDevolver = "";
        for (String command : validCommands.keySet()) {
            aDevolver += command + " ";
        }
        return aDevolver;
    }
}
