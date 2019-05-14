package scripts;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ClassDemoTest {

	@Parameters({"theme"})
	@Test(groups={"Sanity"})
	public void method1(@Optional("default") String theme){
		System.out.println("Theme Passed Sanity:"  + theme);
	}

	@Parameters({"theme"})
	@Test(groups={"Smoke"})
	public void method2(@Optional("default") String theme){
		System.out.println("Theme Passed Smoke:"  + theme);
	}

	@Parameters({"theme"})
	@Test
	public void method3(@Optional("default") String theme){
		System.out.println("Theme Passed Without Group:"  + theme);
	}

	@Parameters("theme")
	@Test(groups={"Sanity","Smoke"})
	public void method4(@Optional("default") String theme){
		System.out.println("Theme Passed Sanity and Smoke :"  + theme);
	}

	@Parameters({"theme"})
	@Test(groups={"Smoke","Sanity","Regression"})
	public void method5(@Optional("default") String theme)
	{
		System.out.println("Theme Passed Smoke,Sanity and Regression:"  + theme);
	}

	@Parameters({"theme"})
	@Test(groups={"Sanity","Regression"})
	public void method6(@Optional("default") String theme)
	{
		System.out.println("Theme Passed Sanity and Regression:"  + theme);
	}


	@Parameters({"theme"})
	@Test(groups={"Smoke","Regression"})
	public void method7(@Optional("default") String theme)
	{
		System.out.println("Theme Passed Smoke and Regression:"  + theme);
	}

}


