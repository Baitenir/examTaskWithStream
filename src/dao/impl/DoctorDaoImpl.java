package dao.impl;
import dao.DoctorDao;
import dao.GenericDao;
import db.Database;
import models.Department;
import models.Doctor;
import models.Hospital;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao, GenericDao<Doctor> {
    @Override
    public Doctor findDoctorById(Long id) {
        for (Doctor doctor : Database.doctors) {
            if (doctor.getId().equals(id)){
                return doctor;
            }
        }
        System.out.println("Doctor by id '" + id + "' not found!");
        return null;
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
//        List<Doctor> doctors = new ArrayList<>();
        int forMassage = 0;
        for (Long id : doctorsId) {
            for (Doctor doctor : Database.doctors) {
                if (id.equals(doctor.getId())){
                    for (Department department : Database.departments) {
                        if (department.getId().equals(departmentId)){
                            department.getDoctors().add(doctor);
                            forMassage++;
                        }
                    }
                }
            }
        }
        return forMassage + " doctors successfully added";
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        for (Hospital hospital : Database.hospitals) {
            if (hospital.getId().equals(id)){
                return hospital.getDoctors();
            }
        }
        System.out.println("Hospital by id '" + id + "' not found!");
        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        for (Department department : Database.departments) {
            if (department.getId().equals(id)) return department.getDoctors();
        }
        System.out.println("Department by id '" + id + "' not found!");
        return null;
    }

    // todo generics:
    @Override
    public String add(Long hospitalId, Doctor doctor) {
        Database.doctors.add(doctor);
        for (Hospital hospital : Database.hospitals) {
            if (hospital.getId().equals(hospitalId)){
                hospital.getDoctors().add(doctor);
                return "New doctor successfully added";
            }
        }
        return "Hospital by id '" + hospitalId + "' not found!";
    }

    @Override
    public void removeById(Long id) {
        boolean isDeletedInHospitals= false;
        boolean isDletedInDepartments = false;

        // delete doctor in main database
        boolean isDeletedInMainDatabase =  Database.doctors.removeIf(h -> h.getId().equals(id));

        // delete doctor in hospital database
        for (Hospital hospital : Database.hospitals) {
            boolean isDeleted = hospital.getDoctors().removeIf(doc -> doc.getId().equals(id));
            if (isDeleted) isDeletedInHospitals = true;
        }
        // delete doctor in department database
        for (Department department : Database.departments) {
            boolean isDelete = department.getDoctors().removeIf(doc -> doc.getId().equals(id));
            if (isDelete) isDletedInDepartments = true;
        }
        if (isDeletedInMainDatabase || isDeletedInHospitals || isDletedInDepartments) System.out.println("Doctor successfully deleted");
        else System.out.println("Doctor by id '"+id+"' not found!");
    }

    @Override
    // TODO later time check this method
    public String updateById(Long id, Doctor doctor) {
        for (Doctor doctor1 : Database.doctors) {
            if (doctor1.getId().equals(id)){
                doctor1.setFirstName(doctor.getFirstName());
                doctor1.setLastName(doctor.getFirstName());
                doctor1.setExperienceYear(doctor.getExperienceYear());
                return "Doctor information successfully updated";
            }
        }
        return "Doctor by id '" + id + "' not found!";
    }
}
