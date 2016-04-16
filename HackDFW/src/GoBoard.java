/* GoBoard - 2-D array representation of a Go Board populated
 * by Go pieces.
 * Author: Michael Nelson
*/
import java.util.*;
import java.awt.*;
public class GoBoard 
{   
    private int dimension;
    private GoPiece[][] board;
    
    
    /* Constructor for GoBoard. Takes desired dimension and instantiates board;
     * populating with empty Go Pieces.
     * @param dimension desired dimensionXdimension of board. 
    */
    public GoBoard(int dimension)
    {
        this.dimension = dimension;
        board = new GoPiece[dimension][dimension];
        // Populates board with empty stones
        for (int i = 0; i < dimension; i++)
        {
            for (int q = 0; q < dimension; q++)
            {
                board[i][q] = new GoPiece(0, new Point(i, q));
            }
        }
    }
    
    
    /* Places stone of desired color on desired location on the board if
     * position is unoccupied. Then checks if all stones adjacent to the placed
     * stone have liberties. If not, the captured stones are removed. 
     * @param color - Color of stone.
     * @param position - Desired position of stone.
     * @return 0 = Stone sucessfully placed.
     *         1 = Position occupied
     *         2 = Suicide rule violation
    */
    public int placeStone(int color, Point position)
    {
        int row = (int)position.getX();
        int col = (int)position.getY();
        
        if (board[row][col].getColor() == 0)
        {
            GoPiece stone = new GoPiece(color, position);
            GoPiece[] adjacents = new GoPiece[4];
            
            // Fills array with adjacent pieces from board. If adjacent spot
            // is out of bounds, puts null instead.
            //Checks above
            if (row - 1 >= 0)
            {
                adjacents[0] = board[row - 1][col];
            }
            else 
            {
                adjacents[0] = null;
            }
            
            //Checks right
            if (col + 1 < dimension)
            {
                adjacents[1] = board[row][col + 1];
            }
            else 
            {
                adjacents[1] = null;
            }
            
            //Checks below
            if (row + 1 < dimension)
            {
                adjacents[2] = board[row + 1][col];
            }
            else 
            {
                adjacents[2] = null;
            }
            
            //Checks left
            if (col - 1 >= 0)
            {
                adjacents[3] = board[row][col - 1];
            }
            else 
            {
                adjacents[3] = null;
            }
            stone.setAdjacent(adjacents);
            board[row][col] = stone;
                    
            // Checks each adjacent enemy stone to see if they still have
            // liberties after stone placement. If not, they are captured and 
            // removed.
            for (GoPiece i: adjacents)
            {
                if(i != null)
                {
                    i.updateLiberties();
                    
                    if (i.getColor() == -color && !(i.getHasLiberties()))
                    {
                        removeCapturedStones(i);
                    }
                }
            }
            stone.updateLiberties();
            if (stone.getHasLiberties())
            {
                return 0;
            }
            else
            {
                board[row][col] = new GoPiece(0, position);
                return 2;
            }
        }
        return 1;
    }
    
    
    /* Removes all stones that have no liberties and are not capturing stones
     * @param stone - The stone from which to recursively eliminate stones
     * with no liberties
    */
    private void removeCapturedStones(GoPiece stone)
    {
        int color = stone.getColor();
        stone.setColor(0);
        GoPiece[] adjacents = stone.getAdjacents();
        for (GoPiece i: adjacents)
        {
            if (i.getColor() == color)
            {
                removeCapturedStones(i);
            }
        }
    }
    
    
    /* Provides a string representation of the Go Board
     * @return the string representation of Go Board. 
    */
    public String toString()
    {
        String theBoard = "";
        //for (int y =  dimension - 1; y >= 0; y--)
        for(int row = 0; row < dimension; row ++)
        {
            for (int col = 0; col < dimension; col++)
            {
                theBoard = theBoard + board[row][col] + " ";
            }
            theBoard = theBoard + "\n";
        }
        return theBoard;
    }
}
