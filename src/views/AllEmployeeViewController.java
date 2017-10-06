
package views;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Employee;

/**
 * FXML Controller class
 *
 * @author JWright
 */
public class AllEmployeeViewController implements Initializable {

    //configure the table elements
    @FXML private TableView<Employee> employeeTable;
    @FXML private TableColumn<Employee, String> firstNameColumn;
    @FXML private TableColumn<Employee, String> lastNameColumn;
    @FXML private TableColumn<Employee, LocalDate> dobColumn;
    @FXML private TableColumn<Employee, String> positionColumn;
    @FXML private TableColumn<Employee, Integer> employeeNumColumn;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<Employee, LocalDate>("dateOfBirth"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("position"));
        employeeNumColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("lastName"));
       
       
    }    
    
}
