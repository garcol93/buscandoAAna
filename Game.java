import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;
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
    private Room currentRoom;
    //backRoom true estas en la habitacion anterior o primera
    private Stack<Room> backsRoom;
    private static final int PESO_MAX = 800;
    private int pesoActual;
    private ArrayList<Item> mochila;
    /**
     * Crea el juego e inicializa su mapa interno.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        backsRoom = new Stack<>();
        mochila = new ArrayList<>();
        pesoActual = 0; 
    }

    /**
     * Crea todas las salas y vincula sus salidas juntas.
     */
    private void createRooms()
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

        //crea los Items 
        Item jeringa, tortilla, caramelo, goma, rata, cubo;

        tortilla = new Item("tortilla", "una tortilla de patata de la abuela ", 800 ,true);
        jeringa = new Item("jeringa", "una jeringa con un aspecto asqueroso ", 5, true);
        caramelo = new Item("caramelo", "un caramelo con un aspecto delicioso ", 5, true);
        goma = new Item("goma","una goma de borrar ", 5,true);
        rata = new Item("rata","una rata muerta ",200,true);
        cubo = new Item("cubo", "un cubo hasta arriba de meadas ",200,false);
        //a�ade items
        casaAbuela.addItem(tortilla);
        laberinto.addItem(jeringa);
        colegio.addItem(caramelo);
        colegio.addItem(goma);
        laberinto.addItem(rata);
        mordor.addItem(cubo);

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
        printLocationInfo();
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
            goRoom(command);
        }
        else if (commandWord.equals("salir")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("mirar")) {
            look();
        }
        else if(commandWord.equals("comer")) {
            eat();
        }
        else if(commandWord.equals("volver")){
            back();
        }
        else if(commandWord.equals("coger")){
            take(command.getSecondWord());
        }
        else if(commandWord.equals("dejar")){
            drop(command.getSecondWord());
        }
        else if(commandWord.equals("mochila")){
            itemsMochila();
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
     * Intenta ir en una direccion. Si hay una salida, ingrese
     * la nueva sala; de lo contrario, imprima un mensaje de error.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // si no hay una segunda palabra, no sabemos a donde ir ...
            System.out.println("A donde vamos?");
            return;
        }

        String direction = command.getSecondWord();

        // Intenta salir de la habitacion actual.         
        if (currentRoom.getExit(direction)== null) {
            System.out.println("No hay puerta!!!");
        }
        else { 
            backsRoom.push(currentRoom);
            currentRoom = currentRoom.getExit(direction);
            printLocationInfo();
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

    /**
     *este metodo imprime por pantalla la posicion actual.    
     */
    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());       
        System.out.println();
    }

    /**
     *este metodo imprime por pantalla la posicion actual y las posibles direcciones   
     */
    private void look() 
    {
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
    }

    /**
     *este metodo imprime por pantalla que se ha comido   
     */
    private void eat() 
    {
        System.out.println("Has comido ahora y ya no tienes hambre");
        System.out.println();
    }

    /**
     *este metodo le hace volver a la sala anterior 
     */
    private void back() 
    {       
        if(backsRoom.empty()){
            System.out.println("Lo siento no puedes volver");
        }
        else{
            currentRoom = backsRoom.pop();
            printLocationInfo();
        }
    }

    /**
     * @param item que se quiere a�adir a la mochila
     */
    private void addItemMochila(Item item)
    {
        mochila.add(item);
    }  

    /**
     *este metodo le permite coger objetos
     *@param item que desea recoger
     */
    private void take(String nombre) 
    {               
        Item item = currentRoom.getObjetos(nombre);       
        if(item.getPuedoCoger() && item != null){
            int pesoTotal = pesoActual + item.getPesoItem();                      
            if(pesoTotal <= PESO_MAX){
                mochila.add(item);
                pesoActual += item.getPesoItem();
                currentRoom.removeItem(item);
                System.out.println("Se a�adio a la mochila " + nombre);
            }
            else{
                System.out.println("Lo siento no puedes llevar tanto peso a la vez");
            }            
        }
        else{
            System.out.println("Lo siento no puedes coger este objeto");
        }
    }

    /**
     *este metodo le permite dejar objetos
     *@param item que desea soltar
     */
    private void drop(String nombre)
    {                 
        if(mochila.size() > 0){
            boolean buscando = true;
            int contador = 0;
            Item item = null;
            while(buscando)
            {
                if(mochila.get(contador).getNombre().equals(nombre)){
                    item = mochila.get(contador);
                    currentRoom.addItem(item);
                    buscando = false;
                    pesoActual -= item.getPesoItem();
                    mochila.remove(contador);
                }
                contador++;
            }
            System.out.println("Se dejo " + nombre);
        }
        else
        {
            System.out.println("La mochila esta vacia");
        }
    }   

    /**
     *este metodo imprime los objetos de la mochila
     */
    private void itemsMochila()
    {   
        String texto = "En la mochila llevas: ";
        if(mochila.size() > 0){
            for(Item objeto : mochila)
            {
                texto += objeto.getNombre() + " ";         
            }
            texto += "\n"+ "El peso de la mochila es :" + pesoActual + "g";
            System.out.println(texto);
        }
        else{
            System.out.println("La mochila esta vacia");
        }
    }
}