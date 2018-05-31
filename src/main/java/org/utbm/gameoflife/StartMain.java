/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package org.utbm.gameoflife;

import java.awt.geom.Rectangle2D;
import static java.lang.Math.min;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Nero
 */
public class StartMain extends Application{
    /**matrice representant l'ETAT des cellules*/
    Grid2D grid, gridOld;
    /**objets graphiques représentant les cellules*/
    public  Circle[][] circles;
    /**taille d'une cellule en pixel*/
    int sizeCell = 10;
    /**longueur de la matrice (en nombre de cellules sur une rangée)*/
    private int nbColonnesCellules,nbLignesCellules;
    /**densite de cellules actives au départ*/
    double densite;
    /**nb de cycles d'execution**/
    int nbCycle = Timeline.INDEFINITE;
    String typeJeu;
    /**délai en ms entre chaque évolution*/
    private int tempo;
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        // Passer en fullscreen
        Screen ecran = Screen.getPrimary();
        javafx.geometry.Rectangle2D limitesEcran = ecran.getVisualBounds();

        primaryStage.setX(limitesEcran.getMinX());
        primaryStage.setY(limitesEcran.getMinY());
        primaryStage.setWidth(limitesEcran.getWidth());
        primaryStage.setHeight(limitesEcran.getHeight());
        
        // Initialisation de variables fondamentales
        nbColonnesCellules=nbLignesCellules=40;
        tempo = 60;
        sizeCell = calculerTailleCellulesSelonTailleEcran (limitesEcran,200,nbColonnesCellules,nbLignesCellules);
        densite = 0.5;
        //snbCycle = 50;
        //typeJeu = "JeuDeLaVie";
        typeJeu = "feuforet";
        construireSceneJeu(primaryStage);
        
    }
    
    int calculerTailleCellulesSelonTailleEcran (javafx.geometry.Rectangle2D limitesEcran, 
                                                int LargeurZoneBoutons,
                                                int nbColonnesCellules,
                                                int nbLignesCellules)
    {
        // Calculons les dimensions maximales d'une cellule pour que la grille rentre dans l'ecran
        double largeurMax = (double)(limitesEcran.getWidth() - LargeurZoneBoutons)/ (double)nbColonnesCellules ;
        double hauteurMax = (double)limitesEcran.getHeight() / (double)nbLignesCellules ;
        
        // Une cellule doit etre ronde : largeur = hauteur, arrondi à l'inférieur
        int tailleMax = (int)min (largeurMax,hauteurMax);
        
        // Limiter la taille à une gamme choisie
        if (tailleMax < 2)
            tailleMax = 2 ;
        else if (tailleMax > 16)
            tailleMax = 16 ;
        
        return tailleMax ;
    }
    void construireSceneJeu(Stage primaryStage)
    {
        int largeur = (nbColonnesCellules+1) * (sizeCell+1);
        int hauteur = (nbLignesCellules+1) * (sizeCell+1);
        //definir la troupe
        Group root = new Group();
        
        // Definir le groupe contenant les cellules de l'automate
        Group conteneurAutomate = new Group();
        conteneurAutomate.setLayoutX(100);
        //conteneurAutomate.setLayoutY(5);
        root.getChildren().add(conteneurAutomate);
        //definir la scene principale
        Scene scene = new Scene(root, largeur, hauteur, Color.BLACK);
        primaryStage.setTitle("Jeux");
        primaryStage.setScene(scene);
        //creation et initialisation des cellules
        grid = new Grid2D(nbColonnesCellules,nbLignesCellules);
        gridOld = new Grid2D(nbColonnesCellules,nbLignesCellules);
        
        if(typeJeu =="JeuDeLaVie")
            JeuDeLaVie.initMatrice2D(tailleX, tailleY, grid, densite);
        else if(typeJeu == "feuforet")
            JeuSimulationFeuForet.initMatrice2D(tailleX, tailleY, grid, densite);
        else
            JeuDeLaVie.initMatrice2D(nbColonnesCellules, nbLignesCellules, grid, densite);
        
        //definir les acteurs (representation des cellules)
        circles = new Circle[nbColonnesCellules][nbLignesCellules];
        creationVisuel2D(conteneurAutomate);
        
        //afficher le theatre
        primaryStage.show();
        
        
        //-----lancer le timer pour faire vivre la matrice
        Timeline littleCycle = new Timeline(new KeyFrame(Duration.millis(tempo),
                event-> {

                    
                    //à chaque top, lancer une evolution du jeu de la vie
                    if(typeJeu =="JeuDeLaVie")
                        JeuDeLaVie.evoluerMatrice(tailleX, tailleY, grid, circles);
                    else if(typeJeu == "feuforet"){
                        copie(grid,gridOld);
                        JeuSimulationFeuForet.evoluerMatrice(tailleX, tailleY, grid, gridOld, circles);
                    }
                    else
                        JeuDeLaVie.evoluerMatrice(nbColonnesCellules, nbLignesCellules, grid, circles);
                    
                } ));
        //animation en boucle
        littleCycle.setCycleCount(nbCycle);
        littleCycle.play();
    }

    
    /**
     *creation des cercles et de leurs couleurs en fonction de l'etat de leur cellule (cellule située aux même coordonnées (i,j))
     *@param root ensemble des acteurs de la scène dans lequel les cercles seront ajoutés
     */
    void creationVisuel2D(Group root)
    {
        for(int i=0; i<nbColonnesCellules; i++)
            for(int j=0; j<nbLignesCellules; j++)
            {
                circles[i][j] = new Circle((i+1)*(sizeCell+1), (j+1)*(sizeCell+1), sizeCell/2);
                if (grid.getCell(i,j).getEtat()>=0) circles[i][j].setFill(Couleur.getValeurByInt(grid.getCell(i,j).getEtat()));
                root.getChildren().add(circles[i][j]);
            }
    }
    
    void copie(Grid2D grid, Grid2D gridOld)
    {
        for(Cell2D[] cells :grid.getCells())
            for(Cell2D cell : cells){
                gridOld.getCell(cell.getPosition().getPosX(), cell.getPosition().getPosY()).setEtat(cell.getEtat());
            }
            gridOld.setVoisins();
    }
    
    /**
     * lancement du programme
     * @param args argument de lancement du programme
     */
    public static void main(String[] args) {
        launch(args);
    }
}
