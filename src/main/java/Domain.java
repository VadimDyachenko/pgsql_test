import entity.Address;
import entity.EmplProj;
import entity.Employee;
import entity.Project;
import service.AddressService;
import service.EmplProjService;
import service.EmployeeService;
import service.ProjectService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * @author Vadim Dyachenko
 */
public class Domain {
    public static void main(String[] args) {
        AddressService addressService = new AddressService();
        EmployeeService employeeService = new EmployeeService();
        EmplProjService emplProjService = new EmplProjService();
        ProjectService projectService = new ProjectService();

        Address address = getAddress();

        Employee employee = getEmployee(address);

        Project project = getProject();

        EmplProj emplProj = getEmplProj(employee, project);

        try {
            addressService.add(address);
            employeeService.add(employee);
            projectService.add(project);
            emplProjService.add(emplProj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static EmplProj getEmplProj(Employee employee, Project project) {
        EmplProj emplProj = new EmplProj();
        emplProj.setEmployeeID(employee.getId());
        emplProj.setProjectID(project.getId());
        return emplProj;
    }

    private static Project getProject() {
        Project project = new Project();
        project.setId(2L);
        project.setTitle("Sport life to all");
        return project;
    }

    private static Employee getEmployee(Address address) {
        Employee employee = new Employee();
        employee.setId(2L);
        employee.setFirstName("Nick");
        employee.setLastName("Petrov");

        Calendar calendar = Calendar.getInstance();
        calendar.set(1995, Calendar.APRIL, 1);

        employee.setBirthday(new Date(calendar.getTime().getTime()));
        employee.setAdressID(address.getId());
        return employee;
    }

    private static Address getAddress() {
        Address address = new Address();
        address.setId(2L);
        address.setCountry("Ukraine");
        address.setCity("Kiev");
        address.setStreet("Rose");
        address.setPostCode("04000");
        return address;
    }
}
