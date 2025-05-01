package db;

public class GenId {
    private static Long idForHospital = 0L;
    private static Long idForDepartment = 0L;
    private static Long idForDoctor = 0L;
    private static Long idForPatient = 0L;

    public static Long generateHospitalId (){
        return ++idForHospital;
    }
    public static Long generateDepartmentId (){
        return ++idForDepartment;
    }
    public static Long generateDoctorId (){
        return ++idForDoctor;
    }
    public static Long generatePatientId (){
        return ++idForPatient;
    }
}
