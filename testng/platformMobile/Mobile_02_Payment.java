package platformMobile;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Mobile_02_Payment {
    @BeforeClass
    public void beforeClass(){
        System.out.println("Before Class");
    }

    @Test(groups = {"platformMobile"})
    public void Payment_01_Cheque(){
        System.out.println("Payment by Cheque");
    }
    @Test(groups = {"platformMobile"})
    public void Payment_02_Cash(){
        System.out.println("Payment by Cash");
    }
    @Test(groups = {"platformMobile"})
    public void Payment_03_Card(){
        System.out.println("Payment by Card");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("After Class");
    }
}
