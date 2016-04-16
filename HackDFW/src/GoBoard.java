/* GoBoard - 2-D array representation of a Go Board populated
 * by Go pieces.
 * Author: Michael Nelson
*/
import java.util.*;
import java.awt.*;
public class GoBoard 
{   
    private GoPiece[][] board;
    
    
    /* Constructor for GoBoard. Takes desired dimension and instantiates board;
     * populating with empty Go Pieces.
     * @param dimension desired dimensionXdimension of board. 
    */
    public GoBoard(int dimension)
    {
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
     * return whether or not stone was placed. 
    */
    public boolean placeStone(int color, Point location)
    {
        
    }
    
    
    /* Removes all stones that have no liberties and are not capturing stones
     * @param stone - The stone from which to recursively eliminate stones
     * with no liberties
    */
    public void removeCapturedStones(GoPiece stone)
    {
        
    }
}
