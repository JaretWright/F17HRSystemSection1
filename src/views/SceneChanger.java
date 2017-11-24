package views;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Employee;

/**
 *  This class is used to change scenes in the system
 * @author jwright
 */
public class SceneChanger {
    /**
     * This method will accept the view.fxml filename, the ActionEvent and a title
     * for the new view
     */
    public void changeScenes(ActionEvent event, String viewName, String title) throws IOException
    {
        //load the .fxml file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = loader.load();
        
        //create the scene
        Scene scene = new Scene(parent);
        
        //get the stage (window) from the event passed in
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * This method will change scenes and preload the next scene with an Employee
     */
    public void changeScenes(ActionEvent event, String viewName, String title, 
                                Employee employee, ControllerInterface controllerInterface ) throws IOException
    {
        //load the .fxml file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewName));
        Parent parent = loader.load();
        
        Scene scene = new Scene(parent);
        
        //access the controller class and preload the employee
        controllerInterface = loader.getController();
        controllerInterface.preloadData(employee);
        
         //get the stage (window) from the event passed in
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
