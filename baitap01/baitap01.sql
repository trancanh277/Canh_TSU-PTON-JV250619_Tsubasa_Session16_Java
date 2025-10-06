create database baitap01;
use baitap01;
create table Student(
    student_id int primary key auto_increment,
    full_name varchar(100) not null ,
    date_of_birth date not null,
    email varchar(100) not null unique
);
delimiter //
create procedure get_all_students(

)
begin
    select *from student;
end;
delimiter //
select * from Student;
call get_all_students();
delimiter //
create procedure add_students(
    in_full_name varchar(100),
    in_date_of_birth date,
    in_email varchar(100)
)
begin
    insert into student(full_name, date_of_birth, email)
        value (in_full_name, in_date_of_birth, in_email);
end//
delimiter //
call  add_students('tam', '1999-10-22', 'trantam@gmail.com');

delimiter //
create procedure update_students(
    in_id int ,
    in_full_name varchar(100),
    in_date_of_birth date,
    in_email varchar(100)
)
begin
    update student
        set full_name = in_full_name,
            date_of_birth = in_date_of_birth,
            email = in_email
    where student_id = in_id;
end //
delimiter //
delimiter //
create procedure find_student_by_id(
    in_id int
)
begin
    select * from student where student_id = in_id;
end //
delimiter //

call find_student_by_id(2);
delimiter //
create procedure delete_student(
    in_id int
)
begin
    delete from student where student_id = in_id;
end //
delimiter //