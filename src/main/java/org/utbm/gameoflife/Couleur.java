/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utbm.gameoflife;
import javafx.scene.paint.Color;
/**
 *
 * @author Nero
 */
public enum Couleur {
    noir(Color.DARKSLATEGRAY),blanc(Color.ANTIQUEWHITE),vert(Color.YELLOWGREEN),rouge(Color.FIREBRICK),jaune(Color.GOLD),violet(Color.SLATEBLUE),orange(Color.DARKORANGE),bleu(Color.STEELBLUE),marron(Color.SIENNA);
    private final Color valeur;
    
    private Couleur(Color c) {  
         this.valeur = c ;  
    }
    public Color getValeur() {
        return valeur;
    }
    public static Color getValeurByInt(int value) {
        return Couleur.values()[value].getValeur();
    }
    
}
