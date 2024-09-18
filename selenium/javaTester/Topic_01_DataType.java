package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Topic_01_DataType {
    // Bài toán quản ly 1 công ty về IT
    // Thông tin về công ty: Tên/ Địa chỉ/ MST
    // Có bao nhiêu phòng ban
    // Có bao nhiêu nhân viên
    // Thông tin về nhân viên: Tên/ Tuổi/ Địa chỉ/ Giới tính/ Mã số thuế/ ...
    // Giờ giấc làm việc
    // Lương/ Thưởng
    String companyName;
    String address;
    String tax;
    int department;
    int employee;
    String empName;
    String empInfo;
    float workingHours;
    float salary;

    // Quản lý trường học
    // Điểm số học sinh/ sinh viên
    String schoolName;
    float score;


    //Test Exam
    // Yes/ No question
    boolean answer;

    //Feedback
    //Good/ Bad
    boolean feedback;

    //Tên người có dùng số để đặt tên?
    // Quản lí trường học/ khách sạn/ kho bãi
    // Công ty/ Quán ăn/ Bệnh viện/...



    // 1 kiểu dữ liệu sẽ được sử dụng cho 1 thông tin/ thuộc tính nào đó
    // Mỗi ngôn ngữ lập trình sẽ có quy ước các kiểu dữ liệu khác nhau


    // Java có 2 nhóm kiểu dữ liệu
    // 1 - Kiểu dữ liệu nguyên thuỷ (Primitive Type)
    // 8 kiểu đại diện (chia ra gồm 4 nhóm)
    // Kiểu kí tự (đại diện cho 1 kí tự)
    char a = 'A';

    // Kiểu logic (boolean)
    boolean answerQuestion = true;

    //Kiểu số nguyên (byte, short, int, long)
    byte bNum = 1;
    short sNum = 32767;
    int iNum = 2147483647;
    long lNum = 999999999;

    //Kiểu số thực (float, double)
    float fNum = 26.8f;
    double dNum = 19.9f;



    // 2 - Kiểu dữ liệu tham chiếu (Reference Type)
    // String, Arrays, Objects, Classes, Interfaces
    // Kiểu mảng
    // Mảng kiểu string
    String[] studentName = {"Nguyễn Văn A","Phan Hoàng B","Lê Thành C"};
    int[] studentAge = {25, 26, 28};

    // Kiểu Object (đại diện cho các kiểu dữ liệu khác)
    // Đối tượng => chuyển đổi qua các kiểu dữ liệu khác
    Object studentAdd = "123 PO Box";
    Object empAge = 29;
    Object empSex = false;

    // Kiểu chuỗi (string)
    String name = "Bình";
    String empNum = "090909090909";

    // Class
    FirefoxDriver ffDriver = new FirefoxDriver(); // public class

    // Interface
    WebDriver driver = new ChromeDriver(); // public interface WebDriver


    // Collection
    List<WebElement> textboxes = driver.findElements(By.cssSelector(""));
    ArrayList<String> studentCity = new ArrayList<>();





}
