/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utbm.gameoflife;

import com.jfoenix.controls.JFXComboBox;
import io.datafx.controller.FXMLController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Nero
 */
@FXMLController(value = "/fxml/Gametype.fxml", title = "")
public class GametypeController implements Initializable {

    @FXML
    private JFXComboBox jfxComboBox;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
