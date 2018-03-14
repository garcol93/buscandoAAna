import java.util.Scanner;

/**
 * Esta clase es parte de la aplicación "Buscando a Ana".
 * "Buscando a Ana" es un juego de aventura basado en texto muy simple.
 * 
 * @author (garcol93) 
 * @version (18.03.07)
 */
public class Parser 
{
    private CommandWords commands;  // contiene todas las palabras de comando válidas
    private Scanner reader;        // fuente de entrada de comando
    /**
     * 
     * Crea un analizador para leer desde la ventana del terminal.
     */
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * @return El siguiente comando del usuario.
     */
    public Command getCommand() 
    {
        String inputLine;   // mantendrá la línea de entrada completa
        String word1 = null;
        String word2 = null;

        System.out.print("> ");     // print prompt

        inputLine = reader.nextLine();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();     // obtener la primera palabra
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();      // obtener una segunda palabra
                // nota: simplemente ignoramos el resto de la línea de entrada.
            }
        }

        // Ahora comprueba si esta palabra es conocida. Si es así, crea un comando
        // con eso. Si no, crea un comando "null" (para comando desconocido).
        if(commands.isCommand(word1)) {
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2); 
        }
    }

    /**
     * Imprime una lista de las palabras de comando validas
     */
    public void showCommands()
    {
        commands.showAll();
    }
}
