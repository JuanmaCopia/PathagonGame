/**
 * Title:        Main Class<p>
 * Description:  The Main class, where we play the game<p>         
 * Company:      UNRC<p>
 * @author Juan Manuel Copia, Sebastian Fischer, Gabriel Gonzalez.
 * @version 0.1
 */

import java.util.*;

public class Main {


  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }


  public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);
    int opcion,aux;
    boolean continuar=true;
    do {
      clearScreen();
      System.out.println("--------------------------------------------------------------------------------");
      System.out.println("                            PATHAGON");
      System.out.println("--------------------------------------------------------------------------------");
      System.out.println("");
      System.out.println("");
      System.out.println("INGRESE LA OPCION DESEADA: ");
      System.out.println("");
      System.out.println("1 - JUGAR PARTIDA");
      System.out.println("");
      System.out.println("2 - SALIR");
      System.out.println("");
      System.out.print("Opcion:  ");
      opcion = entrada.nextInt();
      while (opcion<1 || opcion>2){
        System.out.println("El numero de opcion elegido no es valido. Ingrese nuevamente...");
        opcion = entrada.nextInt();
      }
      if (opcion==1){
        clearScreen();
        System.out.println("");
        System.out.println("");
        System.out.println("QUIEN VA A COMENZAR JUGANDO? ");
        System.out.println("");
        System.out.println("1 - YO.");
        System.out.println("");
        System.out.println("2 - EL ORDENADOR.");
        System.out.println("");
        System.out.println("3 - VOLVER AL MENU.");
        System.out.println("");
        System.out.print("Opcion:  ");
        opcion = entrada.nextInt();
        while (opcion<1 || opcion>3){
          System.out.println("El numero de opcion elegido no es valido. Ingrese nuevamente...");
          opcion = entrada.nextInt();
        }
        if (opcion==1){
          clearScreen();
          PathagonGame game = new PathagonGame(1);
          game.startGame();
          System.out.println("");
          System.out.print("Ingrese cualquier numero para continuar...");
          aux=entrada.nextInt();
        }
        if (opcion==2){
          clearScreen();
          PathagonGame game = new PathagonGame(0);
          game.startGame();
          System.out.println("");
          System.out.print("Ingrese cualquier numero para continuar...");
          aux=entrada.nextInt();
        }       
      }
      else {
        continuar=false;
      }
    } while(continuar);
    entrada.close();
    clearScreen();
  }

}