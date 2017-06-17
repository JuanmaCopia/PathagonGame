public class PhatagonState implements AdversarySearchState {

	private int[][] board;
	private Boolean max;
	private int whites, blacks;
	private PhatagonState padre;
	private int whitePiece, blackPiece;



	//Constructor of the class.
	public PhatagonState(){
			tablero = new int [7] [7];
			blancas = 14;
			negras = 14;
			max = true;
			padre = this;
	}

	
	public PhatagonState phatagonStateClone(PhatagonState s){
		PhatagonState copy = new PhatagonState();
		copy.setBlancas(s.getBlancas());
		copy.setNegras(s.getNegras());
		copy.setPaadre(s);
		copy.setTurno(!(s.getTurno()));

		for(int i=0, i<7,i++){
			for(int j=0, j<7,j++){
				copy.setTablero(i,j, (s.getTablero())[i][j]));
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
    	// chequeos verticales hacia arriba
    	if (i-1 != 0) {
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
    	if (i+1 != 6) {
    		if (board[i-1][j] == adversaryPiece) {
    			if (board[i-2][j] == myPiece) {
    				board [i+1] [j] = -1;
    				if (max)
    					whites++;
    				else 
    					blacks++;
    			}
    		}
    	}
    	// chequeo horizontal hacia la izquierda
    	if (j-1 != 0) {
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
    	if (j+1 != 6) {
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
	 * @pre. true
	 * @param. a State
	 * @pos. int
	 */	
	public int getBoard(int i, int j){
		return this.board[i][j];
	}

	public void setTablero(int i, int j, int val){
		this.tablero[i][j] = val;
	}
	
	/**
	 * @pre. true
	 * @param. a State
	 * @pos. True sii is a max state.
	 */
	public Boolean isMax(){
		return this.max;
	}

	/**
	 * @pre. S != null & this != null
	 *	@param a State
	 *	@pos. true sii this equals S
	 */
	public Boolen equals(PhatagonState s){
		for(int i=0, i<7,i++){
			for(int j=0, j<7,j++){
				if(((this.getState())[i][j]) != ((s.getState())[i][j])){
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
	public void toString(){
		for(int i=0, i<7,i++){
			for(int j=0, j<7,j++){
					if(j<=5){
						System.out.print((this.getState()[i][j]) + "-");
					}
					else{
						System.out.print(this.getState()[i][j]);
					}
				}
			}
			System.out.println("");
		}
	}


}