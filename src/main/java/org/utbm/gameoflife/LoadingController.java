package org.utbm.gameoflife;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXPopup;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;
import io.datafx.controller.flow.context.ViewFlowContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javax.annotation.PostConstruct;
import org.utbm.gameoflife.datafx.ExtendedAnimatedFlowContainer;

@FXMLController(value = "/fxml/loading.fxml", title = "")
public class LoadingController {
    
        @PostConstruct
    public void init() throws Exception {
        
        

    }

    public static void Next(String nextPage,ViewFlowContext context, JFXDrawer drawer) throws Exception{
        System.out.print("eazezae");

                switch (nextPage)
                {
                  case "menu":
                        Flow innerFlow = new Flow(ButtonController.class);

                        final FlowHandler flowHandler = innerFlow.createHandler(context);
                        context.register("ContentFlowHandler", flowHandler);
                        context.register("ContentFlow", innerFlow);
                        final Duration containerAnimationDuration = Duration.millis(320);
                //{
                    try {
                        drawer.setContent(flowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
                    }
                    catch (FlowException ex) {
                        Logger.getLogger(LoadingController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                //}
                        context.register("ContentPane", drawer.getContent().get(0));
                    break;                
                  
                  default:
                    /*Action*/;             
                }
            }

        
        
    
    
}
