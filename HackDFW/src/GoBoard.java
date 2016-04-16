/* GoBoard - 2-D array representation of a Go Board populated
 * by Go pieces.
 * Author: Michael Nelson
*/
public class GoBoard 
{   
    private GoPiece[][] board;
    
    
    /* Constructor for GoBoard.
     * @param dimension the 
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
}
