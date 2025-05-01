
import db.AcceptUserInfo;
import db.GenId;
import models.Department;
import models.Doctor;
import models.Hospital;
import models.Patient;
import service.DepartmentService;
import service.DoctorService;
import service.HospitalService;
import service.PatientService;
import service.impl.DepartmentServiceImpl;
import service.impl.DoctorServiceImpl;
import service.impl.HospitalServiceImpl;
import service.impl.PatientServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HospitalService hospitalService = new HospitalServiceImpl();
        DepartmentService departmentService = new DepartmentServiceImpl();
        DoctorService doctorService = new DoctorServiceImpl();
        PatientService patientService = new PatientServiceImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    
                    MAIN MENU
                    1. About hospitals
                    2. About departments
                    3. About doctors
                    4. About patients
                    0. Exit
                    """);
            switch (AcceptUserInfo.getUserChoiceNumber()){
                case 0:
                    return;
                case 1: // todo this case about hospital
                    boolean isTrue = true;
                    while (isTrue){
                        System.out.println("""
                                
                                HOSPITALS MENU
                                1. Add hospital
                                2. Find hospital
                                3. Show all hospitals
                                4. Show all patients from hospital
                                5. Delete hospital
                                6. Show all hospitals by address
                                0. Exit
                                """);
                        switch (AcceptUserInfo.getUserChoiceNumber()){
                            case 0:
                                isTrue = false;
                                break;
                            case 1:
                                Hospital hospital = new Hospital();
                                hospital.setId(GenId.generateHospitalId());
                                System.out.println("Write name: ");
                                hospital.setHospitalName(scanner.nextLine());
                                System.out.println("Write address: ");
                                hospital.setAddress(scanner.nextLine());
                                System.out.println(hospitalService.addHospital(hospital));
                                break;
                            case 2:
                                System.out.println("Write hospital id: ");
                                System.out.println(hospitalService.findHospitalById(AcceptUserInfo.getUserChoiceID()));
                                break;
                            case 3:
                                System.out.println(hospitalService.getAllHospital());
                                break;
                            case 4:
                                System.out.println("Write hospital id: ");
                                System.out.println(hospitalService.getAllPatientFromHospital(AcceptUserInfo.getUserChoiceID()));
                                break;
                            case 5:
                                System.out.println("Write hospital id: ");
                                System.out.println(hospitalService.deleteHospitalById(AcceptUserInfo.getUserChoiceID()));
                                break;
                            case 6:
                                System.out.println("Write address: ");
                                System.out.println(hospitalService.getAllHospitalByAddress(scanner.nextLine()));
                                break;
                            default:
                                System.out.println("Incorrect value! Try again!");
                                break;
                        }
                    }
                    break;
                case 2:
                    boolean isTrue2 = true;
                    while (isTrue2){
                        System.out.println("""
                                
                                DEPARTMENT MENU
                                1. Add department
                                2. Update department
                                3. Delete department
                                4. Show all departments from hospital
                                5. Find department by name
                                0. Exit
                                """);
                        switch (AcceptUserInfo.getUserChoiceNumber()){
                            case 0:
                                isTrue2 = false;
                                break;
                            case 1:
                                Department department = new Department();
                                department.setId(GenId.generateDepartmentId());
                                System.out.println("Write name: ");
                                department.setDepartmentName(scanner.nextLine());
                                System.out.println("Write hospital id: ");
                                System.out.println(departmentService.add(AcceptUserInfo.getUserChoiceID(), department));
                                break;
                            case 2:
                                Department newDeportment = new Department();
                                System.out.println("Write new name: ");
                                newDeportment.setDepartmentName(scanner.nextLine());
                                System.out.println("Write id: ");
                                System.out.println(departmentService.updateById(AcceptUserInfo.getUserChoiceID(), newDeportment));
                                break;
                            case 3:
                                System.out.println("Write id: ");
                                departmentService.removeById(AcceptUserInfo.getUserChoiceID());
                                break;
                            case 4:
                                System.out.println("Write hospital id: ");
                                System.out.println(departmentService.getAllDepartmentByHospital(AcceptUserInfo.getUserChoiceID()));
                                break;
                            case 5:
                                System.out.println("Write name: ");
                                System.out.println(departmentService.findDepartmentByName(scanner.nextLine()));
                                break;
                            default:
                                System.out.println("Incorrect value! Try again!");
                                break;
                        }
                    }
                    break;
                case 3:
                    boolean isTrue3 = true;
                    while (isTrue3){
                        System.out.println("""
                                
                                DOCTORS MENU
                                1. Add doctor
                                2. Update doctor information
                                3. Delete doctor
                                4. Find doctor by id
                                5. Assign doctor to department
                                6. Show all doctors by hospital
                                7. Show all doctors by department
                                0. Exit
                                """);
                        switch(AcceptUserInfo.getUserChoiceNumber()){
                            case 0:
                                isTrue3 = false;
                                break;
                            case 1:
                                Doctor doctor = new Doctor();
                                doctor.setId(GenId.generateDoctorId());
                                System.out.println("Write first name: ");
                                doctor.setFirstName(scanner.nextLine());
                                System.out.println("Write last name: ");
                                doctor.setLastName(scanner.nextLine());
                                System.out.println("Write experience: ");
                                doctor.setExperienceYear(AcceptUserInfo.getUserChoiceNumber());
                                System.out.println("Write gender: ");
                                doctor.setGender(AcceptUserInfo.getUserGender());
                                System.out.println("Write hospital id: ");
                                System.out.println(doctorService.add(AcceptUserInfo.getUserChoiceID(), doctor));
                                break;
                            case 2:
                                Doctor newDoctor = new Doctor();
                                System.out.println("Write new first name: ");
                                newDoctor.setFirstName(scanner.nextLine());
                                System.out.println("Write new last name: ");
                                newDoctor.setLastName(scanner.nextLine());
                                System.out.println("Write new experience: ");
                                newDoctor.setExperienceYear(AcceptUserInfo.getUserChoiceNumber());
                                System.out.println("Write doctor id: ");
                                System.out.println(doctorService.updateById(AcceptUserInfo.getUserChoiceID(), newDoctor));
                                break;
                            case 3:
                                System.out.println("Write id: ");
                                doctorService.removeById(AcceptUserInfo.getUserChoiceID());
                                break;
                            case 4:
                                System.out.println("Write id: ");
                                System.out.println(doctorService.findDoctorById(AcceptUserInfo.getUserChoiceID()));
                                break;
                            case 5:
                                List<Long> ides = new ArrayList<>();
                                System.out.println("Write how many doctors do you assign to department?: ");
                                int c =0;
                                while (true){
                                    try {
                                        c = scanner.nextInt();
                                        break;
                                    } catch (RuntimeException e){
                                        System.err.println("Incorrect value! (write number)");
                                        scanner.nextLine();
                                    }
                                }
                                for (int i = 0; i < c; i++) {
                                    System.out.println("Write id: ");
                                    ides.add(AcceptUserInfo.getUserChoiceID());
                                }
                                System.out.println("Write department id: ");
                                System.out.println(doctorService.assignDoctorToDepartment(AcceptUserInfo.getUserChoiceID(), ides));
                                break;
                            case 6:
                                System.out.println("Write hospital id: ");
                                System.out.println(doctorService.getAllDoctorsByHospitalId(AcceptUserInfo.getUserChoiceID()));
                                break;
                            case 7:
                                System.out.println("Write department id: ");
                                System.out.println(doctorService.getAllDoctorsByDepartmentId(AcceptUserInfo.getUserChoiceID()));
                                break;
                            default:
                                System.out.println("Incorrect value try again!");
                                break;
                        }
                    }
                    break;
                case 4: // patient
                    boolean isTrue4 = true;
                    while (isTrue4){
                        System.out.println("""
                                
                                PATIENT MENU
                                1. Add patient
                                2. Update patient information
                                3. Delete patient
                                4. Find patient by id
                                5. Find patient by age
                                6. Sort patient by age
                                7. Show all patients from hospital
                                0. Exit
                                """);
                        switch (AcceptUserInfo.getUserChoiceNumber()){
                            case 0:
                                isTrue4 = false;
                                break;
                            case 1:
                                Patient patient = new Patient();
                                patient.setId(GenId.generatePatientId());
                                System.out.println("Write first name: ");
                                patient.setFirstName(scanner.nextLine());
                                System.out.println("Write last name: ");
                                patient.setLastName(scanner.nextLine());
                                System.out.println("Write age: ");
                                patient.setAge(AcceptUserInfo.getUserChoiceNumber());
                                System.out.println("Write gender (male/female): ");
                                patient.setGender(AcceptUserInfo.getUserGender());
                                System.out.println("Write hospital id: ");
                                System.out.println(patientService.add(AcceptUserInfo.getUserChoiceID(), patient));
                                break;
                            case 2:
                                Patient newPatient = new Patient();
                                System.out.println("Write new first name: ");
                                newPatient.setFirstName(scanner.nextLine());
                                System.out.println("Write new last name: ");
                                newPatient.setLastName(scanner.nextLine());
                                System.out.println("Write new age: ");
                                newPatient.setAge(AcceptUserInfo.getUserChoiceNumber());
                                System.out.println("Write id: ");
                                System.out.println(patientService.updateById(AcceptUserInfo.getUserChoiceID(), newPatient));
                                break;
                            case 3:
                                System.out.println("Write patient id: ");
                                patientService.removeById(AcceptUserInfo.getUserChoiceID());
                                break;
                            case 4:
                                System.out.println("Write id: ");
                                System.out.println(patientService.getPatientById(AcceptUserInfo.getUserChoiceID()));
                                break;
                            case 5:
                                Map<Integer, List<Patient>> patientsMap = patientService.getPatientByAge();
                                System.out.println("Write age: ");
                                System.out.println(patientsMap.get(AcceptUserInfo.getUserChoiceNumber()));
                                break;
                            case 6:
                                System.out.println("Write sort order (asc/desc): ");
                                System.out.println(patientService.sortPatientsByAge(scanner.nextLine()));
                                break;
                            case 7:
                                System.out.println("Write hospital id: ");
                                System.out.println(patientService.getAllPatientsFromHospital(AcceptUserInfo.getUserChoiceID()));
                                break;
                            default:
                                System.out.println("Incorrect value try again!");
                                break;
                        }
                    }
                default:
                    System.out.println("Incorrect value try again!");
                    break;
            }
        }
    }
}