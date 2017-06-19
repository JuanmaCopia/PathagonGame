import java.util.*;

public class Main {

	public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);
    int opcion,aux;
    boolean continuar=true;
    do {
      System.out.print("\033[H\033[2J");
			System.out.flush();
      System.out.println("--------------------------------------------------------------------------------");
      System.out.println("                        	  PATHAGON");
      System.out.println("--------------------------------------------------------------------------------");
      System.out.println("");
      System.out.println("");
      System.out.println("");
      System.out.println("Ingrese la opcion deseada: ");
      System.out.println("");
      System.out.println("1 - Jugar partida");
      System.out.println("");
      System.out.println("2 - Salir");
      System.out.println("");
      System.out.print("Opcion:  ");
      opcion = entrada.nextInt();
      while (opcion<1 || opcion>2){
      	System.out.println("El numero de opcion elegido no es valido. Ingrese nuevamente...");
      	opcion = entrada.nextInt();
      }
      if (opcion==1){
        System.out.print("\033[H\033[2J");
				System.out.flush();
        System.out.println("Ingrese la opcion que prefiera, para saber quien realizara el primer movimiento: ");
        System.out.println("");
        System.out.println("1 - Usuario.");
        System.out.println("2 - Ordenador.");
        System.out.println("");
        System.out.println("3 - Volver al menu.");
        System.out.println("");
        System.out.print("Opcion:  ");
        opcion = entrada.nextInt();
        while (opcion<1 || opcion>3){
        	System.out.println("El numero de opcion elegido no es valido. Ingrese nuevamente...");
        	opcion = entrada.nextInt();
        }
        if (opcion==1){
        	System.out.print("\033[H\033[2J");
					System.out.flush();
        	PathagonGame game = new PathagonGame(1);
					game.startGame();
					System.out.print("");
					System.out.print("Ingrese cualquier numero para continuar...");
					aux=entrada.nextInt();
				}
				if (opcion==2){
        	System.out.print("\033[H\033[2J");
					System.out.flush();
					PathagonGame game = new PathagonGame(0);
					game.startGame();
					System.out.print("");
					System.out.print("Ingrese cualquier numero para continuar...");
					aux=entrada.nextInt();
				}				
      }
      else {
      	continuar=false;
      }
    } while(continuar==true);
    entrada.close();
    System.out.print("\033[H\033[2J");
		System.out.flush();
  }

}
		


