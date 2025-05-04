package myException;

public class HospitalException extends RuntimeException {
    public HospitalException() {
    }

    public HospitalException(String message) {
        super(message);
    }
}
