package dao;
import models.Patient;
import java.util.List;
import java.util.Map;

public interface PatientDao extends GenericDao<Patient>{
    String addPatientsToHospital(Long id,List<Patient> patients);
    List<Patient> getAllPatientsFromHospital(Long id);
    Patient getPatientById(Long id);
    Map<Integer, List<Patient>> getPatientByAge();
    List<Patient> sortPatientsByAge(String ascOrDesc);
}
