
import java.util.*;

public class Main {
	


	public static void main(String[] args) {
		Random r = new Random();
		PathagonState state = new PathagonState();
		PathagonSearchProblem p = new PathagonSearchProblem();
		MinMaxAlphaBetaEngine<PathagonSearchProblem,PathagonState> engine = new MinMaxAlphaBetaEngine<PathagonSearchProblem,PathagonState>(p,3);
		int a,b;
		System.out.println("ESTADO INICIAL:");
		System.out.println(state.toString());
		while (!p.end(state)) {
			if (state.isMax()) {
				System.out.println("MOVIO NEGRAS:");			
				state = engine.computeSuccessor(state);
				System.out.println(state.toString());

			}
			else {
				// Jugador Random
				PathagonState child = state.pathagonStateClone();
				System.out.println("MOVIO BLANCAS:");
				a = r.nextInt(6);
				b = r.nextInt(6);
				while ((!child.emptySquare(a,b)) || (child.blockedSquare(a,b))) {
					a = r.nextInt(6);
					b = r.nextInt(6);
				}

    			
    				
    			

				// desbloqueo
				for (int i= 0; i<7 ; i++) {
				    for (int j = 0; j<7 ; j++) {
				    	if (child.emptySquare(i,j)) {
				    		if (!child.blockedSquare(i,j)) {

				    		}
				    		else {
				    			child.unblockSquare(i,j);
				    		}
				    	}
				   	}
				}


				if (child.getWhites() != 0) {
					child.putPieceIn(a,b);
					child.setMax(!child.getMax());
				}
				System.out.println(child.toString());
				state = child;
			}
		}
		System.out.println("Juego finalizado");
		System.out.println("");
		System.out.println("");



	}




}