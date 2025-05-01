package dao.impl;
import dao.DepartmentDao;
import dao.GenericDao;
import db.Database;
import models.Department;
import models.Hospital;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao, GenericDao<Department> {
    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        for (Hospital hospital : Database.hospitals) {
            if (hospital.getId().equals(id)) return hospital.getDepartments();
        }
        System.out.println("Hospital by id '" + id +"' not found!");
        return null;
    }

    @Override
    public Department findDepartmentByName(String name) {
        for (Department department : Database.departments) {
            if (department.getDepartmentName().equals(name)){
                return department;
            }
        }
        System.out.println("Department by name '" + name + "' not found!");
        return null;
    }

    // generics
    @Override
    public String add(Long hospitalId, Department department) {
        Database.departments.add(department);
        for (Hospital hospital : Database.hospitals) {
            if (hospital.getId().equals(hospitalId)) {
                hospital.getDepartments().add(department);
                return "Department successfully added to hospital '"+hospital.getHospitalName()+"' ";
            }
        }
        return "Hospital by id '" + hospitalId + "' not found!";
    }

    @Override
    public void removeById(Long id) {
        Database.departments.removeIf(d -> d.getId().equals(id));
        boolean isDeleted = false;
        for (Hospital hospital : Database.hospitals) {
            isDeleted = hospital.getDepartments().removeIf(department -> department.getId().equals(id));
        }
        if (isDeleted) System.out.println("Deportment successfully deleted!");
        else System.out.println("Deportment by id '" + id + "' not found!");
    }

    @Override
    public String updateById(Long id, Department department) {
        for (Department department1 : Database.departments) {
            if (department == null) continue;
            if (department1.getId().equals(id)) department1.setDepartmentName(department.getDepartmentName());
        }
        for (Hospital hospital : Database.hospitals) {
            if (hospital == null) continue;
            for (Department hospitalDepartment : hospital.getDepartments()) {
                if (hospitalDepartment == null) continue;
                if (hospitalDepartment.getId().equals(id)) {
                    hospitalDepartment.setDepartmentName(department.getDepartmentName());
                    return "Deportment information successfully updated!";
                }
            }
        }
        return "Deportment by id '" + id + "' not found!";
    }
}
