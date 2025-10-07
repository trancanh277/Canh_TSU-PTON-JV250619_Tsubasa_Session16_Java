package baitap05;

import java.util.Scanner;

public class baitap05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieManagement movieManagement = new MovieManagement();

        while (true) {
            System.out.println("\n===== MENU QUẢN LÝ PHIM =====");
            System.out.println("1. Hiển thị danh sách phim");
            System.out.println("2. Thêm phim");
            System.out.println("3. Sửa phim");
            System.out.println("4. Xóa phim");
            System.out.println("5. Thoát");

            int choice = Validator.getInt(sc, " Nhập lựa chọn: ");
            switch (choice) {
                case 1:
                    movieManagement.displayMovies();
                    break;
                case 2:
                    movieManagement.addMovie(sc);
                    break;
                case 3:
                    movieManagement.updateMovie(sc);
                    break;
                case 4:
                    movieManagement.deleteMovie(sc);
                    break;
                case 5:
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
