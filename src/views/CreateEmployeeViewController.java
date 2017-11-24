package views;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Employee;
import models.HourlyEmployee;

/**
 * FXML Controller class
 *
 * @author JWright
 */
public class CreateEmployeeViewController implements Initializable {

    private ObservableList<Employee> employees;
    
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField sinTextField;
    @FXML private DatePicker dob;
    
    public void initialData(ObservableList<Employee> listOfEmp)
    {
        employees = listOfEmp;
    }
    
    public void createNewEmployeeButtonPushed(ActionEvent event) throws IOException
    {
        HourlyEmployee newEmp;
        try{
            newEmp = new HourlyEmployee(firstNameTextField.getText(), 
                    lastNameTextField.getText(),
                    sinTextField.getText(),
                    dob.getValue());
            newEmp.insertIntoDB();
            
            //change scenes to the all employee view
            SceneChanger sc = new SceneChanger();
            sc.changeScenes(event, "AllEmployeeView.fxml", "All Employees");
        } catch (SQLException ex) {
            Logger.getLogger(CreateEmployeeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       
    /**
     * This method will call load the all employee view
     */
    public void cancelButtonPushed(ActionEvent event) throws IOException
    {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "AllEmployeeView.fxml", "All Employees");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
