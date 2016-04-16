/*
* This is the main file of the program
*/

import java.util.Scanner;
import java.io.IOException;

public class HackDFW 
{

    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        int size;
        
        System.out.println("Input the size of the board you would like");
        
        while(true)
        {
            try{ size = sc.nextInt(); break; }
            catch(Exception e) { System.out.println("Please enter a number\n"); sc.nextLine(); }
        }
        
        GoBoard board = new GoBoard(size);
    }
    
}
