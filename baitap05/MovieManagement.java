package baitap05;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieManagement {

    public void displayMovies() {
        List<Movie> movies = new ArrayList<>();
        try (Connection conn = Database.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{call list_movies()}");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setMovieId(rs.getInt("movie_id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setReleaseYear(rs.getInt("release_year"));
                movies.add(movie);
            }
        } catch (Exception e) {
            System.out.println(" Lấy danh sách phim thất bại!");
        }

        if (movies.isEmpty()) {
            System.out.println("Danh sách trống!");
        } else {
            System.out.println("\n DANH SÁCH PHIM:");
            movies.forEach(Movie::display);
        }
    }

    public void addMovie(Scanner sc) {
        String title = Validator.getString(sc, "Nhập tên phim: ");
        String director = Validator.getString(sc, "Nhập tên đạo diễn: ");
        int year = Validator.getInt(sc, "Nhập năm phát hành: ");

        try (Connection conn = Database.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{call add_movie(?, ?, ?)}");
            stmt.setString(1, title);
            stmt.setString(2, director);
            stmt.setInt(3, year);

            boolean success = stmt.executeUpdate() > 0;
            System.out.println(success ? " Thêm phim thành công!" : "⚠ Không thể thêm phim.");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(" Phim này đã tồn tại!");
        } catch (Exception e) {
            System.out.println(" Lỗi khi thêm phim: " );
        }
    }

    public void updateMovie(Scanner sc) {
        int id = Validator.getInt(sc, "Nhập ID phim cần sửa: ");
        String title = Validator.getString(sc, "Tên phim mới: ");
        String director = Validator.getString(sc, "Đạo diễn mới: ");
        int year = Validator.getInt(sc, "Năm phát hành mới: ");

        try (Connection conn = Database.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{call update_movie(?, ?, ?, ?)}");
            stmt.setInt(1, id);
            stmt.setString(2, title);
            stmt.setString(3, director);
            stmt.setInt(4, year);
            boolean success = stmt.executeUpdate() > 0;
            System.out.println(success ? " Cập nhật thành công!" : " Không tìm thấy phim!");
        } catch (Exception e) {
            System.out.println(" Lỗi khi cập nhật: ");
        }
    }

    public void deleteMovie(Scanner sc) {
        int id = Validator.getInt(sc, "Nhập ID phim cần xóa: ");
        try (Connection conn = Database.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{call delete_movie(?)}");
            stmt.setInt(1, id);
            boolean success = stmt.executeUpdate() > 0;
            System.out.println(success ? " Xóa thành công!" : "️ Không tìm thấy phim!");
        } catch (Exception e) {
            System.out.println(" Lỗi khi xóa phim: " );
        }
    }
}