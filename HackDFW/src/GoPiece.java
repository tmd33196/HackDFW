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
    private boolean checked;        //If the piece has been checking in updateLiberties
    private int color;              //The color of the piece
    
    //Constructor that accepts a color and a position
    public GoPiece(int _color, Point _position)
    {
        color = _color;
        position = _position;
        
        adjacent = new GoPiece[4];
        //hasLiberties = updateLiberties();
        checked = false;
        hasLiberties = false;
    }
    
    //Returns the position of the piece
    public Point getPosition() { return position; }
    
    //Returns the color of the piece
    public int getColor() { return color; }
    
    //Sets the color of the piece
    public void setColor(int _color) { color = _color; }
    
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
    
    
    /* Sets the specific adjacent posiiton with desire value
     * @param stone - The adjacent stone to be set
     * @param key - Position in adjacent array the stone will be placed in
     * Author: Michael Nelson
    */
    public void setSingleAdjacent(GoPiece stone, int key)
    {
        adjacent[key] = stone;
    }
    
    
    /* Returns the array of adjacent Go Pieces
     * @return array of Go Pieces
     * Author: Michael Nelson
    */
    public GoPiece[] getAdjacents()
    {
        return adjacent;
    }
    
    //Updates the liberties.  If any connected ally piece has a liberty then all have liberty
    public boolean updateLiberties()
    {
        if(color == 0) return false;
        System.out.println("Beginning of updateLiberties" + position + " " + this.toString() + " " + hasLiberties);
        checked = true; //This piece has been checked
        boolean adjacentLiberties = false; //If there are any adjacent liberties
        setHasLiberties(adjacentLiberties);
        
        //If any of the adjacent pieces are 0 then this piece has a liberty
        if((adjacent[0] != null && adjacent[0].getColor() == 0) 
                || (adjacent[1] != null && adjacent[1].getColor() == 0) 
                || (adjacent[2] != null && adjacent[2].getColor() == 0)
                || (adjacent[3] != null && adjacent[3].getColor() == 0))
        {
            adjacentLiberties = true;
            setHasLiberties(adjacentLiberties);
            System.out.println("\tFound adjacent empty piece"   + (adjacent[0] != null && adjacent[0].getColor() == 0) 
                                                                + (adjacent[1] != null && adjacent[1].getColor() == 0) 
                                                                + (adjacent[2] != null && adjacent[2].getColor() == 0) 
                                                                + (adjacent[3] != null && adjacent[3].getColor() == 0));
        }
        
        //Loop through the 4 adjacent pieces
        for(GoPiece piece: adjacent)
        {
            //If they are from the same player
            if(piece != null && piece.color == this.color)
            {
                System.out.println("\tThis piece is the same color: " + piece.position + " " + piece.toString() + " " + piece.hasLiberties);
                //If the piece has not been checked then call on that piece
                if(!piece.checked)
                    adjacentLiberties |= piece.updateLiberties(); //boolean or with the return of the adjacent piece
                else //Else if has been checked, so just compare with the stored boolean value
                    adjacentLiberties |= piece.getHasLiberties();
            }
        }
        
        //Set liberties, check is now false, returns
        setHasLiberties(adjacentLiberties);
        checked = false;
        System.out.println("End of updateLiberties " + position + " " + this.toString() + " " + hasLiberties);
        return adjacentLiberties;
    }
    
    
    /* Returns string representation of Go Piece. "0" for empty, "B" for black
     * and "W" for white.
     * @return string representation of Go Piece
     * Author: Michael Nelson
    */
    public String toString()
    {
        if (color == 0)
        {
            return "0";
        }
        else if (color == 1)
        {
            return "B";
        }
        else
        {
            return "W";
        }
    }
    
    
    /* Overrides equals method inherited from Object
     * @param stone - The GoPiece implicit parameter is compared to
     * @return  whether or not stone is equal to implicit parameter
    */
    public boolean equals(GoPiece stone)
    {
        return (position.equals(stone.position) && (color == stone.color));
    }

}
