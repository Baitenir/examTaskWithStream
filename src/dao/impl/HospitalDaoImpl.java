package dao.impl;
import dao.HospitalDao;
import db.Database;
import models.Hospital;
import models.Patient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalDaoImpl implements HospitalDao {
    @Override
    public String addHospital(Hospital hospital) {
        Database.hospitals.add(hospital);
        return "New hospital successfully added";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        for (Hospital hospital : Database.hospitals) {
            if (hospital.getId().equals(id)) return hospital;
        }
        System.out.println("Hospital by id '" + id + "' not found!");
        return null;
    }

    @Override
    public List<Hospital> getAllHospital() {
        return Database.hospitals;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        for (Hospital hospital : Database.hospitals) {
            if (hospital.getId().equals(id)){
                return hospital.getPatients();
            }
        }
        System.out.println("Hospital by id '" + id + "' not found!");
        return null;
    }

    @Override
    public String deleteHospitalById(Long id) {
        boolean isDelete = Database.hospitals.removeIf(h -> h.getId().equals(id));
        return isDelete ? "Hospital successfully deleted!" : "Hospital by id '\" + id + \"' not found!";
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        Map<String, Hospital> res = new HashMap<>();
        for (Hospital hospital : Database.hospitals) {
            if (hospital.getAddress().equals(address)){
                res.put(hospital.getHospitalName(), hospital);
            }
        }
        return res;
    }

}
