package interfaces;

import entities.Department;

import java.util.List;

public interface DepartmentDAO {
    void insert(Department dep);
    void delete(int Id);
    Department update(int Id, Department dep);
    Department FindById(int Id);
    List<Department> FindAll();
}
