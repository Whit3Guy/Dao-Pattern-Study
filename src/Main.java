import DAOs.DaoFactory;
import entities.Department;
import entities.Seller;
import interfaces.SellerDAO;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.util.Date date = spf.parse("15/06/2002");
            Seller testSeller = new Seller(3, "Gabriel", "gabrielraialsantos@gmail.com", date, 1200.2, new Department(1,"oioioio"));
            SellerDAO sel = DaoFactory.createSellerDAO();
            List<Seller> res = sel.FindAll();
            sel.insert(testSeller);
        for(Seller item : res)
        {
            System.out.println(item);
        }
        System.out.println("funfou");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
