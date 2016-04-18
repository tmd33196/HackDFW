/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lord Horatio
 */
public class GoPoint extends java.awt.Point {
    public GoPoint(int i, int j) {
        super(i,j);
    }
    
    @Override
    public String toString() {
            
        return "(" + (int)this.getX() + " " + (int)this.getY() + ")";
    }

}
