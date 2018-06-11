/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utbm.gameoflife;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import io.datafx.controller.FXMLController;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.annotation.PostConstruct;





/**
 * FXML Controller class
 *
 * @author Nero
 */
@FXMLController(value = "/fxml/Editor.fxml", title = "")
public class EditorController {
    
    @FXML
    private JFXSlider min;
    @FXML
    private JFXSlider max;
    @FXML
    private JFXSlider birthMin;
    @FXML
    private JFXSlider birthMax;   
    @FXML
    private JFXButton submit;
    
   @PostConstruct
    public void init() throws Exception {
        min.setOnMouseReleased(event -> {
            System.out.println("mouse released");
            HandleMin();
        });       
        max.setOnMouseReleased(event -> {
            System.out.println("mouse released");
            HandleMax();
        });       
        
        birthMin.setOnMouseReleased(event -> {
            System.out.println("mouse released");
            HandleBirthMin();
        });       
        birthMax.setOnMouseReleased(event -> {
            System.out.println("mouse released");
            HandleBirthMax();
        });
        
    }

    void HandleMin() {
        double minv=min.getValue();
        if(max.getValue()<minv)
            max.setValue(minv);
        max.setMin(minv);


    }

    void HandleMax() {
        double maxv=max.getValue();
        if(min.getValue()>maxv)
            min.setValue(maxv);
        min.setMax(maxv);


    }
    
    void HandleBirthMin() {
        double minv=birthMin.getValue();
        if(birthMax.getValue()<minv)
            birthMax.setValue(minv);
        birthMax.setMin(minv);


    }

    void HandleBirthMax() {
        double maxv=birthMax.getValue();
        if(birthMin.getValue()>maxv)
            birthMin.setValue(maxv);
        birthMin.setMax(maxv);


    }
    
   @FXML
    void HandleSubmit(ActionEvent event) throws Exception{
        double maxv=max.getValue();
        double minv=min.getValue();
        double minbirthv=birthMin.getValue();
        double maxbirthv=birthMin.getValue();
        JeuDeLaVie.setNbminsolitude((int)minv);
        JeuDeLaVie.setNbmaxsurpopulation((int)maxv);
        JeuDeLaVie.setNbmaxreproduction((int)maxbirthv);
        JeuDeLaVie.setNbminreproduction((int)minbirthv);
        MenuController.BackMenu();


    }
    
}
