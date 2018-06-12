/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utbm.gameoflife;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import io.datafx.controller.FXMLController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.regex.*;
import javax.annotation.PostConstruct;

/**
 * FXML Controller class
 *
 * @author Nero
 */
@FXMLController(value = "/fxml/Gametype.fxml", title = "")
public class GametypeController {

    @FXML
    private JFXComboBox jfxComboBox;
    
    @FXML
    private JFXButton submit;
    
      @PostConstruct
    public void init() throws Exception {
 
        //init each element of this window
        if(!"".equals(StartMain.getInstance().typeJeu) && "JeuDeLaVie".equals(StartMain.getInstance().typeJeu))
            jfxComboBox.setValue(jfxComboBox.getItems().get(0));
        else if(!"".equals(StartMain.getInstance().typeJeu) && "feuforet".equals(StartMain.getInstance().typeJeu))
            jfxComboBox.setValue(jfxComboBox.getItems().get(1));
        else if(!"".equals(StartMain.getInstance().typeJeu) && "fourmi".equals(StartMain.getInstance().typeJeu))
            jfxComboBox.setValue(jfxComboBox.getItems().get(2));

        
    }
    
    @FXML
    void HandleSubmit(ActionEvent event) throws Exception{
        //set each value in the main program
        Pattern vie,feux,langton;
        Matcher mvie,mfeux,mlangton;
        
        vie = Pattern.compile("vie");
        mvie = vie.matcher(jfxComboBox.getValue().toString());
        
        feux = Pattern.compile("Feux");
        mfeux = feux.matcher(jfxComboBox.getValue().toString());
        
        langton = Pattern.compile("langton");
        mlangton = langton.matcher(jfxComboBox.getValue().toString());

        if(mvie.find()){
            StartMain.getInstance().typeJeu = "JeuDeLaVie";
        }
        else if(mfeux.find()){
            StartMain.getInstance().typeJeu = "feuforet";
        }
        else if(mlangton.find()){
            StartMain.getInstance().typeJeu = "fourmi";
        }
        
        
        MenuController.BackMenu();
    }
    
}
