import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A Board represents the current state of the game. Boards know their dimension, 
 * the collection of tiles that are inside the current flooded region, and those tiles 
 * that are on the outside.
 * 
 * @author <Sergio Perez>
 */

public class Board {
  private Map<Coord, Tile> inside, outside;
  private int size;
  int blue = 0, cyan = 0, pink = 0, red = 0, yellow = 0;
  
  /**
   * Constructs a square game board of the given size, initializes the list of 
   * inside tiles to include just the tile in the upper left corner, and puts 
   * all the other tiles in the outside list.
   */
  public Board(int size) {
    // A tile is either inside or outside the current flooded region.
    inside = new HashMap<>();
    outside = new HashMap<>();
    this.size = size;
    for (int y = 0; y < size; y++)
      for (int x = 0; x < size; x++) {
        Coord coord = new Coord(x, y);
        outside.put(coord, new Tile(coord));
      }
    // Move the corner tile into the flooded region and run flood on its color.
    Tile corner = outside.remove(Coord.ORIGIN);
    inside.put(Coord.ORIGIN, corner);
    flood(corner.getColor());
  }
  
  /**
   * Returns the tile at the specified coordinate.
   */ 
  public Tile get(Coord coord) {
    if (outside.containsKey(coord))
      return outside.get(coord);
    return inside.get(coord);
  }
  
  /**
   * Returns the size of this board.
   */
  public int getSize() {
    return size;
  }
  
  /*
   *
   * 
   * Returns true iff all tiles on the board have the same color.
   * 
   * Checks if the size of the outside of the flooded area is 0. If it is returns true.
   */
  public boolean fullyFlooded() {
    if (outside.size() == 0)
    	return true;
    return false;
  }
  
  /*
   * 
   * 
   * Updates this board by changing the color of the current flood region 
   * and extending its reach.
   * 
   * Starts off with changing the color of the origin tile. From there, for each coord c in the neighbors, 
   * it sees if it is in the outside and also the same as the color selected. If it is the tile on
   * the outside is removed and then out onto the inside of the flooded area with the selected color. 
   */
	public void flood(WaterColor color) {

		for (int y = 0; y < this.inside.size(); y++)
			for (int x = 0; x < this.inside.size(); x++) {

				Coord coord = new Coord(x, y);

				if (this.inside.containsKey(coord)) {

					this.get(coord).setColor(color);

					for (Coord c : coord.neighbors(this.size)) {

						if (this.outside.containsKey(c) && this.outside.get(c).getColor().equals(color)) {

							// Increases the count of the colors with relation
							// to the how many neighbors at the given moment
							// Utilized to help support the suggest() function.
							WaterColor outerColor = this.outside.get(c).getColor();
							if (outerColor.equals(WaterColor.BLUE))
								blue++;
							if (outerColor.equals(WaterColor.CYAN))
								cyan++;
							if (outerColor.equals(WaterColor.PINK))
								pink++;
							if (outerColor.equals(WaterColor.RED))
								red++;
							if (outerColor.equals(WaterColor.YELLOW))
								yellow++;
							
							Tile tile = this.outside.remove(c);
							this.inside.put(c, tile);
							this.inside.get(c).setColor(color);

							WaterColor innerColor = this.inside.get(c).getColor();
							if (innerColor.equals(WaterColor.BLUE))
								blue = 0;
							if (innerColor.equals(WaterColor.CYAN))
								cyan = 0;
							if (innerColor.equals(WaterColor.PINK))
								pink = 0;
							if (innerColor.equals(WaterColor.RED))
								red = 0;
							if (innerColor.equals(WaterColor.YELLOW))
								yellow = 0;

						}
					}
				}
			}
	}
  
  
  
  
  /**
   *
   * 
   * Explore a variety of algorithms for handling a flood. Use the same interface 
   * as for flood above, but add an index so your methods are named flood1,
   * flood2, ..., and so on. Then, use the batchTest() tool in Driver to run game
   * simulations and then display a graph showing the run times of all your different 
   * flood functions. Do not delete your flood code attempts. For each variety of 
   * flood, including the one above that you eventually settle on, write a comment
   * that describes your algorithm in English. For those implementations that you
   * abandon, state your reasons.
  
  public void flood1(WaterColor color) {

  }
  
  public void flood2(WaterColor color) {
    
  } 
   */
	
	//flood1
	
	public void flood1(WaterColor color) {

		for (Tile currentT : inside.values()) {
			// System.out.println(currentT.getColor());
			currentT.setColor(color);
		}
		for (int y = 0; y < this.inside.size(); y++)
			for (int x = 0; x < this.inside.size(); x++) {
				Coord coord = new Coord(x, y);
				if (this.inside.containsKey(coord)) {

					for (Coord c : coord.neighbors(this.size)) {
						// System.out.print(coord.neighbors(this.size));
						if (this.outside.containsKey(c) && this.outside.get(c).getColor().equals(color)) {
							// if(color.equals(this.outside.get(c).getColor())){
							Tile tile = this.outside.remove(c);
							this.inside.put(c, tile);
							this.inside.get(c).setColor(color);

						}
					}

				}
			}
	}
  
