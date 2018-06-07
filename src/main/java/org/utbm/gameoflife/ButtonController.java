package org.utbm.gameoflife;

import com.jfoenix.controls.JFXButton;
import io.datafx.controller.FXMLController;
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

    }

    @FXML
    void HandleGameType(ActionEvent event){
       
    }
    @FXML
    void HandleOptions(ActionEvent event){
       
    }
    @FXML
    void HandleEditor(ActionEvent event){
       
    }
    
}
