package DAOs;

import entities.Department;
import interfaces.DepartmentDAO;

import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDAO {
    @Override
    public void insert(Department dep) {

    }

    @Override
    public void delete(int Id) {

    }

    @Override
    public Department update(int Id, Department dep) {
        return null;
    }

    @Override
    public Department FindById(int Id) {
        return null;
    }

    @Override
    public List<Department> FindAll() {
        return List.of();
    }
}
