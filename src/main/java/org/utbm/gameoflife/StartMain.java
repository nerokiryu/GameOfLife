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
    /**nb de cycles d'execution**/
    int nbCycle = Timeline.INDEFINITE;
    String typeJeu;
    /**délai en ms entre chaque évolution*/
    private int tempo;
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        tailleX=tailleY=40;
        tempo = 60;
        sizeCell = 16;
        densite = 0.5;
        //snbCycle = 50;
        typeJeu = "JeuDeLaVie";
        construireSceneJeu(primaryStage);
        
    }
    void construireSceneJeu(Stage primaryStage)
    {
        int largeur = (tailleX+1) * (sizeCell+1);
        int hauteur = (tailleY+1) * (sizeCell+1);
        //definir la troupe
        Group root = new Group();
        //definir la scene principale
        Scene scene = new Scene(root, largeur, hauteur, Color.BLACK);
        primaryStage.setTitle("Jeux");
        primaryStage.setScene(scene);
        //creation et initialisation des cellules
        grid = new Grid2D(tailleX,tailleY);
        
        initMatrice2D(densite);
        
        //definir les acteurs (representation des cellules)
        circles = new Circle[tailleX][tailleY];
        creationVisuel2D( root);
        
        //afficher le theatre
        primaryStage.show();
        
        
        //-----lancer le timer pour faire vivre la matrice
        Timeline littleCycle = new Timeline(new KeyFrame(Duration.millis(tempo),
                event-> {
                    //à chaque top, lancer une evolution du jeu de la vie
                    if(typeJeu =="JeuDeLaVie")
                        JeuDeLaVie.evoluerMatrice(tailleX, tailleY, grid, circles);
                    else
                        JeuDeLaVie.evoluerMatrice(tailleX, tailleY, grid, circles);
                    
                } ));
        //animation en boucle
        littleCycle.setCycleCount(nbCycle);
        littleCycle.play();
    }
    /**
     *
     * @param densite
     *  initialisation de la matrice
     */
    void initMatrice2D(double densite)
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
    void creationVisuel2D(Group root)
    {
        for(int i=0; i<tailleX; i++)
            for(int j=0; j<tailleY; j++)
            {
                circles[i][j] = new Circle((i+1)*(sizeCell+1), (j+1)*(sizeCell+1), sizeCell/2);
                if (grid.getCell(i,j).getEtat()>=0) circles[i][j].setFill(Couleur.getValeurByInt(grid.getCell(i,j).getEtat()));
                root.getChildren().add(circles[i][j]);
            }
    }
    
    /**
     * lancement du programme
     * @param args argument de lancement du programme
     */
    public static void main(String[] args) {
        launch(args);
    }
}
