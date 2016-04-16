/*
 * Player class will run through the players turn
 * Author Tyler
 */

//Imports
import java.awt.Point;
import java.util.Scanner;
import java.io.IOException;

public class Player
{

    private int playerNumber;   //The number of the player, also carresponds to their peice color
    private int boardSize;      //The size of the board
    
    //Constructor with the player number and board size
    public Player(int _playerNumber, int _boardSize)
    {
        playerNumber = _playerNumber;
        boardSize = _boardSize;
    }
    
    //Get the player number
    public int getPlayerNumber() { return playerNumber; }
    
    //Play their turn
    public Point playTurn() throws IOException
    {
        //Output who's turn it is
        System.out.printf("Player %d's turn: \n", playerNumber);
        Point p = new Point(0, 0); //The point where the player wants to put a piece
        
        Scanner sc = new Scanner(System.in); //Scanner for user input
        
        //While still getting user input
        while(true)
        {
            //Gets the row
            System.out.print("Please enter the row you want the piece to be placed at: ");
            
            try{ p.x = sc.nextInt(); } //Try to get the int
            catch(Exception e) { System.out.println("Please enter a number"); sc.nextLine(); continue; } //If it wasn't an int go to the start of the loop
            
            //Bounds checking
            if(p.x < 0 || p.x >= boardSize)
            {
                System.out.println("The position is out of bounds");
                continue;
            }
            
            //Gets the column
            System.out.print("Please enter the column you want the piece to be placed at: ");
            
            try{ p.y = sc.nextInt(); } //Try to get the int
            catch(Exception e) { System.out.println("Please enter a number"); sc.nextLine(); continue; } //If it wasn't an int go to the start of the loop
            
            //Bounds checking
            if(p.y < 0 || p.y >= boardSize)
            {
                System.out.println("The position is out of bounds");
                continue;
            }
            
            break;
        }
        
        //Return the point the player want to put the piece
        return p;
    }
    
}
