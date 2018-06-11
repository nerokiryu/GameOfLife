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
     * @param tailleX
     * @param tailleY
     * @param grid
     * @param gridOld
     * @param circles
     */
    //on parcours l'entiereté de la matrice et on éxécute la fonction évoluer cellule sur les cases qui sont rouges ou vertes
    public static void evoluerMatrice(int tailleX, int tailleY, Grid2D grid, Grid2D gridOld, Circle[][] circles){
        for(int x=0; x<tailleX; x++)
            for(int y=0; y<tailleY; y++)
                if(grid.getCell(x, y).getEtat()==Couleur.rouge.ordinal() || grid.getCell(x, y).getEtat()==Couleur.vert.ordinal())
                    evoluerCellule(x,y,grid, gridOld, circles);
    }
    
    /**
     *
     * logique de céllule unique
     * @param x coordonnée en x de la céllule
     * @param y coordonnée en y de la céllule
     * @param grid
     * @param gridOld
     * @param circles
     */
    //les cellules rouges (en feu) deviennent grise (cendres) et les vertes(arbres) à proximité d'une rouge s'enflamment et deviennent rouges
    public static void evoluerCellule(int x, int y, Grid2D grid, Grid2D gridOld, Circle[][] circles)
    {
        if(grid.getCell(x, y).getEtat()==Couleur.rouge.ordinal()){                                  // si en feu, devient en cendres
            grid.getCell(x, y).setEtat(Couleur.gris.ordinal());
        }
        else if(grid.getCell(x, y).getEtat()==Couleur.vert.ordinal()){                              // si verte
            for(Cell2D cell: gridOld.getCell(x, y).getVoisins()){                                   // on regarde si les voisins sont en feux
                if(cell != null && cell.getEtat()==Couleur.rouge.ordinal()){                        //si une des cellules voisine est en feu
                    grid.getCell(x, y).setEtat(Couleur.rouge.ordinal());                            //l'arbre s'enflamme
                    circles[x][y].setFill(Couleur.getValeurByInt(grid.getCell(x, y).getEtat()));
                    break;
                }
            }
        }
        else{}
        
        circles[x][y].setFill(Couleur.getValeurByInt(grid.getCell(x, y).getEtat()));
    }
    
    //initialisation de la matrice
    public static void initMatrice2D(int tailleX, int tailleY, Grid2D grid, double densite)
    {
        for(int i=0; i<tailleX; i++)
            for(int j=0; j<tailleY; j++)
                if(Math.random()<densite)
                    grid.getCell(i, j).setEtat(Couleur.vert.ordinal());
        grid.getCell(tailleX/2, tailleY/2).setEtat(Couleur.rouge.ordinal());
    }
}
