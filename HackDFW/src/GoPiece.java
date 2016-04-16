/*
 * GoPieces class that will hold the position, color, liberties, and adjacent pieces
 * Author: Tyler
 */

import java.awt.Point;

public class GoPiece
{
    
    private GoPiece[] adjacent;
    private Point position;
    private boolean hasLiberties;
    private int numLiberties;
    private int color;
    
    public GoPiece(int _color, Point _position)
    {
        color = _color;
        position = _position;
    }
    
    public Point getPosition()
    {
        return position;
    }
    
    public int getColor()
    {
        return color;
    }
    
    public void setColor(int _color)
    {
        color = _color;
    }
    
    public int getNumLiberties()
    {
        return numLiberties;
    }
    
    public void setNumLiberties(int _numLiberties)
    {
        numLiberties = _numLiberties;
        if (numLiberties == 0)
            setHasLiberties(false);
    }
    
    public void setHasLiberties(boolean _hasLiberties)
    {
        hasLiberties = _hasLiberties;
    }
    
    public boolean getHasLiberties()
    {
        return hasLiberties;
    }

}
