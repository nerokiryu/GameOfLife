/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package org.utbm.gameoflife;

import javafx.scene.shape.Circle;

/**
 *
 * @author Nero
 */
public class JeuDeLaVie {
    private static int nbminsolitude=2,nbmaxsurpopulation=3,nbminreproduction=3,nbmaxreproduction=3;

    public static int getNbminsolitude() {
        return nbminsolitude;
    }

    public static void setNbminsolitude(int nbminsolitude) {
        JeuDeLaVie.nbminsolitude = nbminsolitude;
    }

    public static int getNbmaxsurpopulation() {
        return nbmaxsurpopulation;
    }

    public static void setNbmaxsurpopulation(int nbmaxsurpopulation) {
        JeuDeLaVie.nbmaxsurpopulation = nbmaxsurpopulation;
    }

    public static int getNbminreproduction() {
        return nbminreproduction;
    }

    public static void setNbminreproduction(int nbminreproduction) {
        JeuDeLaVie.nbminreproduction = nbminreproduction;
    }

    public static int getNbmaxreproduction() {
        return nbmaxreproduction;
    }

    public static void setNbmaxreproduction(int nbmaxreproduction) {
        JeuDeLaVie.nbmaxreproduction = nbmaxreproduction;
    }
    
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
            if(nbVoisinesActives < nbminsolitude || nbVoisinesActives > nbmaxsurpopulation)
            {grid.getCell(x, y).setEtat(0); circles[x][y].setFill(Couleur.getValeurByInt(grid.getCell(x, y).getEtat()));}
        }
        else
        {
            if(nbVoisinesActives >= nbminreproduction  && nbVoisinesActives <= nbmaxreproduction )
            {grid.getCell(x, y).setEtat(1); circles[x][y].setFill(Couleur.getValeurByInt(grid.getCell(x, y).getEtat()));}
        }
    }
    
    /**
     *
     * @param densite
     *  initialisation de la matrice
     */
    public static void initMatrice2D(int tailleX, int tailleY, Grid2D grid, double densite)
    {
        for(int i=0; i<tailleX; i++)
            for(int j=0; j<tailleY; j++)
                if(Math.random()<densite)
                    grid.getCell(i, j).setEtat(1);
    }
}
