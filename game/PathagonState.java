
import java.util.*;

public class PathagonState implements AdversarySearchState {

	private int[][] board;
	private Boolean max;
	private int whites, blacks;
	private PathagonState parent;
	private int whitePiece, blackPiece;



	//Constructor of the class.
	public PathagonState(){
		board = new int [7] [7];
		whites = 14;
		blacks = 14;
		max = true;
		parent = null;
		whitePiece = 1;
		blackPiece = 2;
	}

	/**
   	* @pre. this != null
   	* @param. a State
   	* @pos. a copy of the current state with the iverted turn.
   	*/	
	public PathagonState pathagonStateChild(){
		PathagonState copy = new PathagonState();
		copy.whites = this.whites;
		copy.blacks = this.blacks;
		copy.parent = this;
		copy.max = !this.max;
		copy.whitePiece = this.whitePiece;
		copy.blackPiece = this.blackPiece;

		for(int i=0; i<7; i++){
			for(int j=0; j<7 ;j++){
				copy.board [i][j] = this.board[i][j];
			}
		}
		return copy;
	}


	/**
   	* @pre. this != null
   	* @param. a State
   	* @pos. 
   	*/	
	public PathagonState pathagonStateClone(){
		PathagonState copy = new PathagonState();
		copy.whites = this.whites;
		copy.blacks = this.blacks;
		copy.parent = this.parent;
		copy.max = this.max;
		copy.whitePiece = this.whitePiece;
		copy.blackPiece = this.blackPiece;

		for(int i=0; i<7; i++){
			for(int j=0; j<7 ;j++){
				copy.board [i][j] = this.board[i][j];
			}
		}
		return copy;
	}

	
	public void unblockAllSquares() {
		for (int i= 0; i<7 ; i++) {
			for (int j = 0; j<7 ; j++) {
			    if (this.blockedSquare(i,j)) {
			    	this.unblockSquare(i,j);
			    }
			}
		}
	}




	/**
	 * @pre. this != null
	 * @param. indices de la casilla
	 * @pos. El estado correspondiente despues de haber insertado la ficha en esa casilla
	 */	
	public void putPieceIn(int i, int j) {
  	int myPiece, adversaryPiece;
  	if (max) {
  		myPiece = blackPiece;
  		adversaryPiece = whitePiece;
  		blacks--;
  	}
  	else {
  		myPiece = whitePiece;
  		adversaryPiece = blackPiece;
  		whites--;
  	}
  	// pongo la ficha 
  	board [i][j] = myPiece;
  	unblockAllSquares();
  	this.max = !this.max;
  	// chequeos verticales hacia arriba
  	if (i-1 > 0) {
  		if (board[i-1][j] == adversaryPiece) {
  			if (board[i-2][j] == myPiece) {
  				board [i-1] [j] = -1;
  				if (max)
  					whites++;
  				else 
  					blacks++;    				
  			}
  		}
  	}
  	// chequeo vertical hacia abajo
  	if (i+1 < 6) {
  		if (board[i+1][j] == adversaryPiece) {
  			if (board[i+2][j] == myPiece) {
  				board [i+1] [j] = -1;
  				if (max)
  					whites++;
  				else 
  					blacks++;
  			}
  		}
  	}
  	// chequeo horizontal hacia la izquierda
  	if (j-1 > 0) {
  		if (board[i][j-1] == adversaryPiece) {
  			if (board[i][j-2] == myPiece) {
  				board [i] [j-1] = -1;
  				if (max)
  					whites++;
  				else 
  					blacks++;
  			}
  		}
  	}
  	// chequeo horizontal hacia la derecha
  	if (j+1 < 6) {
  		if (board[i][j+1] == adversaryPiece) {
  			if (board[i][j+2] == myPiece) {
  				board [i] [j+1] = -1;
  				if (max)
  					whites++;
  				else 
  					blacks++;
  			}
  		}
  	}
  }

  /**
   * @pre. this != null
   * @param. indices
   * @pos. true si la casilla (i,j) del tablero esta vacia, false caso contrario
   */	
  public boolean emptySquare(int i, int j) {
  	return (this.board[i][j] <= 0);
  }

  /**
   * @pre. this != null
   * @param. a State
   * @pos. false si la casilla (i,j) del tablero esta bloqueada, false caso contrario
   */	
  public boolean blockedSquare(int i,int j) {
  	return (this.board[i][j] == -1);
  }

  /**
   * @pre. this != null
   * @param. a State
   * @pos. casilla desbloqueada
   */	
  public void unblockSquare(int i,int j) {
  	this.board[i][j] = 0;
  }


	/**
	 * @pre. this != null
	 * @param. a State
	 * @pos. numer of blancas
	 */	
	public int getWhites(){
		return this.whites;
	}

	/**
	 * @pre. this != null
	 * @param. a State
	 * @pos. numer of negras
	 */	
	public int getBlacks(){
	  return this.blacks;
	}

	/**
	 * @pre. this != null
	 * @param. a State
	 * @pos. numer of negras
	 */	
	public PathagonState getParent(){
	  return this.parent;
	}

