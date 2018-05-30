/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utbm.gameoflife;

import javafx.scene.shape.Circle;

/**
 *
 * @author nbarras
 */
public class JeuSimulationFeuForet {
    /** 
     *evolution de l'ensemble de la matrice
     */
    public static void evoluerMatrice(int tailleX, int tailleY, Grid2D grid, Circle[][] circles){
        for(int x=0; x<tailleX; x++)
            for(int y=0; y<tailleY; y++)
                    evoluerCellule(x,y,grid, circles);
    }
        
        /**
         * 
         * @param x coordonnée en x de la céllule
         * @param y coordonnée en y de la céllule
         * logique de céllule unique
         */
    public static void evoluerCellule(int x, int y, Grid2D grid, Circle[][] circles)
    {
        if(grid.getCell(x, y).getEtat()==Couleur.rouge.ordinal()){
            grid.getCell(x, y).setEtat(Couleur.gris.ordinal());
        }
        else if(grid.getCell(x, y).getEtat()==Couleur.vert.ordinal()){
            for(Cell2D cell: grid.getCell(x, y).getVoisins()){
                if(cell != null && cell.getEtat()==Couleur.rouge.ordinal()){
                    grid.getCell(x, y).setEtat(Couleur.rouge.ordinal());
                    break;
                }
            }
        }
        else{}
        
        circles[x][y].setFill(Couleur.getValeurByInt(grid.getCell(x, y).getEtat()));
    }
    
    public static void initMatrice2D(int tailleX, int tailleY, Grid2D grid, double densite)
    {
        for(int i=0; i<tailleX; i++)
            for(int j=0; j<tailleY; j++)
                if(Math.random()<densite)
                    grid.getCell(i, j).setEtat(Couleur.vert.ordinal());
        grid.getCell(tailleX/2, tailleY/2).setEtat(Couleur.rouge.ordinal());
    }
}
