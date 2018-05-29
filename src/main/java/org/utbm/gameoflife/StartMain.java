/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utbm.gameoflife;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Nero
 */
public class StartMain extends Application{
    /**matrice representant l'ETAT des cellules*/
    Grid2D grid;
    /**objets graphiques représentant les cellules*/
    public  Circle[][] circles;
    /**taille d'une cellule en pixel*/
    int sizeCell = 10;    
    /**longueur de la matrice (en nombre de cellules sur une rangée)*/
    private int tailleX,tailleY;
    /**densite de cellules actives au départ*/
    double densite;
 
    /**délai en ms entre chaque évolution*/
    private int tempo;    
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        tailleX=tailleY=40;
        tempo = 60;
        sizeCell = 16;
        densite = 0.5;
        construireScenePourJeuDeLaVie(primaryStage);
    }
    void construireScenePourJeuDeLaVie(Stage primaryStage)  
        {
           int largeur = (tailleX+1) * (sizeCell+1);
           int hauteur = (tailleY+1) * (sizeCell+1);
           //definir la troupe
           Group root = new Group();
           //definir la scene principale
           Scene scene = new Scene(root, largeur, hauteur, Color.BLACK);
           primaryStage.setTitle("Life...");
           primaryStage.setScene(scene);
           //creation et initialisation des cellules
           grid = new Grid2D(tailleX,tailleY);
           
           initMatrice(densite);
 
           //definir les acteurs (representation des cellules)
           circles = new Circle[tailleX][tailleY];
           creationVisuel( root);           
 
           //afficher le theatre
           primaryStage.show();
 
 
           //-----lancer le timer pour faire vivre la matrice
           Timeline littleCycle = new Timeline(new KeyFrame(Duration.millis(tempo), 
                      event-> {
                               //à chaque top, lancer une evolution
                                    evoluerMatrice();
                          } ));
           //animation en boucle
           littleCycle.setCycleCount(Timeline.INDEFINITE);
           littleCycle.play();
        }
    /**
     * 
     * @param densite
     *  initialisation de la matrice
     */
        void initMatrice(double densite)
        {
             for(int i=0; i<tailleX; i++)
                  for(int j=0; j<tailleY; j++)
                       if(Math.random()<densite) 
                            grid.getCell(i, j).setEtat(1);
        }
        
         /** 
         *creation des cercles et de leurs couleurs en fonction de l'etat de leur cellule (cellule située aux même coordonnées (i,j))
         *@param root ensemble des acteurs de la scène dans lequel les cercles seront ajoutés
         */
        void creationVisuel(Group root)
        {
           for(int i=0; i<tailleX; i++)
              for(int j=0; j<tailleY; j++)
              {
                 circles[i][j] = new Circle((i+1)*(sizeCell+1), (j+1)*(sizeCell+1), sizeCell/2);
                 if (grid.getCell(i,j).getEtat()!=0) circles[i][j].setFill(Color.ANTIQUEWHITE);//grid.getCell(i,j).getEtat()
                 else circles[i][j].setFill(Color.DARKSLATEGRAY);
                 root.getChildren().add(circles[i][j]);
              }
        }
        
         /** 
         *evolution de l'ensemble de la matrice
         */
        void evoluerMatrice()
        {
            for(int x=0; x<tailleX; x++)
                 for(int y=0; y<tailleY; y++)
                      evoluer(x,y);   
        }
        
        /**
         * 
         * @param x coordonnée en x de la céllule
         * @param y coordonnée en y de la céllule
         * logique de céllule unique
         */
        void evoluer(int x, int y)
        {
            int nbVoisinesActives = grid.getCell(x, y).nbVoisinsEtat(1);
             if(grid.getCell(x, y).getEtat()==1)
             {
                  if(nbVoisinesActives > 3 || nbVoisinesActives < 2) 
                       {grid.getCell(x, y).setEtat(0); circles[x][y].setFill(Color.DARKSLATEGRAY);}
             }
             else
             {
                  if(nbVoisinesActives == 3) 
                       {grid.getCell(x, y).setEtat(1); circles[x][y].setFill(Color.ANTIQUEWHITE);}
             }
        }
        
        /**
         * lancement du pro
         * @param args argument de lancement du programme
        */
        public static void main(String[] args) {
           launch(args);
        }
}