	/**
	 * @pre. this != null
	 * @param. a State
	 * @pos. numer of negras
	 */	
	public void setParent(PathagonState parent){
	  this.parent = parent;
	}


	/**
	 * @pre. this != null
	 * @param. a State
	 * @pos. numer of negras
	 */	
	public void setMax(boolean max){
	  this.max = max;
	}


	/**
	 * @pre. this != null
	 * @param. a State
	 * @pos. numer of negras
	 */	
	public boolean getMax(){
	  return this.max;
	}

	/**
	 * @pre. true
	 * @param. numer of white pieces
	 * @pos. number of wihite pieces updated
	 */	
	public void setWhites(int n_whites){
	  this.whites = n_whites;
	}

	/**
	 * @pre. true
	 * @param. numer of black pieces
	 * @pos. number of black pieces updated
	 */	
	public void setBlacks(int n_blacks){
	  this.blacks = n_blacks;
	}


	/**
	 * @pre. this != null
	 * @param. i,j: Piece position, param: value of original board on [i][j] position
	 * @pos. board of copy updated.
	 */	
	public void setBoard(int i, int j, int param){
		this.board[i][j] = param;
	}

	/**
	 * @pre. this != null
	 * @param. 
	 * @pos. 
	 */	
	public int getBoard(int i, int j){
		return this.board[i][j];
	}
	


	/**
	 * @pre. this != null
	 * @param. 
	 * @pos. 
	 */	
	public int getBlackPiece(){
		return this.blackPiece;
	}

	/**
	 * @pre. this != null
	 * @param. 
	 * @pos.
	 * @return. la posicion de la ficha negra mas cercana al lado izquierdo del tablero  
	 */	
	public Pair<Integer,Integer> foundFirstBlackPiece() {
		for (int j= 0; j<7 ; j++) {
	    	for (int i = 0; i<7 ; i++) {
	    		if (board[i][j] == blackPiece) {
	    			return new Pair<Integer,Integer>(i,j);
	    		}
	    	}
	    }
	    return null;
	}

	/**
	 * @pre. 
	 *	@param 
	 *	@pos. True si existe un camino del jugador blanco que conecta sus 2 extremos del tablero, false caso contrario
	 */
  public boolean whiteWins() {
  	Queue<Pair<Integer,Integer>> q = new LinkedList<Pair<Integer,Integer>>();
  	int i = 0;
  	Pair<Integer,Integer> p;
  	List<Pair<Integer,Integer>> visited = new ArrayList<Pair<Integer,Integer>>();
  	List<Pair<Integer,Integer>> adjacents;
  	for (int j= 0; j<7 ;j++) {
  		if (board [0][j] == whitePiece) {
  			p = new Pair<Integer,Integer>(0,j);
  			q.add(p);
  			visited.add(p);
  		}
  	}
  	while (!q.isEmpty()) {
  		p = q.poll();
  		if (p.getFirst() == 6) {
  			return true;
  		}
  		adjacents = new LinkedList<Pair<Integer,Integer>>();
  		adjacents = getAdjacents(p,whitePiece);
  		for (Pair<Integer,Integer> pair: adjacents) {
  			if (!visited.contains(pair) && !q.contains(pair)) {
  				visited.add(pair);
  				q.add(pair);
  			}
  		}
  	}
  	return false;
  }

  /**
	 * @pre. 
	 *	@param 
	 *	@pos. True si existe un camino del jugador negro que conecta sus 2 extremos del tablero, false caso contrario
	 */
 	public boolean blackWins() {
  	Queue<Pair<Integer,Integer>> q = new LinkedList<Pair<Integer,Integer>>();
  	int i = 0;
  	Pair<Integer,Integer> p;
  	List<Pair<Integer,Integer>> visited = new ArrayList<Pair<Integer,Integer>>();
  	List<Pair<Integer,Integer>> adjacents;
  	for (int j= 0; j<7 ;j++) {
  		if (board [j][0] == blackPiece) {
  			p = new Pair<Integer,Integer>(j,0);
  			q.add(p);
  			visited.add(p);
  		}
  	}
  	while (!q.isEmpty()) {
  		p = q.poll();
  		if (p.getSecond() == 6) {
  			return true;
  		}
  		adjacents = new LinkedList<Pair<Integer,Integer>>();
  		adjacents = getAdjacents(p,blackPiece);
  		for (Pair<Integer,Integer> pair: adjacents) {
  			if (!visited.contains(pair) && !q.contains(pair)) {
  				visited.add(pair);
  				q.add(pair);
  			}
  		}
  	}
  	return false;
  }

