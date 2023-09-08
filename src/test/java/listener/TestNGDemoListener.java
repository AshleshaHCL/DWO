package listener;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGDemoListener {
	
   @Test (priority = 1)
   @Parameters("myName")
   public void test1(String name) {
		System.out.println("Running Test1 "+name);
	}
	
   @Test  (dataProvider="employee",priority = 2)
	public void test2(String getName) {
	    System.out.println("Running test2");
		System.out.println("Employee name is : "+getName);
	}
   
     @DataProvider(name="employee")
     public Object[][] dpMethod(){
	 return new Object[][] {{"Ashlesha"}, {"Ridhansh"}};
     }
     
   @Test  (dataProvider="data",priority = 3)  
	public void test3(int a,int b) {
		System.out.println("Running Test3");
		Assert.assertTrue(a<b);
	}
   
   @DataProvider(name="data")
   public Object[][] dpMethod2(){
	 return new Object[][] {{5,7}, {8,11}};
   }
   
   @Test  (priority = 4) 
   @Parameters("expData")
  	public void test4(String expData) {
  		System.out.println("Running Test4");
  		Assert.assertEquals("test4", expData);
  	}
   
   @Test (priority = 5)
   @Parameters("expData")
  	public void test5(String expData) {
	   System.out.println("Running Test5");
	   Assert.assertEquals("test5", expData);  		
  	}
   
   @Test (priority = 6)
  	public void test6() {
  		System.out.println("Running Test6");
  		Assert.assertTrue(false);
  	}
   
   @Test  (priority = 7)
   @Parameters("failTest")
  	public void test7(String name) {
  		System.out.println("Running Test7"+name);
  	}
   
   @Test (timeOut = 1000,priority = 8)
  	public void test8() throws InterruptedException {
	   Thread.sleep(2000);
  		System.out.println("Running Test8");
  	}
   
   @Test (priority = 9)
  	public void test9() {
  		System.out.println("Running Test9");
  		throw new SkipException("This test is skipped");
  	}
   
   @Test (dependsOnMethods = "test8",priority = 10)
  	public void test10() {
  		System.out.println("Running Test10");
  	}

}
