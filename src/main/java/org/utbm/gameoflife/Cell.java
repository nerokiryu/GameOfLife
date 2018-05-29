/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.utbm.gameoflife;
import java.util.Map;
import javafx.scene.paint.Color;
/**
 *
 * @author Nero
 */
public class Cell {
    private int etat = 0;
    public Cell[] voisins;
    private Position pos;
    Cell(int etat){
        setEtat(etat);
        this.voisins = new Cell[8];
    }
    Cell(int etat, int x,int y){
        setEtat(etat);
        setPosition(x,y);
        this.voisins = new Cell[8];
    }
    public int getEtat(){
        return etat;
    }
    public void setEtat(int etat){
        this.etat = etat;
    }
    public Position getPosition(){
        return pos;
    }
    public void setPosition(int x,int y){
        this.pos = new Position(x,y);
    }
    public Cell[] getVoisins(){
        return voisins;
    }
    public int nbVoisinsEtat(int etat){
        int res=0;
        for(Cell item : this.getVoisins())
            if(item != null && item.etat == etat)
                res++;
        return res;
    }
    public void Draw()
    {
        switch (getEtat()){
            case 0:
              //spriteBatch.Draw(Game1.Pixel, Bounds, Color.BLACK);
              break;
            case 1:
              //spriteBatch.Draw(Game1.Pixel, Bounds, Color.BLUE);
              break;
            case 2:
              //spriteBatch.Draw(Game1.Pixel, Bounds, Color.RED);
              break;
            default:
              //spriteBatch.Draw(Game1.Pixel, Bounds, Color.WHITE);
    }
            
 
        // Don't draw anything if it's dead, since the default background color is white.
    }
}
