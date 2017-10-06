package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import models.Employee;

/**
 * FXML Controller class
 *
 * @author JWright
 */
public class CreateEmployeeViewController implements Initializable {

    private ObservableList<Employee> employees;
    
    
    public void initialData(ObservableList<Employee> listOfEmp)
    {
        employees = listOfEmp;
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
