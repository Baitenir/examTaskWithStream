package db;
import models.Department;
import models.Doctor;
import models.Hospital;
import models.Patient;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public static List<Department> departments = new ArrayList<>();
    public static List<Doctor> doctors = new ArrayList<>();
    public static List<Hospital> hospitals = new ArrayList<>();
    public static List<Patient> patients = new ArrayList<>();
}
