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



   @FXML
    void HandleSubmit(ActionEvent event) throws Exception{
        double densv=dens.getValue();
        double lengthv=length.getValue();
        double heightv=height.getValue();
        double speedv=speed.getValue();
        int nbcyclev;
        if(Integer.parseInt(nbcycle.getText())>0)
        nbcyclev=Integer.parseInt(nbcycle.getText());
        else
        nbcyclev=Timeline.INDEFINITE;
        StartMain.getInstance().densite=((101-(int)densv)/100);
        StartMain.getInstance().nbColonnesCellules=((int)lengthv);
        StartMain.getInstance().nbLignesCellules=((int)heightv);
        StartMain.getInstance().nbCycle=((int)nbcyclev);
        StartMain.getInstance().tempo=((int)speedv);
        MenuController.BackMenu();


    }
    
}
