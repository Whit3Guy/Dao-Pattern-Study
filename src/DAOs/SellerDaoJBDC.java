package DAOs;

import Db.DB;
import entities.Department;
import entities.Seller;
import interfaces.SellerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJBDC implements SellerDAO {
    @Override
    public void insert(Seller dep) {
    }

    @Override
    public void delete(int Id) {

    }

    @Override
    public Seller update(int Id, Seller dep) {
        return null;
    }

    @Override
    public Seller FindById(int Id) {
        return null;
    }

    @Override
    public List<Seller> FindAll() {
        return List.of();
    }
}
