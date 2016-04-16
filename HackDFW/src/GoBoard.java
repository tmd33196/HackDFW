/* GoBoard - 2-D array representation of a Go Board populated
 * by Go pieces.
 * Author: Michael Nelson
*/
import java.util.*;
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
        for (int i = 0; i < dimension; i++)
        {
            for (int q = 0; q < dimension; q++)
            {
                board[i][q] = new GoPiece(0, new Point(i, q));
            }
        }
    }
    
    
    /* Places stone of desired color on desired location on the board.
     * @param color - Color of stone.
     * @param location - Location of stone placement.
    */
    public void placeStone(GoPiece stone)
    {
        int x = stone.getPosition.getX();
        int y = stone.getPosition.getY();
        board[x][y] = stone;
    }
}
