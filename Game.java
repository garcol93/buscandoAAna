import java.util.Random;
/**
 * Esta clase es la clase principal de la aplicación "Buscando a Ana".
 * "Buscando a Ana" es un juego de aventura basado en texto muy simple.
 * Usuarios puede caminar alrededor de un paisaje buscando a ana. 
 *
 * Para jugar a este juego, crea una instancia de esta clase y llama al "Game"
 * método.
 *
 * Esta clase principal crea e inicializa todas las demás: crea todas las
 * habitaciones, crea el analizador e inicia el juego. También evalúa y
 * ejecuta los comandos que devuelve el analizador.
 *
 * @author (garcol93) 
 * @version (18.03.07)
 */
public class Game 
{
    private Parser parser;
    private Room currentRoom;    
    /**
     * Crea el juego e inicializa su mapa interno.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();        
    }

    /**
     * Crea todas las salas y vincula sus salidas juntas.
     */
    private void createRooms()
    {
        Room inicio, bosque, laberinto, colegio, castillo, casaAbuela, armario, narnia;        
        // crea las habitaciones(Room)
        inicio = new Room("en la entrada al mundo de Ana");
        bosque = new Room("en el bosque te llamas Ana?");
        laberinto = new Room("en el laberinto donde se pinchan los yonkis");
        colegio = new Room("en el colegio donde Jorge reparte caramelos");
        castillo = new Room("en el castillo donde la reina los ve a todos ociosos");
        casaAbuela = new Room("en la casa de la abuela que esta haciendo la tortilla de patata");
        armario = new Room("en el armario un lugar un tanto oscuro");
        narnia = new Room("en el mundo de Narnia algo no va bien");
        
        //habitaciones posibles donde esta Ana 
        Room[] numeroRoom = {laberinto, colegio, castillo, casaAbuela, armario, narnia}; 
        
        //colocacion de Ana de manera aleatoria
        Random random = new Random();
        numeroRoom[random.nextInt(numeroRoom.length)].setEstaAna(true);
        
        // puertas de salida de las Room(norte, este ,sur, oeste )
        inicio.setExits(bosque, null, null, null);
        bosque.setExits(laberinto, null, inicio, null);
        laberinto.setExits(castillo, colegio, bosque, casaAbuela);
        colegio.setExits(null, null, null, laberinto);
        castillo.setExits(null, null, laberinto, null);
        casaAbuela.setExits(null,laberinto,armario, null);
        armario.setExits(casaAbuela, null, null, narnia);
        narnia.setExits(null, casaAbuela, null, null);

        currentRoom = inicio;  // comienza el juego afuera
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
        System.out.println("Gracias por jugar. Adiós.");
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
        System.out.println("Usted está " + currentRoom.getDescription());
        System.out.print("Posibles salidas: ");
        if(currentRoom.northExit != null) {
            System.out.print("norte ");
        }
        if(currentRoom.eastExit != null) {
            System.out.print("este ");
        }
        if(currentRoom.southExit != null) {
            System.out.print("sur ");
        }
        if(currentRoom.westExit != null) {
            System.out.print("oeste ");
        }
        System.out.println();
    }

    /**
     * Dado un comando, procese (es decir: ejecute) el comando.
     * Comando @param El comando que se procesará.
     * @return true Si el comando finaliza el juego, de lo contrario, false.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("No sé a qué te refieres ...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("ayuda")) {
            printHelp();
        }
        else if (commandWord.equals("ir")) {
            goRoom(command);
        }
        else if (commandWord.equals("salir")) {
            wantToQuit = quit(command);
        }
         
        return wantToQuit;
    }

    // implementaciones de comandos de usuario:

    /**
     * Imprima alguna información de ayuda.
     * Aquí imprimimos un mensaje estúpido y críptico y una lista de los
     * palabras de comando.
     */
    private void printHelp() 
    {
        System.out.println("Estás perdido? Asi como vas a encontrar a Ana.");
        System.out.println(" Vagas alrededor del mundo de Ana.");
        System.out.println();
        System.out.println("Sus palabras de comando son:");
        System.out.println("    ir, salir, ayuda ");
    }

    /** 
     * Intenta ir en una dirección. Si hay una salida, ingrese
     * la nueva sala; de lo contrario, imprima un mensaje de error.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // si no hay una segunda palabra, no sabemos a dónde ir ...
            System.out.println("A donde vamos?");
            return;
        }

        String direction = command.getSecondWord();

        // Intenta salir de la habitación actual.
        Room nextRoom = null;
        if(direction.equals("norte")) {
            nextRoom = currentRoom.northExit;
        }
        if(direction.equals("este")) {
            nextRoom = currentRoom.eastExit;
        }
        if(direction.equals("sur")) {
            nextRoom = currentRoom.southExit;
        }
        if(direction.equals("oeste")) {
            nextRoom = currentRoom.westExit;
        }

        if (nextRoom == null) {
            System.out.println("No hay puerta!!!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println("Usted está " + currentRoom.getDescription());
            //comprueba si esta Ana
            if(currentRoom.getEstaAna() == true){
                System.out.println("Por fin has encontrado Ana");
                System.out.println("       Has Ganado!!!");          
            } 
            else{
                System.out.print("Posibles salidas: ");
                if(currentRoom.northExit != null) {
                    System.out.print("norte ");
                }
                if(currentRoom.eastExit != null) {
                    System.out.print("este ");
                }
                if(currentRoom.southExit != null) {
                    System.out.print("sur ");
                }
                if(currentRoom.westExit != null) {
                    System.out.print("oeste ");
                }
                System.out.println();
            }
        }
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