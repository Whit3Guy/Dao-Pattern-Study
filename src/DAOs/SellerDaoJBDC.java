package DAOs;

import Db.DB;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import entities.Department;
import entities.Seller;
import exceptions.DbException;
import interfaces.SellerDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDaoJBDC implements SellerDAO {
    private final Connection conn;

    public SellerDaoJBDC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller dep) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("insert into seller (Name, Email, BirthDate, BaseSalary, DepartmentId) values (?,?,?,?,?)");
            instaintializeSellerDepId(st, dep);

            int rows = st.executeUpdate();

            System.out.printf("Rows affecteds: %d", rows);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public void delete(int Id) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");
            st.setInt(1, Id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public Seller update(int Id, Seller dep) {
        PreparedStatement st = null;
        try
        {
            st = conn.prepareStatement("UPDATE seller " +
                    "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " +
                    "WHERE Id = ?");
            // seta todos os ?
            instaintializeSellerDepId(st, dep);
            st.executeUpdate();
            return null;
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Seller> FindByDepartmentId(int Id) {
        PreparedStatement st;
        ResultSet rs;
        Department dp;
        Seller sl;
        List<Seller> res = new ArrayList<>();
        try
        {
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE DepartmentId = ? " +
                    "ORDER BY Name");
            st.setInt(1, Id);
            rs = st.executeQuery();
            while (rs.next()) {
                dp = instantializeDepartment(rs);
                sl = instantializeSeller(rs, dp);
                res.add(sl);
            }
            return res;
        }
        catch(SQLException e)
        {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Seller FindById(int Id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = this.conn.prepareStatement("SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE seller.Id = ?");

            st.setInt(1, Id);

            rs = st.executeQuery();


            if(rs.next()) {
                Department dp = instantializeDepartment(rs);
                return instantializeSeller(rs, dp);
            }
            return null;
        }
        catch (SQLException e)
        {
            throw new DbException(e.getMessage());
        }
        finally
        {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private void instaintializeSellerDepId(PreparedStatement st, Seller dep) throws SQLException{
        st.setString(1, dep.getName());
        st.setString(2, dep.getEmail());
        st.setDate(3, new java.sql.Date(dep.getBirthDate().getTime()));
        st.setDouble(4, dep.getBaseSalary());
        st.setInt(5, dep.getDepartment().getId());
    }

    private Seller instantializeSeller(ResultSet rs, Department dp) throws SQLException
    {
            int SellerId = rs.getInt("Id");
            String Name = rs.getString("Name");
            String Email = rs.getString("Email");
            java.util.Date date = new java.util.Date(rs.getDate("BirthDate").getTime());
            double baseSalary = rs.getDouble("BaseSalary");
            return new Seller(SellerId, Name, Email, date, baseSalary, dp);
    }

    private Department instantializeDepartment(ResultSet rs) throws SQLException
    {
        return new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
    }

    @Override
    public List<Seller> FindAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Seller> sellers = new ArrayList<>();
        Department dp;
        Seller sl;
        try
        {
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "ORDER BY Name");
            rs = st.executeQuery();

            while(rs.next())
            {
                dp = instantializeDepartment(rs);
                sl = instantializeSeller(rs, dp);
                sellers.add(sl);
            }

            return sellers;
        }
        catch(SQLException e)
        {
            throw new DbException(e.getMessage());
        }
        finally
        {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

    }
}
