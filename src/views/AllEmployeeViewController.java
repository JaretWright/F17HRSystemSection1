
package views;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Employee;
import models.HourlyEmployee;

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
        employeeNumColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeNum"));
       
        //load dummy data
        employeeTable.setItems(getEmployees());
    }    
    
    public ObservableList<Employee> getEmployees()
    {
        //define an observable list
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        
        //add employees to the list
        employees.add(new HourlyEmployee("Jaret", "Mygosh", "123 456 789", LocalDate.of(1992, Month.MARCH, 15)));
        employees.add(new HourlyEmployee("Adam", "Sandler", "123 456 900", LocalDate.of(1966, Month.APRIL, 15)));
        employees.add(new HourlyEmployee("Bert", "Sesame", "123 555 900", LocalDate.of(1948, Month.APRIL, 22)));
        
        //return the list
        return employees;
    }
}
