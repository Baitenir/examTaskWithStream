package dao.impl;
import dao.HospitalDao;
import db.Database;
import models.Hospital;
import models.Patient;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HospitalDaoImpl implements HospitalDao {
    @Override
    public String addHospital(Hospital hospital) {
        Database.hospitals.add(hospital);
        return "Hospital successfully added";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return Database.hospitals.stream()
                .filter(h -> h.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return Database.hospitals;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return Database.hospitals.stream()
                .filter(h-> h.getId().equals(id))
                .findFirst()
                .map(Hospital::getPatients)
                .orElse(null);
    }

    @Override
    public String deleteHospitalById(Long id) {
        boolean isDeleted = Database.hospitals.removeIf(h -> h.getId().equals(id));
        return isDeleted ? "Hospital by id '"+id+"' successfully deleted!" : "Hospital by id '"+id+"' not found!";
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        return Database.hospitals.stream()
                .filter(h -> h.getAddress().equalsIgnoreCase(address))
                .collect(Collectors.toMap(Hospital::getAddress, hospital -> hospital));
    }
}
