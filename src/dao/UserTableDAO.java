package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.EmployeeUserTableModel;
import models.TechnicianUserTableModel;

public class UserTableDAO {
    private Connection conn;

    public UserTableDAO (Connection conn){
        this.conn = conn;
    }

    public List<EmployeeUserTableModel> getAllEmployees(){
        List<EmployeeUserTableModel> list = new ArrayList<>();
        StringBuilder query = new StringBuilder();

        query.append("SELECT ")
            .append("u.user_id, ")
            .append("e.emp_id, ")
            .append("u.username, ")
            .append("u.password, ")
            .append("CONCAT(e.last_name, ' ', e.first_name) AS name, ")
            .append("d.department_name, ")
            .append("e.job_title, ")
            .append("e.ACTIVE as status ")
            .append("FROM UserAccounts u ")
            .append("JOIN employeeusers eu ON eu.user_id = u.user_id ")
            .append("JOIN employees e ON e.emp_id = eu.emp_id ")
            .append("JOIN departments d ON d.department_id = e.dept_id ")
            .append("ORDER BY status DESC, user_id;"); 

            try (Statement st = conn.createStatement()){
                ResultSet rs = st.executeQuery(query.toString());

                while (rs.next()){
                    EmployeeUserTableModel e = new EmployeeUserTableModel(
                        rs.getInt(1), 
                        rs.getInt(2), 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5), 
                        rs.getString(6), 
                        rs.getString(7), 
                        rs.getBoolean(8)
                    );

                    list.add(e);
                }

                return list;
            } catch (SQLException e){
                System.out.println("[USERTABLEDAO] getAllEmployees error.");
                e.printStackTrace();
                return null;
            } 
    }

    public List<EmployeeUserTableModel> getAllEmployeesByDepartment(int deptID){
        List<EmployeeUserTableModel> list = new ArrayList<>();
        StringBuilder query = new StringBuilder();

        query.append("SELECT ")
            .append("u.user_id, ")
            .append("e.emp_id, ")
            .append("u.username, ")
            .append("u.password, ")
            .append("CONCAT(e.last_name, ' ', e.first_name) AS name, ")
            .append("d.department_name, ")
            .append("e.job_title, ")
            .append("e.ACTIVE as status ")
            .append("FROM UserAccounts u ")
            .append("JOIN employeeusers eu ON eu.user_id = u.user_id ")
            .append("JOIN employees e ON e.emp_id = eu.emp_id ")
            .append("JOIN departments d ON d.department_id = e.dept_id ")
            .append("WHERE d.department_id = ? ")
            .append("ORDER BY status DESC, user_id;"); 

            try (PreparedStatement ps = conn.prepareStatement(query.toString())){
                ps.setInt(1, deptID);
                ResultSet rs = ps.executeQuery();

                while (rs.next()){
                    EmployeeUserTableModel e = new EmployeeUserTableModel(
                        rs.getInt(1), 
                        rs.getInt(2), 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5), 
                        rs.getString(6), 
                        rs.getString(7), 
                        rs.getBoolean(8)
                    );

                    list.add(e);
                }

                return list;
            } catch (SQLException e){
                System.out.println("[USERTABLEDAO] getAllEmployees error.");
                e.printStackTrace();
                return null;
            } 
    }

    public EmployeeUserTableModel getEmployee(int userID){
        StringBuilder query = new StringBuilder();

        query.append("SELECT ")
            .append("u.user_id, ")
            .append("e.emp_id, ")
            .append("u.username, ")
            .append("u.password, ")
            .append("CONCAT(e.last_name, ' ', e.first_name) AS name, ")
            .append("d.department_name, ")
            .append("e.job_title, ")
            .append("e.ACTIVE as status ")
            .append("FROM UserAccounts u ")
            .append("JOIN employeeusers eu ON eu.user_id = u.user_id ")
            .append("JOIN employees e ON e.emp_id = eu.emp_id ")
            .append("JOIN departments d ON d.department_id = e.dept_id ")
            .append("WHERE u.user_id = ? ")
            .append("ORDER BY status DESC, user_id;"); 

            try (PreparedStatement ps = conn.prepareStatement(query.toString())){
                ps.setInt(1, userID);
                ResultSet rs = ps.executeQuery();

                if (rs.next()){
                    return new EmployeeUserTableModel(
                        rs.getInt(1), 
                        rs.getInt(2), 
                        rs.getString(3), 
                        rs.getString(4), 
                        rs.getString(5), 
                        rs.getString(6), 
                        rs.getString(7), 
                        rs.getBoolean(8)
                    );
                }

                System.out.println("no emp found with userid " + userID);
                return null;
            } catch (SQLException e){
                System.out.println("[USERTABLEDAO] getAllEmployees error.");
                e.printStackTrace();
                return null;
            } 
    }

    public List<TechnicianUserTableModel> getAllTechnicians(){
        List<TechnicianUserTableModel> list = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ")
        .append("u.user_id, ")
        .append("t.technician_id, ")
        .append("u.username, ")
        .append("u.password, ")
        .append("CONCAT(t.tech_lastName, ' ', t.tech_firstName) AS name, ")
        .append("t.ACTIVE AS status ")
        .append("FROM UserAccounts u ")
        .append("JOIN technicianusers tu ON tu.user_id = u.user_id ")
        .append("JOIN technicians t ON tu.technician_id = t.technician_id ")
        .append("ORDER BY status DESC, user_id;");

        String query = sb.toString();

        try (Statement st = conn.createStatement()){
            ResultSet rs = st.executeQuery(query.toString());

            while (rs.next()){
                TechnicianUserTableModel t = new TechnicianUserTableModel(
                    rs.getInt(1), 
                    rs.getInt(2), 
                    rs.getString(3), 
                    rs.getString(4), 
                    rs.getString(5), 
                    rs.getBoolean(6)
                );

                list.add(t);
            }

            return list;
        } catch (SQLException e){
            System.out.println("[USERTABLEDAO] getAllEmployees error.");
            e.printStackTrace();
            return null;
        } 
    }

    public TechnicianUserTableModel getTechnicianByUserID(int userID){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ")
        .append("u.user_id, ")
        .append("t.technician_id, ")
        .append("u.username, ")
        .append("u.password, ")
        .append("CONCAT(t.tech_lastName, ' ', t.tech_firstName) AS name, ")
        .append("t.ACTIVE AS status ")
        .append("FROM UserAccounts u ")
        .append("JOIN technicianusers tu ON tu.user_id = u.user_id ")
        .append("JOIN technicians t ON tu.technician_id = t.technician_id ")
        .append("WHERE u.user_id = ? ")
        .append("ORDER BY status DESC, user_id;");

        String query = sb.toString();

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return new TechnicianUserTableModel(
                    rs.getInt(1), 
                    rs.getInt(2), 
                    rs.getString(3), 
                    rs.getString(4), 
                    rs.getString(5), 
                    rs.getBoolean(6)
                );
            }

            return null;
        } catch (SQLException e){
            System.out.println("[USERTABLEDAO] getAllEmployees error.");
            e.printStackTrace();
            return null;
        } 
    }

}
