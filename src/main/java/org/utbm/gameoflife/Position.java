/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package org.utbm.gameoflife;

/**
 *
 * @author Nero
 */
public class Position {
    private int posX,posY;
    
    Position(int x, int y) {
        setPosX(x);
        setPosY(y);
    }
    public void Position(int x, int y){
        setPosX(x);
        setPosY(y);
    }
    public int getPosX(){
        return posX;
    }
    public void setPosX(int x){
        this.posX = x;
    }
    public int getPosY(){
        return posX;
    }
    public void setPosY(int y){
        this.posX = y;
    }
}
