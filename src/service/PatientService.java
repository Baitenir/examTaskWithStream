package service;
import models.Patient;
import java.util.List;
import java.util.Map;

public interface PatientService extends GenericService<Patient>{
    String addPatientsToHospital(Long id, List<Patient> patients);
    List<Patient> getAllPatientsFromHospital(Long id);
    Patient getPatientById(Long id);
    Map<Integer, List<Patient>> getPatientByAge();
    List<Patient> sortPatientsByAge(String ascOrDesc);
}
