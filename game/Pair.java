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

  public Pair(A first, B second) {
    super();
    this.first = first;
    this.second = second;
  }

  public String toString(){ 
    return "(" + first + ", " + second + ")"; 
  }

  public A getFirst() {
    return first;
  }

  public void setFirst(A first) {
    this.first = first;
  }

  public B getSecond() {
    return second;
  }

  public void setSecond(B second) {
    this.second = second;
  }
}