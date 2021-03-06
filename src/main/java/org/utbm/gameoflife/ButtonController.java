package org.utbm.gameoflife;

import com.jfoenix.controls.JFXButton;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

@FXMLController(value = "/fxml/Button.fxml", title = "")
public class ButtonController {
    @FXML
    private JFXButton start;
    
    @FXML
    private JFXButton choose_gametype;
    
    @FXML
    private JFXButton options;
    
    @FXML
    private JFXButton editor;
    
    @FXML
    private Label play;
    
    @FXML
    void HandleStart(ActionEvent event){
        
        //run in a new window the game with parameters
        Runtime runTime = Runtime.getRuntime();
        try {
            Process process = runTime.exec("java -jar ~/../target/GameOfLife-1.0-SNAPSHOT.jar "+StartMain.getInstance().densite+" "+StartMain.getInstance().nbCycle+" "+StartMain.getInstance().tempo+" "+StartMain.getInstance().nbColonnesCellules+" "+StartMain.getInstance().nbLignesCellules+" "+JeuDeLaVie.getNbminsolitude()+" "+JeuDeLaVie.getNbmaxsurpopulation()+" "+JeuDeLaVie.getNbminreproduction()+" "+JeuDeLaVie.getNbmaxreproduction()+" "+StartMain.getInstance().typeJeu);
        }
        catch (IOException ex) {
            Logger.getLogger(ButtonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void HandleGameType(ActionEvent event) throws Exception{
        //lunch menu game type screen
       MenuController.GoMenuGameType();
        
        
    }
    
    @FXML
    void HandleOptions(ActionEvent event) throws FlowException{
        //lunch options screen
       MenuController.GoOptions();
    }    
    @FXML
    void HandleEditor(ActionEvent event) throws FlowException{
        //lunch editor screen
       MenuController.GoEditor();
    }
    @FXML
    void HandleExit(ActionEvent event) throws FlowException{
        //exit the screen
        
       Platform.exit();
    }
}
