package interfaces;

import entities.Seller;
import java.util.List;

public interface SellerDAO {
    void insert(Seller dep);
    void delete(int Id);
    Seller update(int Id, Seller dep);
    Seller FindById(int Id);
    List<Seller> FindAll();
}
