/**
 * Title:        PhatagonState<p>
 * Description:  Class which implements a State of the game<p>         
 * Company:      UNRC<p>
 * @author Juan Manuel Copia, Sebastian Fischer, Gabriel Gonzalez.
 * @version 0.1
 */

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
	* returns a new child of the "this" state with the turn inverted.
	* @pre. this != null.
	* @return. a child of the this state with the turn inverted.
	* @post. the child should be equal to "this" in all the atributes exept in "max".
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
	* returns a new clone of the "this" state.
	* @pre. this != null.
	* @return. a clon of the this state.
	* @post. the clon should be equal to "this".
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
	
	/** 
	* unblock all the squares blocked of the board.
	* @pre. this != null.
	* @post. all previously blocked squares should be unblocked.
	*/	
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
	* sets a player piece (depending who's turn is, can be a black piece or a white one) 
	* in the specified square of the board, and checks if this move capture a opponent piece
	* or not, if it does, the square is blocked. it also has the responsability of decrease
	* and increase the quantity of pieces as necessary, unblock previous blocked squares and shift 
	* the turn of the state.
	* @pre. this != null, the position (i,j) of the board should exist, be empty and not blocked.
	* @param. i is the row number and j is the column number.
	* @post. the "this" state should be consistent after the insertion, that is that the number of pieces
	* from both player are correct, the turn has shifted and if there was a previous blocked square, now is 
	* unblocked.
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
  	board [i][j] = myPiece;
  	unblockAllSquares(); 	
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
  	this.max = !this.max;
  }

  /**
  * check if a square is empty or not (if there is a piece in or not).
	* @pre. this != null, the position (i,j) of the board should exist.
	* @param. i is the row number and j is the column number.
	* @return. true iff the square at (i,j) is empty. 
	* @post. true is returned iff the square at (i,j) is empty.
	*/	
  public boolean emptySquare(int i, int j) {
  	return (this.board[i][j] <= 0);
  }

  /**
  * check if a square is blocked or not.
	* @pre. this != null, the position (i,j) of the board should exist.
	* @param. i is the row number and j is the column number.
	* @return. true iff the square at (i,j) is blocked. 
	* @post. true is returned iff the square at (i,j) is blocked.
	*/	
  public boolean blockedSquare(int i,int j) {
  	return (this.board[i][j] == -1);
  }

  /**
  * unblocks a given square of the board.
	* @pre. this != null, the position (i,j) of the board should exist.
	* @param. i is the row number and j is the column number.
	* @post. the square at row i and column j is unblocked.
	*/	
  public void unblockSquare(int i,int j) {
  	this.board[i][j] = 0;
  }

	/**
	* returns the quantity of white pieces.
	* @pre. this != null.
	* @return. the quantity of white pieces of this state.
	* @post.  the quantity of white pieces is returned
	*/	
	public int getWhites(){
		return this.whites;
	}

	/**
	* returns the quantity of black pieces.
	* @pre. this != null.
	* @return. the quantity of black pieces of this state.
	* @post.  the quantity of black pieces is returned
	*/		
	public int getBlacks(){
	  return this.blacks;
	}

	/**
	* sets the value of the square in a given position.
	* @pre. this != null,  position (i,j) of the board should exist.
	* @param. i is the row number and j is the column number, and value is the new value.
	* @post. the value of the square (i.j) is equal to value.
	*/		
	public void setBoardSquareValue(int i, int j, int value) {
		this.board[i][j] = value;
	}

	/**
	* returns the value of the square at the row i and the at column j of the board.
	* @pre. this != null,  position (i,j) of the board should exist.
	* @param. i is the row number and j is the column number.
	* @return. the value of the square (i,j) of the board.
	* @post. the value of position (i,j) of the board is returned.
	*/	
	public int getBoardSquareValue(int i, int j) {
		return this.board[i][j];
	}
	
	/**
	* returns the value of the blackPiece atribute, it is an int that represent the pieces of the black player
	* @pre. this != null.
	* @return. the value of blackPiece
	* @post. the value of blackPiece is returned
	*/	
	public int getBlackPiece() {
		return this.blackPiece;
	}

	/**
  * check if the white player has won or not. that is if exist a path of white pieces that conects both white sides.
	* @pre. this != null.
	* @return. true iff white player has won.
	* @post. true is returned iff the white player has won .
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
  * check if the black player has won or not. that is if exist a path of black Pieces that conects both black sides.
	* @pre. this != null.
	* @return. true iff black player has won.
	* @post. true is returned iff the black player has won .
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
  * computes the adjacents positions of a given position (square) of the board, represented as pairs of Integers.
	* @pre. this != null, p != null, p should be a existent position of the board.
	* @param.	position is a pair representing the postion of the board for which its adjacents positions are being computed.
	* @return. a list of pairs representing the adjacent positions.
	* @post. the list of the adjacent postions is returned. 
	*/	
	public List<Pair<Integer,Integer>> getAdjacents(Pair<Integer,Integer> position, int playerPiece) {		
		List<Pair<Integer,Integer>> adjacents = new LinkedList<Pair<Integer,Integer>>();
		Integer row = position.getFirst();
		Integer column = position.getSecond();
		if (row != 0) {
			if (board[row-1][column] == playerPiece) 
				adjacents.add(new Pair<Integer,Integer>(row-1,column));	
		}
		if (row != 6) {
			if (board[row+1][column] == playerPiece) 
				adjacents.add(new Pair<Integer,Integer>(row+1,column));
		}
		if (column != 0) {
			if (board[row][column-1] == playerPiece) 
				adjacents.add(new Pair<Integer,Integer>(row,column-1));
		}
		if (column != 6) {
			if (board[row][column+1] == playerPiece) 
				adjacents.add(new Pair<Integer,Integer>(row,column+1));
		}
		return adjacents;
  }
	
	/** 
	* Indicates whether the current state is a max state or not.
	* If the current state is not a 'max' state, then it is assumed
	* to be a min state. 
	* @return true iff 'this' is a max state.
	* @pre. true.
	* @post. true is returned iff 'this' is a max state.
	*/
	public boolean isMax(){
		return this.max;
	}

	/**
	* sets the value of the max atribute in the curren state.
	* @pre. this != null.
	* @param. max is the new value.
	* @post. the value of this.max is equal to the max param.
	*/		
	public void setMax(boolean max){
		this.max = max;
	}

	/**
	* returns the value of the whitePiece atribute, it is an int that represent the pieces of the white player
	* @pre. this != null.
	* @return. the value of whitePiece
	* @post. the value of whitePiece is returned
	*/	
	public int getWhitePiece() {
		return this.whitePiece;
	}

	/** 
	* Checks whether 'this' is equal to another state. 
	* @param other is the state to compare 'this' to.
	* @return true iff 'this' is equal, as a state, to 'other'.
	* @pre. other!=null.
	* @post. true is returned iff 'this' is equal, as a state, 
	* to 'other'.
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
	* Returns a representation as a string of the current state. 
	* @return a string representing the current state.
	* @pre. true.
	* @post. A text representation of the current state is returned.
	*/
	public String toString() {
    String separator = "  ";
    StringBuffer result = new StringBuffer();
    result.append("\n");
    result.append("       WHITE       ");
    result.append("\n");
    for (int k=0; k<8; k++){
    	if(k==0){
    		result.append(k);
    	}
    	else{
    		result.append(" " + k + " ");
    	}
    }
    result.append("\n");
    for (int i = 0; i < 7; i++) {
      for(int j = 0; j < 7; j++){
      	if(j==0){
      		result.append(i+1 + " ");
      	} 	
      	if(board[i][j] == 1) {
          result.append("W");          
        }
        if(board[i][j] == 2) {
          result.append("B");          
        }
        if(board[i][j] == 0) {
          result.append("_");          
        }
        if(board[i][j] == -1) {
          result.append("~");          
        }
        result.append(separator);
      }
      result.setLength(result.length() - separator.length());
      result.append("\n");  
    }
    result.append("       WHITE       ");
    result.append("\n");
    result.append("\n");
    result.append("CANTIDAD DE FICHAS BLANCAS: "+this.whites);
    result.append("\n");
    result.append("CANTIDAD DE FICHAS NEGRAS: "+this.blacks);
    result.append("\n");
    result.append("\n");
    result.append("------------------------------------------------------------------------------");
    return result.toString();
	}
}