/**
 * Title:        Pair<p>
 * Description:  Class which implements a Pair of elements (a position on the board)<p>         
 * Company:      UNRC<p>
 * @author Juan Manuel Copia, Sebastian Fischer, Gabriel Gonzalez.
 * @version 0.1
 */

public class Pair<A, B> {
    private A first;
    private B second;

    //Constructor of the class.
    public Pair(A first, B second) {
        super();
        this.first = first;
        this.second = second;
    }

    public int hashCode() {
        int hashFirst = first != null ? first.hashCode() : 0;
        int hashSecond = second != null ? second.hashCode() : 0;
        return (hashFirst + hashSecond) * hashSecond + hashFirst;
    }


    /** 
    * Compare two Pair objects.
    * @return true if the two objects are equal, false if they are not.
    * @pre. true.
    * @post. the value of compairing the equallity of the two objects is returned.  
    */
    public boolean equals(Object other) {
        if (other instanceof Pair) {
            Pair otherPair = (Pair) other;
            return 
            ((  this.first == otherPair.first ||
                ( this.first != null && otherPair.first != null &&
                  this.first.equals(otherPair.first))) &&
             (  this.second == otherPair.second ||
                ( this.second != null && otherPair.second != null &&
                  this.second.equals(otherPair.second))) );
        }

        return false;
    }

    /** 
    * Returns a representation as a string of the Pair object. 
    * @return a string representing the Pair object.
    * @pre. true.
    * @post. A text representation of the current object is returned.
    */
    public String toString()
    { 
           return "(" + first + ", " + second + ")"; 
    }

    /**
    * returns the value of the first atribute.
    * @pre. this != null.
    * @return. the value of first
    * @post. the value of first is returned
    */  
    public A getFirst() {
        return first;
    }

    /**
    * sets the value of the first atribute in the current pair.
    * @pre. this != null.
    * @param. first is the new value.
    * @post. the value of this.first is equal to the first param.
    */      
    public void setFirst(A first) {
        this.first = first;
    }

    /**
    * returns the value of the second atribute.
    * @pre. this != null.
    * @return. the value of second
    * @post. the value of second is returned
    */  
    public B getSecond() {
        return second;
    }

    /**
    * sets the value of the second atribute in the current pair.
    * @pre. this != null.
    * @param. second is the new value.
    * @post. the value of this.second is equal to the second param.
    */ 
    public void setSecond(B second) {
        this.second = second;
    }
}