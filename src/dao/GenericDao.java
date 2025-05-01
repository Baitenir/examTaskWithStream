package dao;

// todo for (Doctor, Patient, Department)
public interface GenericDao <T>{
    String add(Long  hospitalId, T t);
    void removeById(Long id);
    String updateById(Long id, T t);
}
