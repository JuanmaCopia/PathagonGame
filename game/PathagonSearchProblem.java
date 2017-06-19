
import java.util.*;


public class PathagonSearchProblem implements AdversarySearchProblem<PathagonState> {
	
	PathagonState initial;

	// Constructor of the class
	public PathagonSearchProblem() {
		this.initial = new PathagonState();
	}

	/** 
	* Returns the initial state corresponding to the problem. 
	* Concrete implementations of AdversarySearchProblem must 
	* implement this routine, to indicate the starting point for 
	* the (adversary) search.
	* @return the initial state for the problem.
	* @pre. true.
	* @post. the initial state for the problem is returned.  
	*/
  public PathagonState initialState() {
  	return initial;
  }
    

	/** 
	* Returns the list of successor states for a given state, in the 
	* context of the current problem. Concrete implementations of 
	* AdversarySearchProblem must implement this routine, to indicate
	* the 'advance' rules (or game rules) for the search.
	* @param state is the state for which its successors are being 
	* computed.
	* @return the list of successor states of state.
	* @pre. state!=null.
	* @post. the list of successor states of state is returned.  
	*/
  public List<PathagonState> getSuccessors(PathagonState parent) {
    List<PathagonState> successors = new ArrayList<PathagonState>();
    if (parent.isMax()) {
    	if (parent.getBlacks() == 0) {
    		successors.add(parent.pathagonStateChild());
    		return successors;
    	}
    }
    else {
    	if (parent.getWhites() == 0) {
    		successors.add(parent.pathagonStateChild());
    		return successors;
    	}
    }
    PathagonState child;
    for (int i= 0; i<7 ; i++) {
    	for (int j = 0; j<7 ; j++) {
    		if (parent.emptySquare(i,j) && (!parent.blockedSquare(i,j))) {
    			child = parent.pathagonStateClone();
    			child.putPieceIn(i,j);
    			successors.add(child);
    		}
    	}
    }
    return successors;
  }
     
	/** 
 	* Indicates whether a given state is an end state, i.e., a 
 	* state with no successors. 
 	* @param state is the state being checked to be an end state.
 	* @return true iff state is an end state.
 	* @pre. state!=null.
 	* @post. true is returned iff state is an end state.  
 	*/
  public boolean end(PathagonState state){
    return ((((state.getWhites()) == 0) && ((state.getBlacks()) == 0)) || (state.blackWins() || state.whiteWins()));
  }

	/** 
	* Computes the value of a given state. If the state is a leaf
	* (end state), then this value is an exact value, and indicates
	* the outcome of the game. If the state is not an end state, then
	* this value is an approximate value. Its estimation plays a
	* crucial role in the performance of search. 
	* @param state is the state for which its value is being computed.
	* @return an integer value, representing the value of the state.
	* This value must be greater than minValue(), and smaller than
	* maxValue().
	* @pre. state!=null.
	* @post. an integer value, representing the value of the state.   
	*/
  public int value(PathagonState state) {
  	Queue<Pair<Integer,Integer>> q = new LinkedList<Pair<Integer,Integer>>();
  	Queue<Pair<Integer,Integer>> q2 = new LinkedList<Pair<Integer,Integer>>();
  	List<Pair<Integer,Integer>> visited = new ArrayList<Pair<Integer,Integer>>();
  	List<Pair<Integer,Integer>> adjacents;
  	Pair<Integer,Integer> p;
  	int k = 0;
  	int blackPiece = state.getBlackPiece();
  	int min = 8;
	  for (int j= 0; j<7 ; j++) {
	  	for (int i = 0; i<7 ; i++) {
	  		if (state.getBoardSquareValue(i,j) == blackPiece) {
	  			p = new Pair<Integer,Integer>(i,j);
					q.add(p);
	  		}
	  	}
	  }
	  int maxCurrentColumnReached = -1;
  	int[] maxValuesFound = new int[16];
  	int aux = -1;
		while (!q.isEmpty()) {
			q2.add(q.poll());
			while (!q2.isEmpty()) {
				p = q2.poll();
				visited.add(p);
				aux = p.getSecond();
				if (aux<min) {
	  			min = aux;
	  		}
				if (aux > maxCurrentColumnReached) {
					maxCurrentColumnReached = aux;
				}
				adjacents = new LinkedList<Pair<Integer,Integer>>();
				adjacents = state.getAdjacents(p,blackPiece);
				for (Pair<Integer,Integer> pair: adjacents) {
					if (!visited.contains(pair) && !q2.contains(pair)) {
						visited.add(pair);
						q2.add(pair);
					}
				}			
			}
			maxValuesFound[k] = maxCurrentColumnReached - min;
			k++;
			min = 8;
	  	maxCurrentColumnReached = -1;
	  	aux = -1;  		
	  }
	  return maxIntOfArray(maxValuesFound);
  }
 

  /** 
	* Computes the maximum number of an array of ints
	* @param an array of ints
	* @return the maximum number of the array.
	* @pre. state!=null.
	* @post. an int value, that is greater or equal to the other ints in the array  
	*/
  public static int maxIntOfArray(int[] array) {
  	int max = -1;
  	for (int i=0; i<array.length ;i++) {
  		if (array[i] > max) {
  			max = array[i];
  		}
  	}
  	return max;
  }


  /** 
	* Indicates the least possible value for a state in the problem.
	* Together with maxValue(), it determines an interval in which 
	* values for states must range.
	* @return an integer value, representing the least possible value
	* for the problem. 
	* This value must be strictly smaller than maxValue().
	* @pre. true.
	* @post. an integer value, representing the least possible value
	* for states, is returned. 
	*/
  public int minValue() {
  	return 0;
  }
    
  /** 
	* Indicates the greatest possible value for a state in the problem.
	* Together with minValue(), it determines an interval in which 
	* values for states must range.
	* @return an integer value, representing the greatest possible value
	* for the problem. 
	* This value must be strictly greater than minValue().
	* @pre. true.
	* @post. an integer value, representing the greatest possible value
	* for states, is returned. 
	*/
  public int maxValue() {
  	return 7;
  }


}