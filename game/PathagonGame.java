/**
 * Title:        PhatagonGame<p>
 * Description:  Class to start a new game and show the board (current state).<p>         
 * Company:      UNRC<p>
 * @author Juan Manuel Copia, Sebastian Fischer, Gabriel Gonzalez.
 * @version 0.1
 */

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

	public void startGame() {
		Scanner entrada = new Scanner(System.in);
		int row,column;
		boolean ok;
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
				if (this.state.getWhites() != 0){
					do{
						System.out.println("");
						System.out.print("fila: ");
						row = entrada.nextInt() -1;
						System.out.print("columna: ");
						column = entrada.nextInt() -1;
						System.out.println("");
						System.out.println("");
						if (row<0 || row>6 || column<0 || column>6	){
							System.out.println("ESA CASILLA NO EXISTE.");
							System.out.println("TANTO LA FILA COMO LA COLUMNA DEBE SER UN VALOR ENTRE 1 Y 7.");
							System.out.println("INGRESE NUEVAMENTE...");
							ok=false;
						}
						else{
							if (!state.emptySquare(row,column)){
								System.out.println("YA HAY UNA FICHA EN ESA CASILLA. INGRESE NUEVAMENTE...");
								ok=false;
							}
							else{
								if (state.blockedSquare(row,column)){
									System.out.println("ESA CASILLA ESTA BLOQUEADA. INGRESE NUEVAMENTE...");
									ok=false;
								}
								else {
									this.state.putPieceIn(row,column);
									System.out.println("MOVISTE:");
									System.out.println(this.state.toString());
									ok=true;
								}
							}
						}
					}while(!ok);
				}
				else{
					this.state.setMax(!this.state.isMax());
					System.out.println("NO TIENES FICHAS, SE SALTA TU TURNO");
				}
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