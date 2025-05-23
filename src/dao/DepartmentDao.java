package dao;
import models.Department;
import java.util.List;

public interface DepartmentDao extends GenericDao<Department>{
    List<Department> getAllDepartmentByHospital(Long id);
    Department findDepartmentByName(String name);
}
