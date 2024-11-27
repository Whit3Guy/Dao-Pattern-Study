import Db.DB;
import exceptions.DbException;

import java.sql.Connection;

public class Main {
    public static void main(String[] args)
    {
        Connection conn = null;
        conn = DB.getConnection();
        DB.closeConnection();
        System.out.println("funfou");
    }
}