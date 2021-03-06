/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package org.utbm.gameoflife;

import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.min;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
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
    public int nbColonnesCellules = 40,nbLignesCellules = 40;
    /**densite de cellules actives au départ*/
    double densite = 0.5;
    /**nb de cycles d'execution**/
    int nbCycle = Timeline.INDEFINITE;
    /**liste des arguments **/
    private static String[] args ;
    String typeJeu = "JeuDeLaVie";
    /**délai en ms entre chaque évolution*/
    int tempo = 60;
    /**la fenetre principale**/
    static Stage primaryStage;
    /** le contenue de la fentre**/
    @FXMLViewFlowContext
    private ViewFlowContext flowContext;
    /** instance du singleton**/
    private static StartMain instance=null;


    public StartMain() {
    
    }
    
    /**
     * retourne l'instance
     * @return instance
     */
    public static StartMain getInstance() {
        if (null == instance) { // Premier appel
            instance = new StartMain();
        }
        return instance;
    }
    
    /**
     * retourne la fenetre principale
     * @return primaryStage
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    
    /**
     * affiche le menu
     * @param stage
     */
    public void AffichageMenu(Stage stage)    {
    primaryStage = stage;
    // crée le thread principale
        new Thread(() -> {
            try {
                SVGGlyphLoader.loadGlyphsFont(StartMain.class.getResourceAsStream("/fonts/icomoon.svg"),
                    "icomoon.svg");
            } catch (IOException ioExc) {
                ioExc.printStackTrace();
            }
        }).start();
        //initialise le menu
        Flow flow = new Flow(MenuController.class);
        DefaultFlowContainer container = new DefaultFlowContainer();
        flowContext = new ViewFlowContext();
        flowContext.register("Stage", primaryStage);
        try {
            flow.createHandler(flowContext).start(container);
        }
        catch (FlowException ex) {
            Logger.getLogger(StartMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        // prépare les décorateurs
        JFXDecorator decorator = new JFXDecorator(primaryStage, container.getView());
        decorator.setCustomMaximize(true);
        decorator.setGraphic(new SVGGlyph(""));
        //met le titre à la fenetre
        primaryStage.setTitle("Game of life");
        //valeur par défaut
        double width = 800;
        double height = 600;
        //met en pleine écran le menu
        try {
            Rectangle2D bounds = Screen.getScreens().get(0).getBounds();
            width = bounds.getWidth() / 2.5;
            height = bounds.getHeight() / 1.35;
        }catch (Exception e){ }
        //met le décorateur et le css en place
        Scene scene = new Scene(decorator, width, height);
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(//StartMain.class.getResource("/styles/jfoenix-fonts.css").toExternalForm(),
                           //StartMain.class.getResource("/styles/jfoenix-design.css").toExternalForm(),
                           StartMain.class.getResource("/styles/menu.css").toExternalForm());
        //affiche la scene et la fentre
        primaryStage.setScene(scene);
        primaryStage.show();
}
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // créer l'instance
        instance = new StartMain();
        Screen ecran = Screen.getPrimary();
     // limite l'ecran
        javafx.geometry.Rectangle2D limitesEcran = ecran.getVisualBounds();
        
        primaryStage.setX(limitesEcran.getMinX());
        primaryStage.setY(limitesEcran.getMinY());
        primaryStage.setWidth(limitesEcran.getWidth());
        primaryStage.setHeight(limitesEcran.getHeight());

        
      
        if (args == null || args.length < 9){
            //sans parametre affiche le menu
            AffichageMenu(primaryStage);
        }else{
            // set les parametres
            if(!"0.0".equals(args[0]))        
            densite=Double.parseDouble(args[0]);
            if(!"-1".equals(args[1]))
            nbCycle=Integer.parseInt(args[1]);
            if(!"0".equals(args[2]))
            tempo=Integer.parseInt(args[2]);
            if(!"0".equals(args[3]))
            nbColonnesCellules=Integer.parseInt(args[3]);
            if(!"0".equals(args[4]))
            nbLignesCellules=Integer.parseInt(args[4]);
            if(!"2".equals(args[5]))
            JeuDeLaVie.setNbminsolitude(Integer.parseInt(args[5]));
            if(!"3".equals(args[6]))
            JeuDeLaVie.setNbmaxsurpopulation(Integer.parseInt(args[6]));
            if(!"3".equals(args[7]))
            JeuDeLaVie.setNbminreproduction(Integer.parseInt(args[7]));
            if(!"3".equals(args[8]))
            JeuDeLaVie.setNbmaxreproduction(Integer.parseInt(args[8]));
            if(!"JeuDeLaVie".equals(args[9]))
                typeJeu=args[9];
            //modifie la taille des cellules
            this.sizeCell = calculerTailleCellulesSelonTailleEcran (limitesEcran,200,nbColonnesCellules,nbLignesCellules);
            //lance le jeu
            construireSceneJeu(primaryStage);
        }
    }
    
    int calculerTailleCellulesSelonTailleEcran (javafx.geometry.Rectangle2D limitesEcran,int LargeurZoneBoutons,int nbColonnesCellules,int nbLignesCellules)
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
        System.out.println("youpi");
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
        
        primaryStage.setScene(scene);
        //creation et initialisation des cellules
        grid = new Grid2D(nbColonnesCellules,nbLignesCellules);
        gridOld = new Grid2D(nbColonnesCellules,nbLignesCellules);
        
        Position posfourmi= new Position(nbColonnesCellules/2+2, nbLignesCellules/2-5);
        Position posfourmiold = new Position(nbColonnesCellules/2, nbLignesCellules/2);
        
        if("JeuDeLaVie".equals(typeJeu))
            JeuDeLaVie.initMatrice2D(nbColonnesCellules, nbLignesCellules, grid, densite);
        else if("feuforet".equals(typeJeu))
            JeuSimulationFeuForet.initMatrice2D(nbColonnesCellules, nbLignesCellules, grid, densite);
        else if("fourmi".equals(typeJeu)){
            
            copie(grid,gridOld);
            FourmiLangton.initMatrice2D(nbColonnesCellules, nbLignesCellules, grid);
        }
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
                    if("JeuDeLaVie".equals(typeJeu))
                        JeuDeLaVie.evoluerMatrice(nbColonnesCellules, nbLignesCellules, grid, circles);
                    else if("feuforet".equals(typeJeu)){
                        copie(grid,gridOld);
                        JeuSimulationFeuForet.evoluerMatrice(nbColonnesCellules, nbLignesCellules, grid, gridOld, circles);
                    }
                    else if("fourmi".equals(typeJeu) && posfourmi.getPosX()>=0 && posfourmi.getPosX()<nbColonnesCellules && posfourmi.getPosY()>=0 && posfourmi.getPosY()<nbLignesCellules){
                        
                        FourmiLangton.evoluerMatrice(grid,posfourmi, posfourmiold, circles);
                    }
                    else
                        try{
                            this.stop();
                        }catch(Exception e){
                            
                        }
                    
                    
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
    // copie la grille
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
        StartMain.args = args;
        launch(args);
        
    }
}
