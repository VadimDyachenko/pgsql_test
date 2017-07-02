package Domain;

import entity.Address;
import entity.Employee;
import entity.Project;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vadim Dyachenko
 */
public class Domain {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Address address = new Address();
        address.setCountry("Ukraine");
        address.setCity("Kiev");
        address.setStreet("Rose");
        address.setPostCode("04000");

        Employee employee = new Employee();
        employee.setFirstName("Nick");
        employee.setLastName("Petrov");

        Calendar calendar = Calendar.getInstance();
        calendar.set(1995, Calendar.APRIL, 1);

        employee.setBirthday(new Date(calendar.getTime().getTime()));
        employee.setAddress(address);

        Project project = new Project();
        project.setTitle("Sport life to all");

        Set<Project> projects = new HashSet<>();
        projects.add(project);

        employee.setProjects(projects);

        session.save(address);
        session.save(employee);
        session.save(project);

        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
