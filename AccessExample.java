import java.sql.*;
import java.util.Scanner;

public class AccessExample {

    public void upd(Connection con, String sql, String name, int sa, int id) {
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, sa);
            ps.setInt(3, id);
            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println("data update");
            } else {
                System.out.println("data not update");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void ins(Connection con, String sql, String name, int sa) {
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, sa);
            ps.setString(2, name);
            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println("data inserted");
            } else {
                System.out.println("data not inserted");

            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void del(Connection con, String sql, int id) {
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println("data DELETE");
            } else {
                System.out.println("data not DELETE");

            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void res(Connection con) {
        try {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM A1");
            System.out.printf(" \n\tid \tname \tsalary");
            while (rs.next()) {
                /*
                 * System.out.println(
                 * rs.getInt("ID") + " " +
                 * rs.getString("NAME") + " " +
                 * rs.getInt("SALARY") + " "
                 * 
                 * );
                 */
                System.out.printf("\n\t%d \t%s \t%d", rs.getInt("ID"), rs.getString("NAME"), rs.getInt("SALARY"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {

        try {
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            } catch (ClassNotFoundException ex) {
                System.getLogger(AccessExample.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }

            String dbURL = "jdbc:ucanaccess://ACCOUNT.accdb";
            Connection conn = DriverManager.getConnection(dbURL);

            Scanner sc = new Scanner(System.in);
            AccessExample obj = new AccessExample();

            while (true) {

                System.out.println("\n1)Insert\n2)update\n3)delete\n4)display\n5)exit\n");
System.out.println("enter number:");
                int n = sc.nextInt();

                switch (n) {

                    case 1 -> {
                        String sql = "INSERT INTO A1(SALARY,NAME) VALUES(?,?)";

                        System.out.println("name");
                        sc.nextLine();
                        String name = sc.nextLine();
                        // sc.nextLine();
                        System.out.println("salary");
                        int sa = sc.nextInt();
                        obj.ins(conn, sql, name, sa);

                    }
                    case 2 -> {
                        obj.res(conn);

                        String sql = "UPDATE A1 SET NAME=?,SALARY=? WHERE ID=?";
                        System.out.println("name");
                        sc.nextLine();
                        String name = sc.nextLine();
                        // sc.nextLine();
                        System.out.println("salary");
                        int sa = sc.nextInt();
                        System.out.println("id");
                        int id = sc.nextInt();
                        obj.upd(conn, sql, name, sa, id);

                    }
                    case 3 -> {
                        obj.res(conn);

                        String sql = "DELETE FROM A1 WHERE ID=?";
                        System.out.println("id");
                        int id = sc.nextInt();
                        obj.del(conn, sql, id);
                    }
                    case 4 -> {
                        obj.res(conn);

                    }
                    default -> {
                        conn.close();
                        sc.close();
                        System.out.println("Program ended.");
                        return;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}