/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utbm.gameoflife;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.FXMLController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.annotation.PostConstruct;

/**
 * FXML Controller class
 *
 * @author Nero
 */
@FXMLController(value = "/fxml/Options.fxml", title = "")
public class OptionsController{

    @FXML
    private JFXSlider dens;
    @FXML
    private JFXSlider length;
    @FXML
    private JFXSlider height;
    @FXML
    private JFXSlider speed;
    @FXML
    private JFXTextField nbcycle;   
    @FXML
    private JFXButton submit;
    
    
        /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() throws Exception {
        //init each element of this window
        int densite = (int) (StartMain.getInstance().densite*100);
        dens.setValue(densite);
        length.setValue( StartMain.getInstance().nbColonnesCellules);
        height.setValue(StartMain.getInstance().nbLignesCellules);
        speed.setValue(StartMain.getInstance().tempo);

    }



   @FXML
    void HandleSubmit(ActionEvent event) throws Exception{
        //set each value in the main program
        double densv=dens.getValue();
        double lengthv=length.getValue();
        double heightv=height.getValue();
        double speedv=speed.getValue();
        int nbcyclev;
        if(!"".equals(nbcycle.getText()) && Integer.parseInt(nbcycle.getText())>0)
        nbcyclev=Integer.parseInt(nbcycle.getText());
        else
        nbcyclev=Timeline.INDEFINITE;
        StartMain.getInstance().densite=(densv/100);
        StartMain.getInstance().nbColonnesCellules=((int)lengthv);
        StartMain.getInstance().nbLignesCellules=((int)heightv);
        StartMain.getInstance().nbCycle=((int)nbcyclev);
        StartMain.getInstance().tempo=((int)speedv);
        MenuController.BackMenu();


    }
    
}
