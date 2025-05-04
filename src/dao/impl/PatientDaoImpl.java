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
        // У нас уже есть 'add' метод от generic interface
        return "";
    }

    @Override
    public List<Patient> getAllPatientsFromHospital(Long id) {
        return Database.hospitals.stream()
                .filter(h -> h.getId().equals(id))
                .findFirst()
                .map(Hospital::getPatients)
                .orElse(Collections.emptyList());
    }

    @Override
    public Patient getPatientById(Long id) {
        return Database.patients.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Map<Integer, List<Patient>> getPatientByAge() {
        Map<Integer, List<Patient>> patientsMap = new HashMap<>();
        Database.patients.forEach(patient -> {
                    patientsMap.computeIfAbsent(patient.getAge(), k -> new ArrayList<>()).add(patient);
                });
        return patientsMap;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        List<Patient> sortedPatients = Database.patients;
        sortedPatients.sort(Comparator.comparing(Patient::getAge));
        return sortedPatients;
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        return Database.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(hospitalId))
                .findFirst()
                .map(hospital -> {
                    hospital.getPatients().add(patient);
                    Database.patients.add(patient);
                    return "New patient successfully added";
                })
                .orElse("Hospital by id '" + hospitalId + "' not found!");
    }

    @Override
    public void removeById(Long id) {
       boolean isDeletedInMainDatabase = Database.patients.removeIf(p -> p.getId().equals(id));
       boolean isDeletedInHospital = Database.hospitals.stream()
               .anyMatch(hospital -> hospital.getPatients().removeIf(p -> p.getId().equals(id)));
        if (isDeletedInMainDatabase || isDeletedInHospital) System.out.println("Patient successfully deleted");
        else System.out.println("Patient by id '" +id+ "' not found!");
    }

    @Override
    public String updateById(Long id, Patient patient) {
        return Database.patients.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(p -> {
                    p.setFirstName(patient.getFirstName());
                    p.setLastName(patient.getLastName());
                    p.setAge(patient.getAge());
                    Database.hospitals.forEach(hospital -> {
                        hospital.getPatients().stream()
                                .filter(pat -> pat.getId().equals(id))
                                .forEach(patient1 -> {
                                    patient1.setFirstName(patient.getFirstName());
                                    patient1.setLastName(patient.getLastName());
                                    patient1.setAge(patient.getAge());
                                });
                    });
                    return "Patient information successfully updated";
                })
                .orElse("Patient by id '"+id+"' not found!");
    }
}
