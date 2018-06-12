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
public class Grid2D {
    //size of the grid in length
    private int sizeX;
    //size of the grid in height
    private int sizeY;
    // the grid of cells (matrice of cells)
    private Cell2D[][] cells;
    public Grid2D(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.cells = new Cell2D[sizeX][sizeY];
        initGrid2D();
        setVoisins();
    }
    public Cell2D getCell(int x, int y) {
        return cells[x][y];
    }
    
    public Cell2D[][] getCells() {
        return cells;
    }
    
    public int getSizeX() {
        return sizeX;
    }
    
    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }
    
    public int getSizeY() {
        return sizeY;
    }
    
    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }
    private void initGrid2D(){
        //initialize the grid with the state 0 to each cells
        for (int i = 0; i < sizeX; i++)
            for (int j = 0; j < sizeY; j++)
                cells[i][j] = new Cell2D(0,i,j);
    }
    public void setVoisins(){
        for (int i = 0; i < sizeX; i++){
            for (int j = 0; j < sizeY; j++){
                if(i+1 < sizeX && j< sizeY && i+1 >= 0 && j >= 0 && cells[i+1][j] !=null)
                    cells[i][j].getVoisins()[0]=cells[i+1][j];//n
                if(i+1 < sizeX && j+1< sizeY && i+1 >= 0 && j+1 >= 0 && cells[i+1][j+1] !=null)
                    cells[i][j].getVoisins()[1]=cells[i+1][j+1];//ne
                if(i < sizeX && j+1< sizeY && i >= 0 && j+1 >= 0 && cells[i][j+1] !=null)
                    cells[i][j].getVoisins()[2]=cells[i][j+1];//e
                if(i-1 < sizeX && j+1< sizeY && i-1 >= 0 && j+1 >= 0 && cells[i-1][j+1] !=null)
                    cells[i][j].getVoisins()[3]=cells[i-1][j+1];//se
                if(i-1 < sizeX && j< sizeY && i-1 >= 0 && j >= 0 && cells[i-1][j] !=null)
                    cells[i][j].getVoisins()[4]=cells[i-1][j];
                if(i-1 < sizeX && j-1< sizeY && i-1 >= 0 && j-1 >= 0 && cells[i-1][j-1] !=null)
                    cells[i][j].getVoisins()[5]=cells[i-1][j-1];
                if(i < sizeX && j-1< sizeY && i >= 0 && j-1 >= 0 && cells[i][j-1] !=null)
                    cells[i][j].getVoisins()[6]=cells[i][j-1];
                if(i+1 < sizeX && j-1< sizeY && i+1 >= 0 && j-1 >= 0 && cells[i+1][j-1] !=null)
                    cells[i][j].getVoisins()[7]=cells[i+1][j-1];
                
            }
        }
    }
}
