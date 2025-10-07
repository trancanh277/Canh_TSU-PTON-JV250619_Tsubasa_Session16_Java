import java.util.Scanner;
public class baitap01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagement studentManagement = new StudentManagement();
        while (true){
            System.out.println("1.Hiển thị danh sách sinh viên.\n" +
                    "2.Thêm mới sinh viên.\n" +
                    "3.Sửa sinh viên.\n" +
                    "4.Xóa sinh viên.\n" +
                    "5.Tìm kiếm sinh viên.\n" +
                    "6.Thoát .\n");
            int choice = Validator.getInt(scanner,"Mời nhập lựa chọn : ");
            switch (choice) {
                case 1:
                    studentManagement.displayListStudent();
                    break;
                case 2:
                    studentManagement.addStudent(scanner);
                    break;
                case 3:
                    studentManagement.updateStudent(scanner);
                    break;
                case 4:
                    studentManagement.deleteStudent(scanner);
                    break;
                case 5 :
                    studentManagement.searchStudentByName(scanner);
                    break;
                case 6 :
                    System.out.println("Good bye !");
                    System.exit(0);
            }

        }
    }
}
