
/**
 * Esta clase es parte de la aplicación "Buscando a Ana".
 * "Buscando a Ana" es un juego de aventura basado en texto muy simple.
 * 
 * Esta clase contiene una enumeración de todas las palabras de comando conocidas por el juego.
 * Se usa para reconocer comandos a medida que se escriben.
 * 
 * @author (garcol93) 
 * @version (18.03.07)
 */
public class CommandWords
{
    // una matriz constante que contiene todas las palabras de comando válidas
    private static final String[] validCommands = {
            "ir", "salir", "ayuda", "mirar", "comer", "volver"
        };

    /**
     * Constructor - inicializa las palabras de comando.
     */
    public CommandWords()
    {
        // nada que hacer en este momento ...
    }

    /**
     * Verifique si una cadena dada es una palabra de comando válida.
     * @return true si una cadena dada es un comando válido,
     * false si no lo es.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // si llegamos aquí, la cadena no se encontró en los comandos
        return false;
    }

    /**
     * Imprime por pantalla todos los comandos v�lidos
     */
    public String getCommandList()
    {   String aDevolver = "";
        for (String command : validCommands) {
            aDevolver += command + " ";
        }
        return aDevolver;
    }
}
