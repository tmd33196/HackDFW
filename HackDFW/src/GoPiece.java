/*
 * GoPieces class that will hold the position, color, liberties, and adjacent pieces
 * Author: Tyler
 */

import java.awt.Point;

public class GoPiece
{
    
    private GoPiece[] adjacent;     //Array of adjacent goPieces, [top, right, bottom, left]
    private Point position;         //Position of the piece
    private boolean hasLiberties;   //If the piece has any liberties
    private int numLiberties;       //The number of liberties the piece has
    private int color;              //The color of the piece
    
    //Constructor that accepts a color and a position
    public GoPiece(int _color, Point _position)
    {
        color = _color;
        position = _position;
    }
    
    //Returns the position of the piece
    public Point getPosition() { return position; }
    
    //Returns the color of the piece
    public int getColor() { return color; }
    
    //Sets the color of the piece
    public void setColor(int _color) { color = _color; }
    
    //Gets the number of liberties that this piece has
    public int getNumLiberties() { return numLiberties; }
    
    //Sets the number of liberties of the piece
    public void setNumLiberties(int _numLiberties)
    {
        numLiberties = _numLiberties;
        if (numLiberties == 0)
            setHasLiberties(false);
    }
    
    //Sets if there are any liberties left
    public void setHasLiberties(boolean _hasLiberties) { hasLiberties = _hasLiberties; }
    
    //Returns if there are any liberties
    public boolean getHasLiberties() { return hasLiberties; }
    
    
    /* Sets adjacent spots with desired value.
     * @param adjacents - Array of desired adjacent values
     * Author: Michael Nelson
    */
    public void setAdjacent(GoPiece[] adjacents)
    {
        adjacent = adjacents;
    }

}
