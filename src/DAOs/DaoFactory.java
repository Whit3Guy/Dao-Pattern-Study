package DAOs;

import interfaces.DepartmentDAO;
import interfaces.SellerDAO;

public class DaoFactory {
    public static DepartmentDAO createDepartmentDAO()
    {
        return new DepartmentDaoJDBC();
    }

    public static SellerDAO createSellerDAO()
    {
        return new SellerDaoJBDC();
    }
}