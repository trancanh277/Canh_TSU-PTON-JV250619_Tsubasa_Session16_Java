package baitap06;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManagement {

    public void listTasks() {
        List<Task> tasks = new ArrayList<>();
        try (Connection conn = Database.getConnection()) {
            CallableStatement callSt = conn.prepareCall("{call list_tasks()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Task t = new Task();
                t.setId(rs.getInt("id"));
                t.setTaskName(rs.getString("task_name"));
                t.setStatus(rs.getString("status"));
                tasks.add(t);
            }
        } catch (Exception e) {
            System.out.println(" Lỗi khi lấy danh sách: ");
        }

        if (tasks.isEmpty()) {
            System.out.println(" Danh sách công việc trống!");
        } else {
            System.out.println("\n DANH SÁCH CÔNG VIỆC:");
            tasks.forEach(Task::display);
        }
    }

    public void addTask(Scanner sc) {
        String name = Validator.getString(sc, "Nhập tên công việc: ");
        System.out.print("Trạng thái (1. Chưa hoàn thành / 2. Đã hoàn thành): ");
        String status = sc.nextLine().equals("2") ? "Đã hoàn thành" : "Chưa hoàn thành";

        try (Connection conn = Database.getConnection()) {
            CallableStatement callSt = conn.prepareCall("{call add_task(?, ?)}");
            callSt.setString(1, name);
            callSt.setString(2, status);
            boolean rs = callSt.executeUpdate() > 0;
            System.out.println(rs ? " Thêm công việc thành công!" : " Thêm thất bại!");
        } catch (Exception e) {
            System.out.println(" Lỗi khi thêm công việc: ");
        }
    }

    public void updateTaskStatus(Scanner sc) {
        int id = Validator.getInt(sc, "Nhập ID công việc cần cập nhật: ");
        System.out.print("Nhập trạng thái mới (1. Chưa hoàn thành / 2. Đã hoàn thành): ");
        String status = sc.nextLine().equals("2") ? "Đã hoàn thành" : "Chưa hoàn thành";

        try (Connection conn = Database.getConnection()) {
            CallableStatement callSt = conn.prepareCall("{call update_task_status(?, ?)}");
            callSt.setInt(1, id);
            callSt.setString(2, status);
            boolean rs = callSt.executeUpdate() > 0;
            System.out.println(rs ? " Cập nhật thành công!" : " Không tìm thấy công việc!");
        } catch (Exception e) {
            System.out.println(" Lỗi khi cập nhật: ");
        }
    }

    public void deleteTask(Scanner sc) {
        int id = Validator.getInt(sc, "Nhập ID công việc cần xóa: ");
        try (Connection conn = Database.getConnection()) {
            CallableStatement callSt = conn.prepareCall("{call delete_task(?)}");
            callSt.setInt(1, id);
            boolean rs = callSt.executeUpdate() > 0;
            System.out.println(rs ? " Xóa thành công!" : " Không tìm thấy công việc!");
        } catch (Exception e) {
            System.out.println(" Lỗi khi xóa công việc: ");
        }
    }

    public void searchTaskByName(Scanner sc) {
        String name = Validator.getString(sc, "Nhập tên công việc cần tìm: ");
        List<Task> tasks = new ArrayList<>();

        try (Connection conn = Database.getConnection()) {
            CallableStatement callSt = conn.prepareCall("{call search_task_by_name(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Task t = new Task();
                t.setId(rs.getInt("id"));
                t.setTaskName(rs.getString("task_name"));
                t.setStatus(rs.getString("status"));
                tasks.add(t);
            }
        } catch (Exception e) {
            System.out.println(" Lỗi khi tìm kiếm: " );
        }

        if (tasks.isEmpty()) {
            System.out.println(" Không tìm thấy công việc nào có tên chứa '" + name + "'");
        } else {
            System.out.println(" Kết quả tìm kiếm:");
            tasks.forEach(Task::display);
        }
    }

    public void taskStatistics() {
        try (Connection conn = Database.getConnection()) {
            CallableStatement callSt = conn.prepareCall("{call task_statistics()}");
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                int done = rs.getInt("done");
                int notDone = rs.getInt("not_done");
                System.out.println("\n THỐNG KÊ CÔNG VIỆC:");
                System.out.println("Đã hoàn thành: " + done);
                System.out.println("Chưa hoàn thành: " + notDone);
            }
        } catch (Exception e) {
            System.out.println(" Lỗi khi thống kê: ");
        }
    }
}