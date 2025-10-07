

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {

    public void displayListStudent() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            CallableStatement callableStatement = connection.prepareCall("{call get_all_students()}");
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getInt("student_id"));
                student.setEmail(resultSet.getString("email"));
                student.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
                student.setFullName(resultSet.getString("full_name"));
                students.add(student);
            }

        } catch (Exception e) {
            System.out.println("Lấy dữ liệu ko thành công");
        }
        if (students.isEmpty()) {
            System.out.println("Danh sách sinh viên trống !");
        }else {
            System.out.println("Danh sách sinh viên");
            for (Student student : students) {
                student.display();
            }
        }
    }

    public void addStudent(Scanner scanner) {
        Student student = new Student();
        student.setFullName(Validator.getString(scanner, "Mời nhập tên: "));
        student.setEmail(Validator.getString(scanner, "Mời nhập  email : "));
        student.setDateOfBirth(Validator.getLocalDate(scanner, "Mời nhập ngày sinh (dd/mm/yyyy) : "));
        try (Connection connection = Database.getConnection()) {
            CallableStatement callableStatement = connection.prepareCall("{call add_Student(?,?,?)}");
            callableStatement.setString(1, student.getFullName());
            callableStatement.setDate(2, Date.valueOf(student.getDateOfBirth()));
            callableStatement.setString(3, student.getEmail());
            boolean rs = callableStatement.executeUpdate() > 0;
            if (rs){
                System.out.println("Thêm thành công");
            }else {
                System.out.println("Thêm thất bại");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void updateStudent(Scanner scanner) {
        int id = Validator.getInt(scanner, "Mời nhập id sinh viên cần cập nhập : ");
        Student oldStudent = findById(id);
        if (oldStudent == null) {
            System.out.println("Sinh viên không tồn tại");
        }else {
            Student student = new Student();
            student.setStudentId(oldStudent.getStudentId());
            student.setFullName(Validator.getString(scanner, "Mời nhập tên sinh viên : "));
            student.setEmail(Validator.getString(scanner, "Mời nhập email : "));
            student.setDateOfBirth(Validator.getLocalDate(scanner, "Mời nhập ngày sinh (dd/mm/yyyy) : "));
            try (Connection connection = Database.getConnection()) {
                CallableStatement callableStatement = connection.prepareCall("{call update_student(?,?,?,?)}");
                callableStatement.setInt(1, student.getStudentId());
                callableStatement.setString(2, student.getFullName());
                callableStatement.setDate(3, Date.valueOf(student.getDateOfBirth()));
                callableStatement.setString(4, student.getEmail());
                boolean rs = callableStatement.executeUpdate() > 0;
                if (rs) {
                    System.out.println("Đã cập nhập thành công");
                }else {
                    System.out.println("đã xảy ra lỗi");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public void deleteStudent(Scanner scanner) {
        int studentId = Validator.getInt(scanner, "Mời nhập id sinh viên cần xóa : ");
        Student student = findById(studentId);
        if (student == null) {
            System.out.println("Sinh viên không tồn tại");
        }else {
            try (Connection connection = Database.getConnection()) {
                CallableStatement callableStatement = connection.prepareCall("{call delete_student(?)}");
                callableStatement.setInt(1, studentId);
                boolean rs = callableStatement.executeUpdate() > 0;
                if (rs){
                    System.out.println("Xóa thành công !");
                }else {
                    System.out.println("Không thể xóa !");
                }
            }catch (Exception e) {
                System.out.println("Xóa thất bại !");
            }
        }
    }

    public Student findById(int studentId) {

        try (Connection connection = Database.getConnection()) {
            CallableStatement callableStatement = connection.prepareCall("{call find_student_by_id(?)}");
            callableStatement.setInt(1, studentId);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getInt("student_id"));
                student.setFullName(resultSet.getString("full_name"));
                student.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
                student.setEmail(resultSet.getString("email"));
                return student ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void searchStudentByName(Scanner scanner) {
        String search = Validator.getString(scanner, "Mời nhập sinh viên cần tìm : ");
        List<Student> students = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            CallableStatement callableStatement = connection.prepareCall("{call search_student_by_name(?)}");
            callableStatement.setString(1, search);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getInt("student_id"));
                student.setEmail(resultSet.getString("email"));
                student.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
                student.setFullName(resultSet.getString("full_name"));
                students.add(student);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (students.isEmpty()){
            System.out.println("Không tim thấy sinh viên nào có chứa tên : " + search);
        }else {
            System.out.println("Kết quả sau khi tìm kiếm với tên : " + search);
            for(Student student : students){
                student.display();
            }
        }
    }
}