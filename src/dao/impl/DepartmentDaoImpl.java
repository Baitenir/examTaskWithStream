package dao.impl;
import dao.DepartmentDao;
import dao.GenericDao;
import db.Database;
import models.Department;
import models.Hospital;
import myException.HospitalException;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao, GenericDao<Department> {
    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        return Database.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst()
                .map(Hospital::getDepartments)
                .orElse(List.of());
    }

    @Override
    public Department findDepartmentByName(String name) {
        return Database.departments.stream()
                .filter(h -> h.getDepartmentName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(()-> new HospitalException("Department by name '" +name+"' not found!"));
    }

    @Override
    public String add(Long hospitalId, Department department) {
        return Database.hospitals.stream()
                .filter(h -> h.getId().equals(hospitalId))
                .findFirst()
                .map(hospital -> {
                    hospital.getDepartments().add(department);
                    Database.departments.add(department);
                    return "Department successfully added to hospital '"+hospital.getHospitalName()+"' ";
                })
                .orElse("Hospital by id '" +hospitalId+ "' not found!");
    }

    @Override
    public void removeById(Long id) {
        boolean isDeletedInHospital = Database.hospitals.stream()
                .anyMatch(hospital -> hospital.getDepartments().removeIf(department -> department.getId().equals(id)));
        boolean isDeletedInMainDatabase = Database.departments.removeIf(department -> department.getId().equals(id));
        if (isDeletedInHospital || isDeletedInMainDatabase) System.out.println("Department with id '"+id+"' successfully deleted");
        else System.out.println("Department with id '"+id+"' not founded!");
    }

    @Override
    public String updateById(Long id, Department department) {
        return Database.departments.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .map(d -> {
                    d.setDepartmentName(department.getDepartmentName());
                    Database.hospitals.forEach(h ->{
                        h.getDepartments().stream()
                                .filter(dep -> dep.getId().equals(id))
                                .forEach(dep->{
                                    dep.setDepartmentName(department.getDepartmentName());
                                });
                    });
                    return "Doctor information successfully updated";
                })
                .orElse("Doctor with id '"+id+"' not found!");
    }
}