	//flood2
	public void flood2(WaterColor color) {
		for (Tile currentT : inside.values()) {
			// System.out.println(currentT.getColor());
			currentT.setColor(color);
		}
		for (int y = 0; y < this.inside.size(); y++)
			for (int x = 0; x < this.inside.size(); x++) {
				Coord coord = new Coord(x, y);
				if (this.inside.containsKey(coord)) {

					for (Coord c : coord.neighbors(this.size)) {
						//System.out.print(coord.neighbors(this.size));
						if (this.outside.containsKey(c) && this.outside.get(c).getColor().equals(color)) {
							// if(color.equals(this.outside.get(c).getColor())){
							Tile tile = this.outside.remove(c);
							this.inside.put(c, tile);
							this.inside.get(c).setColor(color);

						}
					}

				}
			}
	}
  
  
  /*
   * 
   * 
   * Returns the "best" GameColor for the next move. 
   * 
   * Modify this comment to describe your algorithm. Possible strategies to pursue 
   * include maximizing the number of tiles in the current flooded region, or maximizing
   * the size of the perimeter of the current flooded region.
   * 
   * Within the flood function there is a count for each color that increases with regards to how many of that 
   * color are on the outside of the flooded area.
   * Then within suggest it takes those counts and compares them, it then returns the max as the suggested color
   * choice.
   */
  
	public WaterColor suggest() {

		WaterColor initialSuggest = WaterColor.BLUE;
		for (int y = 0; y < this.inside.size(); y++)
			for (int x = 0; x < this.inside.size(); x++) {

				Coord coord = new Coord(x, y);

				if (this.inside.containsKey(coord)) {
					//Tile currentT = this.inside.get(coord);

					for (Coord c : coord.neighbors(this.size)) {
						// WaterColor innerColor =
						// this.inside.get(c).getColor();
						if (this.outside.containsKey(c)) {
							{
								WaterColor outerColor = this.outside.get(c).getColor();
								if (outerColor.equals(WaterColor.BLUE))
									blue++;
								if (outerColor.equals(WaterColor.CYAN))
									cyan++;
								if (outerColor.equals(WaterColor.PINK))
									pink++;
								if (outerColor.equals(WaterColor.RED))
									red++;
								if (outerColor.equals(WaterColor.YELLOW))
									yellow++;
								if ((blue > cyan) && (blue > pink) && (blue > red) && (blue > yellow))
									initialSuggest = WaterColor.BLUE;
								if ((cyan > pink) && (cyan > red) && (cyan > yellow) && (cyan > blue))
									initialSuggest = WaterColor.CYAN;
								if ((pink > red) && (pink > yellow) && (pink > blue) && (pink > cyan))
									initialSuggest = WaterColor.PINK;
								if ((red > yellow) && (red > blue) && (red > cyan) && (red > pink))
									initialSuggest = WaterColor.RED;
								if ((yellow > blue) && (yellow > cyan) && (yellow > pink) && (yellow > red))
									initialSuggest = WaterColor.YELLOW;

							}

						}
					}
				}
			}

		return initialSuggest;

	}
  
  /**
   * Returns a string representation of this board. Tiles are given as their
   * color names, with those inside the flooded region written in uppercase.
   */ 
  public String toString() {
    StringBuilder ans = new StringBuilder();
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        Coord curr = new Coord(x, y);
        WaterColor color = get(curr).getColor();
        ans.append(inside.containsKey(curr) ? color.toString().toUpperCase() : color);
        ans.append("\t");
      }
      ans.append("\n");
    }
    return ans.toString();
  }
  
  /*
   * Simple testing.
   * 
   * I put some tests for the three flood functions in here since I was unsure how to test them with asserts
   * First I have random boards printed out from sizes 1 to 5. Then after they have been printed I run each 
   * flood function on that board with a hard coded Blue as the selected color. From there the test prints
   * the newly flooded boards with blue being properly changed in each board. 
   */
  public static void main(String... args) {
    // Print out boards of size 1, 2, ..., 5
    int n = 5;
    for (int size = 1; size <= n; size++) {
      Board someBoard = new Board(size);
      System.out.println(someBoard);
      someBoard.flood(WaterColor.BLUE); // this is used to to test the flood functions with a hard coded blue as the selected color.
      System.out.println("Flood:");
      System.out.println(someBoard); 
      someBoard.flood1(WaterColor.BLUE);
      System.out.println("Flood1:");
      System.out.println(someBoard);
      someBoard.flood2(WaterColor.BLUE);
      System.out.println("Flood2:");
      System.out.println(someBoard);
    } 
  }
}






