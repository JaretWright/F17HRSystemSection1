
package views;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
        try{
            employeeTable.setItems(getEmployees());
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
    }    
    
    public void createNewEmployeeButtonPushed(ActionEvent event) throws IOException
    {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "CreateEmployeeView.fxml", "Create new employee");
    }
    
    public ObservableList<Employee> getEmployees() throws SQLException
    {
        //define an observable list
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        
        //Read from the DB andd add employees to the list
        //connect to the DB
        Connection conn = null;  //connects to DB
        Statement statement = null; //is the SQL statement we want to run
        ResultSet resultSet = null; //is the response from the DB
        
        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrSystem?useSSL=false", 
                                                "student", "student");
            
            //2.  create a statement
            statement = conn.createStatement();
            
            //3.  create the sql query
            resultSet = statement.executeQuery("SELECT * FROM employees");
            
            //4.  create emplpoyee objects from each record
            while (resultSet.next())
            {
                employees.add(new HourlyEmployee(resultSet.getString("firstName"), 
                                                 resultSet.getString("lastName"),
                                                 resultSet.getString("socialInsuranceNumber"),
                                                 resultSet.getDate("dateOfBirth").toLocalDate()));
            }
        } 
        catch (SQLException e)
        {
            System.err.println(e);
        }
        finally
        {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
        
     
        //return the list
        return employees;
    }
}
