package dao.impl;
import dao.DoctorDao;
import dao.GenericDao;
import db.Database;
import models.Department;
import models.Doctor;
import models.Hospital;
import java.util.Collections;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao, GenericDao<Doctor> {

    @Override
    public Doctor findDoctorById(Long id) {
        return Database.doctors.stream()
                .filter(doctor -> doctor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        return Database.departments.stream()
                .filter(d -> d.getId().equals(departmentId))
                .findFirst()
                .map(department -> {
                    List<Doctor> doctors = Database.doctors.stream()
                            .filter(d -> doctorsId.contains(d.getId()))
                            .toList();
                    department.getDoctors().addAll(doctors);
                    return "Successfully assigned";
                })
                .orElse("Department by id '"+departmentId+"' not found!");
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        return Database.hospitals.stream()
                .filter(h -> h.getId().equals(id))
                .findFirst()
                .map(Hospital::getDoctors)
                .orElse(Collections.emptyList());
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        return Database.departments.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .map(Department::getDoctors)
                .orElse(Collections.emptyList());
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        return Database.hospitals.stream()
                .filter(h -> h.getId().equals(hospitalId))
                .findFirst()
                .map(hospital -> {
                    hospital.getDoctors().add(doctor);
                    Database.doctors.add(doctor);
                    return "New doctor successfully added";
                })
                .orElse("Hospital with id '"+hospitalId+"' not found!");
    }

    @Override
    public void removeById(Long id) {
        boolean isDeletedInHospital = Database.hospitals.stream()
                .anyMatch(hospital -> hospital.getDoctors().removeIf(doctor -> doctor.getId().equals(id)));
        boolean isDeletedInMainDatabase = Database.doctors.removeIf(doctor -> doctor.getId().equals(id));
        if (isDeletedInMainDatabase || isDeletedInHospital) System.out.println("Doctor with id '"+id+"' successfully deleted");
        else System.out.println("Doctor with id '"+id+"' not found!");
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        // Updating doctor info from in main database
        return Database.doctors.stream()
                .filter(doc -> doc.getId().equals(id))
                .findFirst()
                .map(doc1 -> {
                    doc1.setFirstName(doctor.getFirstName());
                    doc1.setLastName(doctor.getLastName());
                    doc1.setExperienceYear(doctor.getExperienceYear());
                    // Updating doctor info in hospital database
                    Database.hospitals.forEach(hospital -> {
                        hospital.getDoctors().stream()
                                .filter(d -> d.getId().equals(id))
                                .forEach(d -> {
                                    d.setFirstName(doctor.getFirstName());
                                    d.setLastName(doctor.getLastName());
                                    d.setExperienceYear(doctor.getExperienceYear());
                                });
                    });
                    return "Doctor information successfully updated";
                })
                .orElse("Doctor with id '" + id +"' not found!");
    }
}
