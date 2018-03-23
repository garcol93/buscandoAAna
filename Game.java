import java.util.Random;
/**
 * Esta clase es la clase principal de la aplicacion "Buscando a Ana".
 * "Buscando a Ana" es un juego de aventura basado en texto muy simple.
 * Usuarios puede caminar alrededor de un paisaje buscando a ana. 
 *
 * Para jugar a este juego, crea una instancia de esta clase y llama al "Game"
 * metodo.
 *
 * Esta clase principal crea e inicializa todas las demás: crea todas las
 * habitaciones, crea el analizador e inicia el juego. Tambien evalua y
 * ejecuta los comandos que devuelve el analizador.
 *
 * @author (garcol93) 
 * @version (18.03.07)
 */
public class Game 
{
    private Parser parser;
    private Player player;
    /**
     * Crea el juego e inicializa su mapa interno.
     */
    public Game() 
    {
        createPlayer();      
        parser = new Parser();       
    }

    /**
     * Crea jugador
     */
    private void createPlayer()
    {
        player = new Player();
        player.setCurrentRoom(createRooms()); // comienza el juego afuera
    }
    
    /**
     * Crea todas las salas y vincula sus salidas juntas.
     */
    private Room createRooms()
    {        
        // crea las habitaciones(Room)
        Room inicio, bosque, laberinto, colegio, castillo, casaAbuela, armario, narnia, mordor;        

        inicio = new Room("en la entrada al mundo de Ana");
        bosque = new Room("en el bosque te llamas Ana?");
        laberinto = new Room("en el laberinto donde se pinchan los yonkis");
        colegio = new Room("en el colegio donde Jorge reparte caramelos");
        castillo = new Room("en el castillo donde la reina los ve a todos ociosos");
        casaAbuela = new Room("en la casa de la abuela que esta haciendo la tortilla de patata");
        armario = new Room("en el armario un lugar un tanto oscuro");
        narnia = new Room("en el mundo de Narnia algo no va bien");
        mordor = new Room("en Mordor lugar donde viven los orcos te han capturado");
        //habitaciones posibles donde esta Ana 
        Room[] numeroRoom = {laberinto, colegio, castillo, casaAbuela, armario, narnia}; 

        //colocacion de Ana de manera aleatoria
        Random random = new Random();
        numeroRoom[random.nextInt(numeroRoom.length)].setEstaAna(true);

        // puertas de salida de las Room
        inicio.setExit("norte",bosque);

        bosque.setExit("norte",laberinto);
        bosque.setExit("sur",inicio);
        bosque.setExit("sureste",mordor);

        laberinto.setExit("norte",castillo);
        laberinto.setExit("este",colegio);
        laberinto.setExit("sur", bosque);
        laberinto.setExit("oeste",casaAbuela);

        colegio.setExit("oeste", laberinto);
        colegio.setExit("noreste", castillo);

        castillo.setExit("sur", laberinto);
        castillo.setExit("sureste",colegio);

        casaAbuela.setExit("este",laberinto);
        casaAbuela.setExit("sur",armario);

        armario.setExit("norte", casaAbuela);
        armario.setExit("oeste", narnia);

        narnia.setExit("este", armario);

        //crea los Items y a�ade
        casaAbuela.addItem(new Item("tortilla","una tortilla de patata de la abuela ", true, 800));
        laberinto.addItem(new Item("jeringa","una jeringa con un aspecto asqueroso ", true, 5));
        colegio.addItem(new Item("caramelo","un caramelo con un aspecto delicioso ", true, 5));
        colegio.addItem(new Item("goma","una goma de borrar ", true, 5));
        laberinto.addItem(new Item("rata","una rata muerta ", true, 200));
        mordor.addItem(new Item("cubo","un cubo hasta arriba de meadas ", false, 200));

         return inicio; // comienza el juego afuera
    }

    /**
     *  Rutina de juego principal. Bucles hasta el final del juego.
     */
    public void play() 
    {            
        printWelcome();
        // Ingrese el ciclo de comando principal. Aquí leemos repetidamente los comandos y
        // ejecutarlos hasta que el juego termine.
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Gracias por jugar. Adios.");
    }

    /**
     * Imprime el mensaje de apertura para el jugador.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bienvenido al mundo de Ana");
        System.out.println("Buscando a Ana es un juego en el que tendras que encontrar a la traviesa Ana");
        System.out.println("Escriba 'ayuda' si necesita algo.");
        System.out.println();
        player.look();
        
    }

    /**
     * Dado un comando, procese (es decir: ejecute) el comando.
     * Comando @param El comando que se procesara.
     * @return true Si el comando finaliza el juego, de lo contrario, false.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("No se a que te refieres ...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("ayuda")) {
            printHelp();
        }
        else if (commandWord.equals("ir")) {
            player.goRoom(command);
        }
        else if (commandWord.equals("salir")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("mirar")) {
            player.look();
        }
        else if(commandWord.equals("comer")) {
            player.eat();
        }
        else if(commandWord.equals("volver")){
            player.back();
        }
        else if(commandWord.equals("coger")){
            player.take(command.getSecondWord());
        }
         else if(commandWord.equals("mochila")){
            player.itemsMochila(); 
        }
        else if(commandWord.equals("dejar")){
            player.drop(command.getSecondWord());
        }
        return wantToQuit;
    }

    // implementaciones de comandos de usuario:

    /**
     * Imprima alguna informacion de ayuda.
     * Aqui imprimimos un mensaje estúpido y críptico y una lista de los
     * palabras de comando.
     */
    private void printHelp() 
    {
        System.out.println("Estas perdido? Asi como vas a encontrar a Ana.");
        System.out.println(" Vagas alrededor del mundo de Ana.");
        System.out.println();
        System.out.println("Sus palabras de comando son:");
        System.out.println(parser.showCommands());
    }

    /** 
     * "Salir" fue ingresado. Compruebe el resto del comando para ver
     * si realmente abandonamos el juego.
     * @return true, si este comando sale del juego, de lo contrario es false.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Salir de que?");
            return false;
        }
        else {
            return true;  // señal de que queremos abandonar
        }
    }   

}