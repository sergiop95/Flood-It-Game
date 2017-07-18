import java.util.ArrayList;
import java.util.List;

/**
 * Coord represents an (x,y)-coordinate on a 2D board. The origin, (0,0) is
 * assumed to be in the upper left corner. X-coordinates increase from left
 * to right. Y-coordinates increase from top to bottom. Operations are provided
 * to easily locate neighboring coordinates (in the NSEW compass directions)
 * on a board.
 * 
 * @author <Sergio Perez>
 */

public class Coord implements Comparable<Coord> {
  /**
   *  The upper left corner of the board.
   */
  public static Coord ORIGIN = new Coord(0, 0);
  
  private int x, y;

  /**
   * Constructs a new Coord that is a copy of the given Coord.
   */
  public Coord(Coord coord) {
    this(coord.x, coord.y);
  }

  /**
   * Constructs a new Coord representing (x,y).
   */
  public Coord(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the Coord that is directly above (i.e., north of) this one.
   */
  public Coord up() {
    return new Coord(x, y - 1);
  }

  /**
   * Returns the Coord that is directly below (i.e., south of) this one.
   */
  public Coord down() {
    return new Coord(x, y + 1);
  }

  /**
   * Returns the Coord that is immediately to the left (i.e., west)
   * of this one.
   */
  public Coord left() {
    return new Coord(x - 1, y);
  }

  /**
   * Returns the Coord that is immediately to the right (i.e., east)
   * of this one.
   */
  public Coord right() {
    return new Coord(x + 1, y);
  }

  /*
   * 
   * 
   * Returns true iff this Coord is visible on a board of the given size.
   * If either the x or y coord is greater than the size, return false.
   */
  public boolean onBoard(int size) {
    if ((this.x > size) || (this.y > size) || this.x < 0 || this.y < 0)
	  return false;
    return true;
  }

  /*
   *
   * 
   * Returns a list of the immediate board coordinates of this Coord's north,
   * south, east, and west neighbors.
   * 
   * Large number of various if statements that print neighboring coords with regards to the 
   * location of the current coord and also with relation to the size of the board. 
   */
  public List<Coord> neighbors(int size) {
	  Coord upNeighbor = this.up();
	  Coord downNeighbor = this.down();
	  Coord leftNeighbor = this.left();
	  Coord rightNeighbor = this.right();
	  
	  List<Coord> neighborCoords = new ArrayList<Coord>();
	  
	  if(!onBoard(size))
		  return neighborCoords;
	  if (this.y > 0)
		  neighborCoords.add(upNeighbor);
	  if (this.y < size - 1)
		  neighborCoords.add(downNeighbor);
	  if (this.x > 0)
		  neighborCoords.add(leftNeighbor);
	  if (this.x < size - 1)
		  neighborCoords.add(rightNeighbor);
	  /*
	  if (size == 1) {
		  return neighborCoords;
	  }
	   
	  if((this.x < 0 || this.x >= size) || (this.y < 0 || this.y >= size)) {	
			 return neighborCoords;
	  } 
	  
	  if(!onBoard(size)) {
		  return neighborCoords;
	  }
	  
	  if((this.x == 0) && (this.y == 0)){ //upper left coord
		 neighborCoords.add(rightNeighbor);
	  	 neighborCoords.add(downNeighbor);	
	  	 return neighborCoords;
	  } 
	  
	  if((this.x == size - 1) && (this.y == 0)){ //upper right coord
			 neighborCoords.add(leftNeighbor);
		  	 neighborCoords.add(downNeighbor);	
		  	 return neighborCoords;
		  } 
	  
	  if((this.x == size - 1) && (this.y == size - 1)){ //bottom right coord
			 neighborCoords.add(leftNeighbor);
		  	 neighborCoords.add(upNeighbor);	
		  	 return neighborCoords;
		  } 
	  
	  if((this.x == 0) && (this.y == size - 1)){ //bottom left coord
			 neighborCoords.add(upNeighbor);
		  	 neighborCoords.add(rightNeighbor);	
		  	 return neighborCoords;
		  } 
	  
	  if(this.x == 0) { // left hand border coords 
		 neighborCoords.add(upNeighbor);
		 neighborCoords.add(rightNeighbor);
	  	 neighborCoords.add(downNeighbor);	
	  	 return neighborCoords;
	  }
	  
	  if(this.y == 0) { // top border coords
		 neighborCoords.add(leftNeighbor);
		 neighborCoords.add(rightNeighbor);
	  	 neighborCoords.add(downNeighbor);	
		 return neighborCoords;
	  }
	  
	  if(this.x == size - 1) { // right border coords
			 neighborCoords.add(leftNeighbor);
			 neighborCoords.add(upNeighbor);
		  	 neighborCoords.add(downNeighbor);	
			 return neighborCoords;
		  }
	  
	  if(this.y == size - 1) { // bottom border coords
			 neighborCoords.add(leftNeighbor);
			 neighborCoords.add(upNeighbor);
		  	 neighborCoords.add(rightNeighbor);	
			 return neighborCoords;
		  }
	  if((this.x > 0 && this.x < size - 1) && (this.y > 0 && this.y < size - 1)) { //any coords not on a border
		  	 neighborCoords.add(leftNeighbor);
			 neighborCoords.add(upNeighbor);
		  	 neighborCoords.add(rightNeighbor);
		  	 neighborCoords.add(downNeighbor);	
			 return neighborCoords;
	  } */
	  
	  return neighborCoords;
	
	 
  }
  
  /**
   * Returns true iff the (x,y)-coordinates of the given object match this Coord's
   * (x,y)-coordinates.
   */
  public boolean equals(Object obj) {
    if (obj instanceof Coord) {
      Coord that = (Coord) obj;
      return that.x == this.x && that.y == this.y;
    }
    return false;
  }

  /**
   * Coords are ordered first on the x-coordinate and then on the y-coordinate.
   */
  public int compareTo(Coord that) {
    if (this.x == that.x)
      return this.y - that.y;
    return this.x - that.x;
  }

  /**
   * Returns the x-coordinate.
   */
  public int getX() {
    return x;
  }

  /**
   * Returns the y-coordinate.
   */
  public int getY() {
    return y;
  }

  /*
   * 
   * 
   * Pre-hashing: pack this coordinate into an int, so that the key space is
   * as uniformly distributed among the range of integers as possible.
   *
   *
   * difference of 1056 between x-coord
   * EX: (5,5) =  5445, (6,5) = 6501
   * difference of 33 between y-coord
   * EX: (5, 5) = 5445, (5, 6) = 5478
   * 
   * Basically for the x-coord is multiplied by 1056 and added to the y-coord mutiplied by 33/
   * The sum is the hashcode for the coordinate. 
   */
  
  
  public int hashCode() { 									// difference of 1056 between x-coord
    int result  = (int) (this.x ^ (this.x << 5));    		// EX: (5,5) =  5445, (6,5) = 6501
    result = (result << 5) + (int) (this.y ^ (this.y << 5)); // difference of 33 between y-coord
    return result;											// EX: (5, 5) = 5445, (5, 6) = 5478
  }


  /**
   * Returns this Coord as a string of the form (x, y).
   */
  public String toString() {
    return "(" + x + ", " + y + ")"; 
  }
  
  /**
   * Simple testing.
   */
  public static void main(String... args) {
    Coord someCoord = new Coord(2, 1);
    System.out.println("someCoord = " + someCoord);
    System.out.println("someCoord.hashCode() = " + someCoord.hashCode());
    System.out.println("someCoord.onBoard(4) = " + someCoord.onBoard(4));
    System.out.println("neighbors on a 3x3 board = " + someCoord.neighbors(3));
    System.out.println("neighbors on a 4x4 board = " + someCoord.neighbors(4));   
    System.out.println();
    
    someCoord = ORIGIN;
    System.out.println("someCoord = " + someCoord);
    System.out.println("someCoord.hashCode() = " + someCoord.hashCode());
    System.out.println("someCoord.onBoard(3) = " + someCoord.onBoard(3));
    System.out.println("neighbors on a 3x3 board = " + someCoord.neighbors(3));
    System.out.println("neighbors on a 1x1 board = " + someCoord.neighbors(1));   
    System.out.println();

    someCoord = new Coord(5, 5);
    System.out.println("someCoord = " + someCoord);
    System.out.println("someCoord.hashCode() = " + someCoord.hashCode());
    System.out.println("someCoord.onBoard(5) = " + someCoord.onBoard(5));
    System.out.println("neighbors on a 3x3 board = " + someCoord.neighbors(3));
    System.out.println("neighbors on a 6x6 board = " + someCoord.neighbors(6));
    System.out.println();
    
    someCoord = new Coord(6, 5);
    System.out.println("someCoord = " + someCoord);
    System.out.println("someCoord.hashCode() = " + someCoord.hashCode());
    System.out.println("someCoord.onBoard(5) = " + someCoord.onBoard(5));
    System.out.println("neighbors on a 3x3 board = " + someCoord.neighbors(3));
    System.out.println("neighbors on a 6x6 board = " + someCoord.neighbors(6));
    System.out.println();
    
    someCoord = new Coord(0, 2); // testing coord on left border
    System.out.println("someCoord = " + someCoord);
    System.out.println("someCoord.hashCode() = " + someCoord.hashCode());
    System.out.println("someCoord.onBoard(5) = " + someCoord.onBoard(5));
    System.out.println("neighbors on a 3x3 board = " + someCoord.neighbors(3));
    System.out.println("neighbors on a 6x6 board = " + someCoord.neighbors(6));
    System.out.println();
    
    someCoord = new Coord(3, 0); // testing coord on top border
    System.out.println("someCoord = " + someCoord);
    System.out.println("someCoord.hashCode() = " + someCoord.hashCode());
    System.out.println("someCoord.onBoard(5) = " + someCoord.onBoard(5));
    System.out.println("neighbors on a 3x3 board = " + someCoord.neighbors(3));
    System.out.println("neighbors on a 6x6 board = " + someCoord.neighbors(6));
    System.out.println();
    
    someCoord = new Coord(6, 2); // testing coord on right border
    System.out.println("someCoord = " + someCoord);
    System.out.println("someCoord.hashCode() = " + someCoord.hashCode());
    System.out.println("someCoord.onBoard(5) = " + someCoord.onBoard(5));
    System.out.println("neighbors on a 3x3 board = " + someCoord.neighbors(3));
    System.out.println("neighbors on a 6x6 board = " + someCoord.neighbors(6));
    System.out.println();
    
    someCoord = new Coord(3, 6); // testing coord on bottom border
    System.out.println("someCoord = " + someCoord);
    System.out.println("someCoord.hashCode() = " + someCoord.hashCode());
    System.out.println("someCoord.onBoard(5) = " + someCoord.onBoard(5));
    System.out.println("neighbors on a 3x3 board = " + someCoord.neighbors(3));
    System.out.println("neighbors on a 6x6 board = " + someCoord.neighbors(6));
    System.out.println();
    
    someCoord = new Coord(3, 4); // testing coord on bottom border
    System.out.println("someCoord = " + someCoord);
    System.out.println("someCoord.hashCode() = " + someCoord.hashCode());
    System.out.println("someCoord.onBoard(5) = " + someCoord.onBoard(5));
    System.out.println("neighbors on a 3x3 board = " + someCoord.neighbors(3));
    System.out.println("neighbors on a 6x6 board = " + someCoord.neighbors(6));
    System.out.println();
  }
}
