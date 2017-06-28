package entity;

/**
 * @author Vadim Dyachenko
 */
public class EmplProj {
    private Long employeeID;
    private Long projectID;

    public EmplProj() {
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmplProj emplProj = (EmplProj) o;

        if (employeeID != null ? !employeeID.equals(emplProj.employeeID) : emplProj.employeeID != null) return false;
        return projectID != null ? projectID.equals(emplProj.projectID) : emplProj.projectID == null;
    }

    @Override
    public int hashCode() {
        int result = employeeID != null ? employeeID.hashCode() : 0;
        result = 31 * result + (projectID != null ? projectID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EmplProj{" +
                "employeeID=" + employeeID +
                ", projectID=" + projectID +
                '}';
    }
}
