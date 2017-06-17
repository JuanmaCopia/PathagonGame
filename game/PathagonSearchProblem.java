
import java.util.*;


public class PathagonSearchProblem implements AdversarySearchProblem<PathagonState> {
	
	PathagonState initial;

	/** 
	 * Returns the initial state corresponding to the problem. 
	 * Concrete implementations of AdversarySearchProblem must 
	 * implement this routine, to indicate the starting point for 
	 * the (adversary) search.
	 * @return the initial state for the problem.
	 * @pre. true.
	 * @post. the initial state for the problem is returned.  
	 */
    public S initialState() {
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
    public List<S> getSuccessors(S parent) {
    	List<S> successors = new ArrayList<PathagonState>();
    	PathagonState child = pathagonStateClone(parent);
    	// si no posee fichas para jugar retorno el mismo estado
    	if (child.isMax()) {
    		if (child.getBlacks() == 0) {
    			successors.add(child);
    			return successors;
    		}
    	}
    	else {
    		if (child.getWhites() == 0) {
    			successors.add(child);
    			return successors;
    		}	
    	}
    	// si tiene fichas...
    	for (int i= 0; i<6 ; i++) {
    		for (int j = 0; j<6 ; j++) {
    			if (child.emptySquare(i,j)) {
    				if (!child.blockedSquare(i,j)) {
    					child.putPieceIn(i,j);
    					successors.add(child);
    				}
    				else {
    					child.unblockSquare(i,j);
    				}
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
    public boolean end(PhatagonState state){
        return ( ((state.getBlancas()) == 0) || someOneWins(state) || ((state.getNegras()) == 0) );
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
    abstract public int value(S state);
 
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
    abstract public int minValue();
    
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
    abstract public int maxValue();


}