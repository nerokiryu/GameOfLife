/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utbm.gameoflife;
import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import javax.annotation.PostConstruct;

import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import org.utbm.gameoflife.datafx.ExtendedAnimatedFlowContainer;

@FXMLController(value = "/fxml/menu.fxml", title = "Material Design Example")
public final class MenuController {

    

    @FXMLViewFlowContext
    private static ViewFlowContext context;

    @FXML
    private  StackPane root;

    @FXML
    private static JFXButton button;

    @FXML
    private StackPane optionsBurger;
    @FXML
    private JFXRippler optionsRippler;
    @FXML
    private static JFXDrawer drawer;

    private JFXPopup toolbarPopup;
    
   @FXML
    void HandleBackMenu(ActionEvent event) throws Exception{
        //handle button action
        BackMenu();
        

    }
    static void BackMenu() throws Exception{
        //return to menu
        button.setVisible(false);
        Loading();  
        LoadingController.Next("menu", context, drawer);


    }
    
    static void  GoEditor() throws FlowException{
        // display editor panel
        button.setVisible(true);
        Flow innerFlow = new Flow(EditorController.class);

        final FlowHandler flowHandler = innerFlow.createHandler(context);
        context.register("ContentFlowHandler", flowHandler);
        context.register("ContentFlow", innerFlow);
        final Duration containerAnimationDuration = Duration.millis(320);
        drawer.setContent(flowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
        context.register("ContentPane", drawer.getContent().get(0));
    }
    static void  GoOptions() throws FlowException{
        // display options panel
        button.setVisible(true);
        Flow innerFlow = new Flow(OptionsController.class);

        final FlowHandler flowHandler = innerFlow.createHandler(context);
        context.register("ContentFlowHandler", flowHandler);
        context.register("ContentFlow", innerFlow);
        final Duration containerAnimationDuration = Duration.millis(320);
        drawer.setContent(flowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
        context.register("ContentPane", drawer.getContent().get(0));
    }
    
    static void Loading()throws FlowException{
       // display loading panel
       Flow innerFlow = new Flow(LoadingController.class);
       final FlowHandler flowHandler = innerFlow.createHandler(context);
       context.register("ContentFlow", innerFlow);
       context.register("ContentFlowHandler", flowHandler);
       final Duration containerAnimationDuration = Duration.millis(320);
        try {
            drawer.setContent(flowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
        }
        catch (FlowException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
       context.register("ContentPane", drawer.getContent().get(0));
    }
    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() throws Exception {
        // build the main screen
        button.setVisible(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainPopup.fxml"));
        loader.setController(new InputController());
        toolbarPopup = new JFXPopup(loader.load());

        optionsBurger.setOnMouseClicked(e -> toolbarPopup.show(optionsBurger,
                                                               PopupVPosition.TOP,
                                                               PopupHPosition.RIGHT,
                                                               -12,
                                                               15));

        // create the inner flow and content
        context = new ViewFlowContext();
        // set the default controller
        Flow innerFlow = new Flow(ButtonController.class);

        final FlowHandler flowHandler = innerFlow.createHandler(context);
        context.register("ContentFlowHandler", flowHandler);
        context.register("ContentFlow", innerFlow);
        final Duration containerAnimationDuration = Duration.millis(320);
        drawer.setContent(flowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
        context.register("ContentPane", drawer.getContent().get(0));

    }

    public static final class InputController {
        @FXML
        private JFXListView<?> toolbarPopupList;

        // close application
        @FXML
        private void submit() {
            if (toolbarPopupList.getSelectionModel().getSelectedIndex() == 1) {
                Platform.exit();
            }
        }
    }

    static void GoMenuGameType()throws Exception {
        // display menu game type panel
        button.setVisible(true);
        Flow innerFlow = new Flow(GametypeController.class);

        final FlowHandler flowHandler = innerFlow.createHandler(context);
        context.register("ContentFlowHandler", flowHandler);
        context.register("ContentFlow", innerFlow);
        final Duration containerAnimationDuration = Duration.millis(320);
        drawer.setContent(flowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
        context.register("ContentPane", drawer.getContent().get(0));
    }
}