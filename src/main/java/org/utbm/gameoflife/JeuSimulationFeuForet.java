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
            int nbVoisinesActives = grid.getCell(x, y).nbVoisinsEtat(1);
            if(grid.getCell(x, y).getEtat()==1)
            {
                if(nbVoisinesActives > 3 || nbVoisinesActives < 2) 
                    {grid.getCell(x, y).setEtat(0); circles[x][y].setFill(Couleur.getValeurByInt(grid.getCell(x, y).getEtat()));}
            }
            else
            {
                if(nbVoisinesActives == 3) 
                    {grid.getCell(x, y).setEtat(1); circles[x][y].setFill(Couleur.getValeurByInt(grid.getCell(x, y).getEtat()));}
            }
    }
}
