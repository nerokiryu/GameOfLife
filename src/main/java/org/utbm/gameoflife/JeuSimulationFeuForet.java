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
                if(grid.getCell(x, y).getEtat()==Couleur.rouge.ordinal()){
                    evoluerCellule(x,y,grid, circles);
                }
    }
        
        /**
         * 
         * @param x coordonnée en x de la céllule
         * @param y coordonnée en y de la céllule
         * logique de céllule unique
         */
    public static void evoluerCellule(int x, int y, Grid2D grid, Circle[][] circles)
    {
        for(Cell2D cell: grid.getCell(x, y).getVoisins()){
            if(cell != null && cell.getEtat()== Couleur.vert.ordinal()){
                cell.setEtat(Couleur.rouge.ordinal());
            }
        }
        grid.getCell(x, y).setEtat(Couleur.gris.ordinal());
    }
}