  /**
	 * @pre. 
	 *	@param Un par que representa una casilla del tablero, un int que es el "color" de ficha que usa el jugador
	 *	@pos. Devuelve la lista de pares con aquellos pares que son adjacentes al recibido y que ademas tienen una ficha del mismo color que el jugador
	 */
	public List<Pair<Integer,Integer>> getAdjacents(Pair<Integer,Integer> p, int playerPiece) {		
		List<Pair<Integer,Integer>> adjacents = new LinkedList<Pair<Integer,Integer>>();
		Integer row = p.getFirst();
		Integer column = p.getSecond();
		// obtengo el par adyacente vertical de arriba
		if (row != 0) {
			if (board[row-1][column] == playerPiece) 
				adjacents.add(new Pair<Integer,Integer>(row-1,column));	
		}
		// obtengo el par adyacente vertical de abajo
		if (row != 6) {
			if (board[row+1][column] == playerPiece) 
				adjacents.add(new Pair<Integer,Integer>(row+1,column));
		}
		// obtengo el par adyacente horizontal de la izquierda
		if (column != 0) {
			if (board[row][column-1] == playerPiece) 
				adjacents.add(new Pair<Integer,Integer>(row,column-1));
		}
		// obtengo el par adyacente horizontal de la derecha
		if (column != 6) {
			if (board[row][column+1] == playerPiece) 
				adjacents.add(new Pair<Integer,Integer>(row,column+1));
		}
		return adjacents;
  }

	
	/**
	 * @pre. true
	 * @param. a State
	 * @pos. True sii is a max state.
	 */
	public boolean isMax(){
		return this.max;
	}

	/**
	 * @pre. S != null & this != null
	 *	@param a State
	 *	@pos. true sii this equals S
	 */
	public boolean equals(AdversarySearchState s){
		PathagonState state = (PathagonState) s;
		for(int i=0; i<7 ;i++){
			for(int j=0; j<7 ;j++){
				if (this.board[i][j] != state.board[i][j]) {
					return false;
				}
			}
		}
		return true;
	}


	
	/**
	* @pre. S != null & this != null
	*	@param a State
	*	@pos. true sii this equals S
	*/
	/*
	public String toString() {

    	String str = "";

    	for (int i = 0 ; i<7 ; i ++ ){
    	    for (int j = 0 ; j < 7 ; j++){
    	        str += board[i][j]+"\t";
    	    }
    	    str += "\n";
    	}
   		return str;
	}
	
	*/

	public String toString() {


    String separator = ", ";
    StringBuffer result = new StringBuffer();
    result.append("\n");

    // iterate over the first dimension
    for (int i = 0; i < 7; i++) {
        // iterate over the second dimension
        for(int j = 0; j < 7; j++){
            result.append(board[i][j]);
            result.append(separator);
        }
        // remove the last separator
        result.setLength(result.length() - separator.length());
        // add a line break.
        result.append("\n");  
    }
    result.append("\n");
    result.append("CANTIDAD DE FICHAS BLANCAS: "+this.whites);
    result.append("\n");
    result.append("CANTIDAD DE FICHAS NEGRAS: "+this.blacks);
    result.append("\n");
    result.append("\n");
    result.append("------------------------------------------------------------------------------");
    return result.toString();
	}

	public Object ruleApplied() {
		return new Object();
	}

/*
	public static void main(String[] args) {

		PathagonState state = new PathagonState();

		state.board[0][6] = state.whitePiece;
		state.board[1][6] = state.whitePiece;
		state.board[2][6] = state.whitePiece;
		state.board[3][6] = state.whitePiece;
		state.board[4][6] = state.whitePiece;
		state.board[5][6] = state.whitePiece;
		state.board[6][6] = state.whitePiece;



		state.board[1][0] = state.blackPiece;
		state.board[1][1] = state.blackPiece;
		state.board[1][2] = state.blackPiece;
		state.board[2][2] = state.blackPiece;
		state.board[2][3] = state.blackPiece;
		state.board[2][4] = state.blackPiece;

		if (state.whiteWins()) {
			System.out.println("El jugador blanco gano");
		}
		else {
			System.out.println("NO GANO BLANCO TODAVIA");
		}

		System.out.println("-------------------------------------------------");
		System.out.println("-------------------------------------------------");
		PathagonSearchProblem p = new PathagonSearchProblem();
		System.out.println("");
		System.out.println("El valor de este estado es: "+p.value(state)+" Deberia ser 5");
		System.out.println("");
		System.out.println("-------------------------------------------------");
		System.out.println("-------------------------------------------------");

		PathagonState state2 = new PathagonState();

		state2.board[1][0] = state2.blackPiece;
		state2.board[1][1] = state2.blackPiece;
		state2.board[1][2] = state2.blackPiece;
		state2.board[2][2] = state2.blackPiece;
		state2.board[2][3] = state2.blackPiece;
		state2.board[2][4] = state2.blackPiece;
		state2.board[1][4] = state2.blackPiece;
		state2.board[1][5] = state2.blackPiece;
		state2.board[1][6] = state2.blackPiece;

		if (state2.blackWins()) {
			System.out.println("El jugador negro gano");
		}
		else {
			System.out.println("NO GANO NEGRO TODAVIA");
		}

		System.out.println("-------------------------------------------------");
		System.out.println("-------------------------------------------------");
		System.out.println("");
		System.out.println("El valor de este estado es: "+p.value(state2)+" Deberia ser 7");
		System.out.println("");
		System.out.println("-------------------------------------------------");
		System.out.println("-------------------------------------------------");
	
	}


*/
}