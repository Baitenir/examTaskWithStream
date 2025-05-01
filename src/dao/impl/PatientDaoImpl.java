package dao.impl;
import dao.GenericDao;
import dao.PatientDao;
import db.Database;
import models.Hospital;
import models.Patient;

import java.util.*;

public class PatientDaoImpl implements PatientDao, GenericDao<Patient> {

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        // Бессмысленный метод так как у нас уже есть add метод от generic interface
        // вместо него я добавил метод "getAllPatientFromHospital"
        return "";
    }

    @Override
    public List<Patient> getAllPatientsFromHospital(Long id) {
        for (Hospital hospital : Database.hospitals) {
            if (hospital.getId().equals(id)) return hospital.getPatients();
        }
        System.out.println("Hospital by id '"+id+"' not found!");
        return null;
    }

    @Override
    public Patient getPatientById(Long id) {
        for (Patient patient : Database.patients) {
            if (patient.getId().equals(id)) return patient;
        }
        System.out.println("Patient by id '" + id + "' not found!");
        return null;
    }

    @Override
    public Map<Integer, List<Patient>> getPatientByAge() {
        Map<Integer, List<Patient>> patientMap = new HashMap<>();
        for (Patient patient : Database.patients) {
            patientMap.computeIfAbsent(patient.getAge(), k -> new ArrayList<>()).add(patient);
        }
        System.out.println("All patients successfully sorted by age");
        return patientMap;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        List<Patient> patients = Database.patients;
        if (ascOrDesc.equals("asc")){
            patients.sort(Comparator.comparingInt(Patient::getAge));
        } else if (ascOrDesc.equals("desc")) {
            patients.sort(Comparator.comparingInt(Patient::getAge).reversed());
        } else {
            System.out.println("Incorrect value! (asc/desc)");
        }
        return patients;
    }

    // generics
    @Override
    public String add(Long hospitalId, Patient patient) {
        // add patient to main database
        Database.patients.add(patient);
        // add patient to hospital
        for (Hospital hospital : Database.hospitals) {
            if (hospital.getId().equals(hospitalId)) {
                hospital.getPatients().add(patient);
                return "New patient successfully added";
            }
        }
        return "Hospital by id '" + hospitalId + "' not found!";
    }

    @Override
    public void removeById(Long id) {
        boolean isResultForMainDatabase =  Database.patients.removeIf(p -> p.getId().equals(id));
        boolean isResultForInHospitals = false;
        for (Hospital hospital : Database.hospitals) {
            boolean isDeleted = hospital.getPatients().removeIf(p -> p.getId().equals(id));
            if (isDeleted) isResultForInHospitals = true;
        }
        if (isResultForMainDatabase || isResultForInHospitals) System.out.println("Patient successfully deleted");
        else System.out.println("Patient by id '" + id + "' not found!");
    }

    @Override
    public String updateById(Long id, Patient patient) {
        for (Patient patient1 : Database.patients) {
            if (patient1.getId().equals(id)){
                patient1.setFirstName(patient.getFirstName());
                patient1.setLastName(patient.getLastName());
                patient1.setAge(patient.getAge());
                return "Patient information successfully updated!";
            }
        }
        return "Patient by id '" + id + "' not found!";
    }
}
