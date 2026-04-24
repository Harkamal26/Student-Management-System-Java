import java.sql.*;
import java.util.*;

public class StudentDAO {

    // LOGIN
    public static boolean login(String user, String pass) {
    try {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM users WHERE username=? AND password=?"
        );

        ps.setString(1, user);
        ps.setString(2, pass);

        ResultSet rs = ps.executeQuery();

        boolean exists = rs.next();
        con.close();

        return exists;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
    // ADD
    public static void addStudent(String name, int age, String course,
                                  String subjects, int total,
                                  double percentage, String result) {

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO students(name, age, course, subjects, total, percentage, result) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);
            ps.setString(4, subjects);
            ps.setInt(5, total);
            ps.setDouble(6, percentage);
            ps.setString(7, result);

            ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // VIEW
    public static List<String> getAllStudents() {
        List<String> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                String data =
    rs.getInt("id") + " | " +
    rs.getString("name") + " | " +
    rs.getString("course") + " | " +
    rs.getString("subjects") + " | " +
    rs.getDouble("percentage") + " | " +
    rs.getString("result");

                list.add(data);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // SEARCH
    public static String searchStudentById(int id) {
    String result = "";

    try {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM students WHERE id=?"
        );

        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            result =
                "ID: " + rs.getInt("id") +
                "\nName: " + rs.getString("name") +
                "\nAge: " + rs.getInt("age") +
                "\nCourse: " + rs.getString("course") +
                "\nSubjects: " + rs.getString("subjects") +
                "\nPercentage: " + rs.getDouble("percentage") +
                "\nResult: " + rs.getString("result");
        } else {
            result = "❌ Student not found!";
        }

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    return result;
}

    // DELETE
    public static void deleteStudent(int id) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM students WHERE id=?"
            );

            ps.setInt(1, id);
            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//register
public static boolean register(String user, String pass) {
String result= " ";
    try {
        Connection con = DBConnection.getConnection();
	if(user.equals("") || pass.equals("")) {
	result = "Fill the info first";
	return false;}
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO users(username, password) VALUES (?, ?)"
        );

        ps.setString(1, user);
        ps.setString(2, pass);

        ps.executeUpdate();
        con.close();

        return true;

    } catch (Exception e) {
        System.out.println("Username already exists!");
        return false;
    }
}

    // UPDATE
    public static void updateStudent(int id, String field, String value) {

    try {
        Connection con = DBConnection.getConnection();

        String query = "UPDATE students SET " + field + "=? WHERE id=?";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, value);
        ps.setInt(2, id);

        int rows = ps.executeUpdate();

        if (rows > 0)
            System.out.println("Updated Successfully");
        else
            System.out.println("ID not found");

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}