/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utbm.gameoflife.exemple;

/**
 *
 * @author Nero
 */
import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.util.Duration;
 
 
public class FenetreApplication extends Application {
        /**matrice representant l'ETAT des cellules*/
        byte[][] matrice;
        /**copie de la matrice representant l'ETAT des cellules*/
        byte[][] matriceCopie;
        /**objets graphiques représentant les cellules*/
        public  Circle[][] circles;
 
        /**taille d'une cellule en pixel*/
        int espace = 10;
        /**longueur de la matrice (en nombre de cellules sur une rangée)*/
        private int taillex,tailley;
        /**densite de cellules actives au départ*/
        double densite;
 
        /**délai en ms entre chaque évolution*/
        private int tempo;
 
 
        /**lancement de l'application*/
        public void start(Stage primaryStage) {
           taillex = 50;
           tailley = 50;
           tempo = 60;
           espace = 16;
           densite = 0.5;
           construireScenePourJeuDeLaVie( primaryStage);
        }
 
        void construireScenePourJeuDeLaVie(Stage primaryStage)  
        {
           int largeur = (taillex+1) * (espace+1);
           int hauteur = (tailley+1) * (espace+1);
           //definir la troupe
           Group root = new Group();
           //definir la scene principale
           Scene scene = new Scene(root, largeur, hauteur, Color.BLACK);
           primaryStage.setTitle("Life...");
           primaryStage.setScene(scene);
 
           //creation et initialisation des cellules
           matrice = new byte[taillex][tailley];
           matriceCopie = new byte[taillex][tailley];
           initMatrice(densite);
           copie(matrice, matriceCopie);
 
           //definir les acteurs (representation des cellules)
           circles = new Circle[taillex][tailley];
           creationTroupe( root);           
 
           //afficher le theatre
           primaryStage.show();
 
 
           //-----lancer le timer pour faire vivre la matrice
           Timeline littleCycle = new Timeline(new KeyFrame(Duration.millis(tempo), 
                      event-> {
                               //à chaque top, lancer une evolution
                                    evoluerMatrice();
                               //puis effectuer une copie de la matrice
                                    copie(matrice, matriceCopie);
                          } ));
           //animation en boucle
           littleCycle.setCycleCount(Timeline.INDEFINITE);
           littleCycle.play();
        }
 
 
        /**copie le contenu de matrice dans matriceCopie
         * @param matrice matrice d'origine
         * @param matriceCopie matrice de destination
         * */
        void copie(byte[][] matrice,byte[][]  matriceCopie)
        {
             for (int i=0; i<taillex; i++)
                  System.arraycopy(matrice[i], 0, matriceCopie[i], 0, tailley);
        }
 
        /**
         * initialise les cellules à active (1) ou non (0)
         * en fonction de la densite d'activation (entre 0 et 1)
         * @param densite densite de cellule à activer, entre entre 0(aucune cellule active) et 1(toutes cellules actives)
         * */
        void initMatrice(double densite)
        {
             for(int i=0; i<taillex; i++)
                  for(int j=0; j<tailley; j++)
                       if(Math.random()<densite) 
                            matrice[i][j] = 1;
        }
 
        /** 
         *creation des cercles et de leurs couleurs en fonction de l'etat de leur cellule (cellule située aux même coordonnées (i,j))
         *@param root ensemble des acteurs de la scène dans lequel les cercles seront ajoutés
         */
        void creationTroupe(Group root)
        {
           for(int i=0; i<taillex; i++)
              for(int j=0; j<tailley; j++)
              {
                 circles[i][j] = new Circle((i+1)*(espace+1), (j+1)*(espace+1), espace/2);
                 if (matrice[i][j]==1) circles[i][j].setFill(Color.ANTIQUEWHITE);
                 else circles[i][j].setFill(Color.DARKSLATEGRAY);
                 root.getChildren().add(circles[i][j]);
              }
        }
 
        /** 
         *evolution d'une cellule par rapport aux voisines/
         *Met à jour les valeurs dans matrice à partir des valeurs dans matriceCopie.
         *@param x no de colonne de la cellule qui doit evoluer
         *@param y no de ligne de la cellule qui doit evoluer
         */
        void evoluer(int x, int y)
        {
             int nbVoisinesActives = 0;
             for(int i=-1; i<=1; i++)
                  for(int j=-1; j<=1; j++)
                  {
                       if(i==0 && j==0) continue;
                       int xx = ((x+j)+taillex)%tailley;
                       int yy = ((y+i)+taillex)%tailley;
                       nbVoisinesActives = nbVoisinesActives + matriceCopie[yy][xx];                       
                  }
             if(matriceCopie[y][x]==1)
             {
                  if(nbVoisinesActives > 3 || nbVoisinesActives < 2) 
                       {matrice[y][x]=0; circles[y][x].setFill(Color.DARKSLATEGRAY);}
             }
             else
             {
                  if(nbVoisinesActives == 3) 
                       {matrice[y][x]=1; circles[y][x].setFill(Color.ANTIQUEWHITE);}
             }
        }
 
        /** 
         *evolution de l'ensemble de la matrice
         */
        void evoluerMatrice()
        {
            for(int x=0; x<taillex; x++)
                 for(int y=0; y<tailley; y++)
                      evoluer(x,y);   
        }
 
        /**lancement du prog*/
        public static void main(String[] args) {
           launch(args);
        }
     }