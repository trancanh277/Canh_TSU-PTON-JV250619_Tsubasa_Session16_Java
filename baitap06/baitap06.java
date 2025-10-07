package baitap06;

import java.util.Scanner;

public class baitap06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManagement manager = new TaskManagement();

        while (true) {
            System.out.println("\n===== QUẢN LÝ TO-DO LIST =====");
            System.out.println("1. Hiển thị danh sách công việc");
            System.out.println("2. Thêm công việc");
            System.out.println("3. Cập nhật trạng thái");
            System.out.println("4. Xóa công việc");
            System.out.println("5. Tìm kiếm công việc");
            System.out.println("6. Thống kê công việc");
            System.out.println("7. Thoát");

            int choice = Validator.getInt(sc, " Nhập lựa chọn: ");
            switch (choice) {
                case 1:
                    manager.listTasks();
                    break;
                case 2:
                    manager.addTask(sc);
                    break;
                case 3:
                    manager.updateTaskStatus(sc);
                    break;
                case 4:
                    manager.deleteTask(sc);
                    break;
                case 5:
                    manager.searchTaskByName(sc);
                case 6:
                    manager.taskStatistics();
                    break;
                case 7:
                {
                    System.out.println(" Tạm biệt!");
                    System.exit(0);
                }
                default:
                    System.out.println(" Lựa chọn không hợp lệ!");
            }
        }
    }
}
