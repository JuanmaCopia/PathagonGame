
import java.util.*;

public class PathagonGame {
	
	private PathagonState state;
	private PathagonSearchProblem p;
	MinMaxAlphaBetaEngine<PathagonSearchProblem,PathagonState> engine;
	
	// Constructor of the class
	public PathagonGame(int startingPlayer) {
		this.p = new PathagonSearchProblem();
		engine = new MinMaxAlphaBetaEngine<PathagonSearchProblem,PathagonState>(p,4);
		this.state = p.initialState();
		if (startingPlayer == 1) {
			state.setMax(false);
		}
	}

	public void moveIA() {
		this.state = engine.computeSuccessor(this.state);
	}

	public boolean movePlayer(int row, int column) {
		if (this.state.getWhites() != 0) {
			this.state.putPieceIn(row,column);
			return true;
		}
		else {
			this.state.setMax(!this.state.getMax());
			return false;
		}	
	}


	public void startGame() {
		Scanner entrada = new Scanner(System.in);
		int row,column;
		System.out.println("ESTADO INICIAL:");
		System.out.println(this.state.toString());
		while (!this.p.end(this.state)) {
			if (this.state.isMax()) {					
				this.moveIA();
				System.out.println("MOVIO NEGRAS:");
				System.out.println(state.toString());
			}
			else {
				System.out.println("SU TURNO: ");
				System.out.println("");
				System.out.print("fila: ");
				row = entrada.nextInt();
				System.out.print("columna: ");
				column = entrada.nextInt();
				System.out.println("");
				System.out.println("");
				System.out.println("MOVISTE:");

				if (!movePlayer(row,column)) {
					System.out.println("NO TIENES FICHAS, SE SALTA TU TURNO");
				}
				System.out.println(this.state.toString());
			}
		}
		System.out.println("FIN DEL JUEGO");
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
	}

}