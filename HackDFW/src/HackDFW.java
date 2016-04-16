/*
* This is the main file of the program
*/

import java.awt.Point;
import java.util.*;
import java.io.IOException;

public class HackDFW 
{

    public static void main(String[] args) throws IOException
    {   
        Scanner sc = new Scanner(System.in); //Scanner for user input
        int size; //The size of the board
        
        //Prompt for user input
        System.out.print("Input the size of the board you would like: ");
        
        //While still getting the input
        while(true)
        {
            try{ size = sc.nextInt(); break; } //Try to get the next int
            catch(Exception e) { System.out.println("Please enter a number"); sc.nextLine(); } //If there was no int then try again
        }
        
        GoBoard board = new GoBoard(size); //Creates the game board
        Player[] players = { new Player(1, size), new Player(2, size) }; //The players in the game
        boolean passed = false; //If the last player passed
        boolean gameOver = false; //If the game has ended
        int player = 0; //Indicates the players turn
        
        //Infinit play loop
        while(!gameOver)
        {
            //Prints the board
            System.out.println(board.toString());
            
            //Resets the player if it's too high
            if(player == 2) player = 0;
            
            Point p; //The point where a player want to put a piece
            
            //Play the turn, returning the point the player wants to put a piece
            p = players[player].playTurn();

            //If it is null then the player passes
            if(p == null)
            {
                //If the last turn was a pass as well then the game is over
                if(passed == true)
                {
                    gameOver = true;
                    break;
                }
                //Else set passed to true to indicate the last turn was a pass
                else
                {
                    passed = true;
                }
            }
            else
            {
                //Place the stone
                int val = board.placeStone(players[player].getPlayerNumber() == 1? 1 : -1, p);

                //Val 0 means the stone was placed successfully
                if(val == 0)
                {
                    System.out.printf("Player %d puts a piece at: %s\n", player + 1, p.toString());
                    passed = false;
                    player ++;
                }
                //Val 1 means the space is occupied
                else if(val == 1)
                {
                    System.out.println("That space is occupied, enter a new point");
                    continue;
                }
                //Val 2 means the suicide rule was violated
                else if(val == 2)
                {
                    System.out.println("Placing there violates the suicide rule");
                    continue;
                }
                //Val 3 means Ko Rule was violated
                else if (val == 3)
                {
                    System.out.println("Placing there violates Ko Rule");
                    continue;
                }
            }
        }
    }
    
}
