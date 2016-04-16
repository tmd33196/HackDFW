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
        Player p1 = new Player(1, size); //Creates player 1 with black pieces
        Player p2 = new Player(2, size); //Creates player 2 with white pieces
        boolean turn = false;//Who's turn it is, false = player 1, true = player 2
        boolean passed = false; //If the last player passed
        boolean gameOver = false; //If the game has ended
        
        //Infinit play loop
        while(!gameOver)
        {
            Point p;
            if(!turn)
            {
                p = p1.playTurn();
                
                if(p == null)
                {
                    if(passed == true)
                    {
                        gameOver = true;
                        break;
                    }
                    else
                    {
                        passed = true;
                    }
                }
                else
                {
                    board.placeStone(p1.getPlayerNumber(), p);
                    System.out.printf("Player 1 puts a piece at: %s\n", p.toString());
                    passed = false;
                }
            }
            else
            {
                p = p2.playTurn();
                
                if(p == null)
                {
                    if(passed == true)
                    {
                        gameOver = true;
                        break;
                    }
                    else
                    {
                        passed = true;
                    }
                }
                else
                {
                    board.placeStone(p1.getPlayerNumber(), p);
                    System.out.printf("Player 2 puts a piece at: %s\n", p.toString());
                    passed = false;
                }
            }
            
            turn = turn ^ true;
        }
    }
    
}
