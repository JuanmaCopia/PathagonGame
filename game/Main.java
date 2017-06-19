
import java.util.*;


public class Main {
	


	public static void main(String[] args) {
		PathagonGame game = new PathagonGame(0);
		game.startGame();
	}
}		





/*
		PathagonState state = new PathagonState();
		PathagonSearchProblem p = new PathagonSearchProblem();
		MinMaxAlphaBetaEngine<PathagonSearchProblem,PathagonState> engine = new MinMaxAlphaBetaEngine<PathagonSearchProblem,PathagonState>(p,4);
		int a,b;
		Scanner entrada = new Scanner(System.in);

		System.out.println("ESTADO INICIAL:");
		System.out.println(state.toString());
		while (!p.end(state)) {
			if (state.isMax()) {					
				state = engine.computeSuccessor(state);
				System.out.println("MOVIO NEGRAS:");
				System.out.println(state.toString());

			}
			else {
				
				PathagonState child = state.pathagonStateClone();
				if (child.getWhites() != 0) {
					System.out.println("SU TURNO: ");
					System.out.println("");
					System.out.print("fila: ");
					a = entrada.nextInt();
					System.out.print("columna: ");
					b = entrada.nextInt();
					System.out.println("");
					System.out.println("");
					System.out.println("MOVISTE:");

					child.putPieceIn(a,b);
				}
				else {
					System.out.println("No tienes fichas");
				}

				System.out.println(child.toString());
				state = child;
			}
		}

		System.out.println("Juego finalizado");
		System.out.println("");
		if (state.blackWins()) {
			System.out.println("NEGRAS HA GANADO");
		}
		else {
			if (state.whiteWins()) {
				System.out.println("BLANCAS HA GANADO");
			}
			else {
				System.out.println("EMPATE");
			}
		}
		


	*/
