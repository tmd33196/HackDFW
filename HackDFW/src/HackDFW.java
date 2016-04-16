/*
* This is the main file of the program
*/

import java.awt.Point;
import java.util.Scanner;
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
        
        //Infinit play loop
        while(!gameOver)
        {
            Point p; //The point where a player want to put a piece
            
            //!turn indicates that it is player 1's turn
            for(int a = 0; a < 2; a ++)
            {
                //Play the turn, returning the point the player wants to put a piece
                p = players[a].playTurn();
                
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
                    int val = board.placeStone(players[a].getPlayerNumber(), p);
                    
                    if(val == 0)
                    {
                        System.out.printf("Player 1 puts a piece at: %s\n", p.toString());
                        passed = false;
                    }
                    else if(val == 1)
                    {
                        System.out.println("That space is occupied, enter a new point");
                        a --;
                        continue;
                    }
                    else if(val == 2)
                    {
                        System.out.println("Placing there violates the suicide rule");
                        a --;
                        continue;
                    }
                }
            }
        }
    }
    
}
