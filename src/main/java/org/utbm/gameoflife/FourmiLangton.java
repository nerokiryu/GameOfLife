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
public class FourmiLangton {
    /** 
     *evolution de l'ensemble de la matrice
     */

    public static void evoluerMatrice(Grid2D grid, Position posfourmi, Position posfourmiold, Circle[][] circles){

        evoluerCellule(grid,posfourmi,posfourmiold, circles, directionfourmi(posfourmi, posfourmiold));

                    
    }
        
        /**
         * 
         * @param x coordonnée en x de la céllule
         * @param y coordonnée en y de la céllule
         * logique de céllule unique
         */
    public static void evoluerCellule(Grid2D grid, Position posfourmi,Position posfourmiold, Circle[][] circles, int dir)
    {
        if(grid.getCell(posfourmi.getPosX(), posfourmi.getPosY()).getEtat()==Couleur.noir.ordinal()){ //si la cellule est noir on va a gauche en fonction de la direction actuel
            grid.getCell(posfourmi.getPosX(), posfourmi.getPosY()).setEtat(Couleur.blanc.ordinal());
            circles[posfourmi.getPosX()][posfourmi.getPosY()].setFill(Couleur.getValeurByInt(grid.getCell(posfourmi.getPosX(), posfourmi.getPosY()).getEtat()));
            posfourmiold.setPosX(posfourmi.getPosX());
            posfourmiold.setPosY(posfourmi.getPosY());
            switch(dir){
                case 0: // a gauche a gauche
                    //grid.getCell(x, y+1).setEtat(Couleur.rouge.ordinal());
                    posfourmi.setPosY(posfourmi.getPosY()+1);
                    break;
                case 1: // en haut a gauche 
                    //grid.getCell(x-1, y).setEtat(Couleur.rouge.ordinal());
                    posfourmi.setPosX(posfourmi.getPosX()-1);
                    break;
                case 2: // a droite a gauche
                    //grid.getCell(x, y-1).setEtat(Couleur.rouge.ordinal());
                    posfourmi.setPosY(posfourmi.getPosY()-1);
                    break;
                case 3: // en bas a gauche
                    //grid.getCell(x+1, y).setEtat(Couleur.rouge.ordinal());
                    posfourmi.setPosX(posfourmi.getPosX()+1);
                    break;
            }
        }
        else if(grid.getCell(posfourmi.getPosX(), posfourmi.getPosY()).getEtat()==Couleur.blanc.ordinal()){ //blanc a droite en fonction de la direction actuel
            grid.getCell(posfourmi.getPosX(), posfourmi.getPosY()).setEtat(Couleur.noir.ordinal());
            circles[posfourmi.getPosX()][posfourmi.getPosY()].setFill(Couleur.getValeurByInt(grid.getCell(posfourmi.getPosX(), posfourmi.getPosY()).getEtat()));
            posfourmiold.setPosX(posfourmi.getPosX());
            posfourmiold.setPosY(posfourmi.getPosY());
            switch(dir){
                case 0: // a gauche a droite
                    //grid.getCell(x, y-1).setEtat(Couleur.rouge.ordinal());
                    posfourmi.setPosY(posfourmi.getPosY()-1);
                    break;
                case 1: // en haut a droite 
                    //grid.getCell(x+1, y).setEtat(Couleur.rouge.ordinal());
                    posfourmi.setPosX(posfourmi.getPosX()+1);
                    break;
                case 2: // a droite a droite
                    //grid.getCell(x, y+1).setEtat(Couleur.rouge.ordinal());
                    posfourmi.setPosY(posfourmi.getPosY()+1);
                    break;
                case 3: // en bas a droite
                    //grid.getCell(x-1, y).setEtat(Couleur.rouge.ordinal());
                    posfourmi.setPosX(posfourmi.getPosX()-1);
                    break;
            }
        }
        else{
//            grid.getCell(x, y).setEtat(Couleur.blanc.ordinal());
//            switch(dir){
//                case 0: // a gauche a gauche
//                    grid.getCell(x, y+1).setEtat(Couleur.rouge.ordinal());
//                    break;
//                case 1: // en haut a gauche 
//                    grid.getCell(x-1, y).setEtat(Couleur.rouge.ordinal());
//                    break;
//                case 2: // a droite a gauche
//                    grid.getCell(x, y-1).setEtat(Couleur.rouge.ordinal());
//                    break;
//                case 3: // en bas a gauche
//                    grid.getCell(x+1, y).setEtat(Couleur.rouge.ordinal());
//                    break;
//            }
        }
        
        
        
//        circles[x+1][y].setFill(Couleur.getValeurByInt(grid.getCell(x+1, y).getEtat()));
//        circles[x-1][y].setFill(Couleur.getValeurByInt(grid.getCell(x-1, y).getEtat()));
//        circles[x][y+1].setFill(Couleur.getValeurByInt(grid.getCell(x, y+1).getEtat()));
//        circles[x][y-1].setFill(Couleur.getValeurByInt(grid.getCell(x, y-1).getEtat()));
    }
    
    
    public static void initMatrice2D(int tailleX, int tailleY, Grid2D grid)
    {
        for(int i=0; i<tailleX; i++)
            for(int j=0; j<tailleY; j++)
                grid.getCell(i, j).setEtat(Couleur.noir.ordinal());
        //grid.getCell(tailleX/2, tailleY/2).setEtat(Couleur.rouge.ordinal());
    }
    
    public static int directionfourmi(Position posfourmi, Position posfourmiold){
        if(posfourmi.getPosX()<posfourmiold.getPosX() && posfourmi.getPosY() == posfourmiold.getPosY()){
            return 0;//a gauche
        }
        else if(posfourmi.getPosY()<posfourmiold.getPosY() && posfourmi.getPosX() == posfourmiold.getPosX()){
            return 1; //au dessus
        }
        else if(posfourmi.getPosX()>posfourmiold.getPosX() && posfourmi.getPosY() == posfourmiold.getPosY()){
            return 2;// a droite
        }
        else if(posfourmi.getPosY()>posfourmiold.getPosY() && posfourmi.getPosX() == posfourmiold.getPosX()){
            return 3; // en bas
        }
        else{
            return 0;
        }
    }
}
