package basic;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {
    @Test
    public void login(){
        //True/False:
        //Bắt buộc phải trả về boolean
        //Selenium: tiền tố isXXXX: isDisplayed/ isEnabled/ isSelected/ isMultiple
        //Hàm tự define về boolean
        Assert.assertTrue(5>3);

        boolean status = 5<3;
        System.out.println(status);
        Assert.assertFalse(status);

        //Equals:
        //2 vế phải cùng kiểu dữ liệu
        //Kiểm tra giá trị của 2 vế
        String student = "Nguyễn Văn A";
        Assert.assertEquals(student,"Nguyễn Văn A");

        Object name = "Nguyễn Văn A";
        Assert.assertEquals(student,name);
    }
}
