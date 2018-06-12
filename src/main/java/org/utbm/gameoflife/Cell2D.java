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
public class Cell2D {
    //state of the cell
    private int etat = 0;
    //neighbourg of this cell
    public Cell2D[] voisins;
    //position on the grid of this cell
    private Position pos;
    Cell2D(int etat){
        setEtat(etat);
        this.voisins = new Cell2D[8];
    }
    Cell2D(int etat, int x,int y){
        setEtat(etat);
        setPosition(x,y);
        this.voisins = new Cell2D[8];
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
    public Cell2D[] getVoisins(){
        return voisins;
    }
    public int nbVoisinsEtat(int etat){
        //get the number of neighbourg with the "etat" state
        int res=0;
        for(Cell2D item : this.getVoisins())
            if(item != null && item.etat == etat)
                res++;
        return res;
    }
    
}
