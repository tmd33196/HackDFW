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
    private Stack<Point> positions;
    private int movecount;
    
    
    /* Constructor for GoBoard. Takes desired dimension and instantiates board;
     * populating with empty Go Pieces.
     * @param dimension desired dimensionXdimension of board. 
    */
    public GoBoard(int dimension)
    {
        this.dimension = dimension;
        board = new GoPiece[dimension][dimension];
        this.positions = new Stack<>();
        movecount = 0;
        // Populates board with empty stones
        for (int i = 0; i < dimension; i++)
        {
            for (int q = 0; q < dimension; q++)
            {
                board[i][q] = new GoPiece(0, new Point(i, q));
            }
        }
        positions.push(new Point(-1, -1));
    }
    
    
    /* Places stone of desired color on desired location on the board if
     * position is unoccupied. Then checks if all stones adjacent to the placed
     * stone have liberties. If not, the captured stones are removed. 
     * @param color - Color of stone.
     * @param position - Desired position of stone.
     * @return 0 = Stone sucessfully placed.
     *         1 = Position occupied
     *         2 = Suicide rule violation
     *         3 = Ko Rule violation
    */
    public int placeStone(int color, Point position)
    {         
        int row = (int)position.getX();
        int col = (int)position.getY();
        boolean equal = false;
        
        if (board[row][col].getColor() == 0)
        {
            if (movecount >= 3)
            {
                Point previousstate = positions.pop();
                Point morepreviousstate = positions.pop();

                equal = morepreviousstate.x == row && morepreviousstate.y == col;
                if (!(equal))
                {
                    positions.push(morepreviousstate);
                    positions.push(previousstate);
                }
                else
                {
                    return 3;
                }
            }
            
            GoPiece stone = new GoPiece(color, position);
            GoPiece[] adjacents = new GoPiece[4];
            
            // Fills array with adjacent pieces from board. If adjacent spot
            // is out of bounds, puts null instead.
            //Checks above
            if (row - 1 >= 0)
            {
                adjacents[0] = board[row - 1][col];
                board[row - 1][col].setSingleAdjacent(stone, 2);
            }
            else 
            {
                adjacents[0] = null;
            }
            
            //Checks right
            if (col + 1 < dimension)
            {
                adjacents[1] = board[row][col + 1];
                board[row][col + 1].setSingleAdjacent(stone, 3);
            }
            else 
            {
                adjacents[1] = null;
            }
            
            //Checks below
            if (row + 1 < dimension)
            {
                adjacents[2] = board[row + 1][col];
                board[row + 1][col].setSingleAdjacent(stone, 0);
            }
            else 
            {
                adjacents[2] = null;
            }
            
            //Checks left
            if (col - 1 >= 0)
            {
                adjacents[3] = board[row][col - 1];
                board[row][col - 1].setSingleAdjacent(stone, 1);
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
                positions.push(new Point(row, col));
                movecount++;
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
            if (i != null && i.getColor() == color)
            {
                removeCapturedStones(i);
            }
        }
    }
    
    
    /* Returns board as a 2-d array
     * @return board instance field
    */
    public GoPiece[][] getBoard()
    {
        return board;
    }
    
    
    /* Returns stack that records positions
     * @return stack
    */
    public Stack<Point> getPositions()
    {
        return positions;
    }
    
    //Attempts to score the game, returns the player number who won
    public int score()
    {
        int[] scores = { 0, 0 }; //The two players scores
        Point score; //The score point
        
        //Loops through the array
        for(int a = 0; a < dimension; a ++)
        {
            for(int b = 0; b < dimension; b ++)
            {
                //If it has not been checked and is empty
                if(!board[a][b].isChecked() && board[a][b].getColor() == 0)
                {
                    //Call the getScore method
                    score = getScore(new Point(a, b), new Point(0, 0));
                    //System.out.println("getScore returned" + score + " from point " + a + " " + b);
                    //If the score is valid
                    if(score.y > 0)
                    {
                        //Assigne the score to the correct player
                        if(score.x == 1)
                            scores[0] += score.y;
                        else if(score.x == -1)
                            scores[1] += score.y;
                    }
                }
            }
        }
        
        System.out.println("Player 1 has a score of: " + scores[0]);
        System.out.println("Player 2 has a score of: " + scores[1]);
        
        //Compares score and returns accordingly
        if(scores[0] > scores[1])
            return 0;
        else if(scores[0] < scores[1])
            return 1;
        else
            return -1;
    }
    
    //Returns the score
    public Point getScore(Point pos, Point p)
    {
        //Bounds checking
        if(pos.x >= 0 && pos.x < dimension && pos.y >= 0 && pos.y < dimension && !board[pos.x][pos.y].isChecked())
        {
            //Gets the piece
            GoPiece piece = board[pos.x][pos.y];
            
            //If it is an empty piece then mark it checked
            if(piece.getColor() == 0) piece.setChecked(true);
            //System.out.println("\t" + piece + " " + piece.getPosition());
            //Switch on the color of the piece
            switch(piece.getColor())
            {
                //Piece is empty
                case 0:
                    //Increase the score and recursively call all surrounding
                    p.y += 1;
                    getScore(new Point(pos.x + 1, pos.y), p);
                    getScore(new Point(pos.x - 1, pos.y), p);
                    getScore(new Point(pos.x, pos.y + 1), p);
                    getScore(new Point(pos.x, pos.y - 1), p);
                    break;
                //If it is either players
                case 1:
                case -1:
                    //If this is the first color found, set x to it
                    if(p.x == 0)
                        p.x = piece.getColor();
                    //If the color is different the previously found one then it is neutral
                    else if(piece.getColor() != p.x)
                        p.y = -1000;
                    break;
            }
        }
        //Return the point
        return p;
    }
    
    /* Provides a string representation of the Go Board
     * @return the string representation of Go Board. 
    */
    public String toString()
    {
        String theBoard = "";
        
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
    
    
    /* Prints a string representation of the paramter
     * @param stones - The 2-D array to be printed
    */
    public void print(GoPiece[][] stones)
    {
        String theBoard = "";
        
        for(int row = 0; row < dimension; row ++)
        {
            for (int col = 0; col < dimension; col++)
            {
                theBoard = theBoard + stones[row][col] + " ";
            }
            theBoard = theBoard + "\n";
        }
        System.out.println(theBoard);
    }
}
