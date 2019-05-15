package scripts;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ClassDemoTest {

	@Parameters({"theme"})
	@Test(groups={"SANITY"})
	public void method1(@Optional("default") String theme){
		System.out.println("Theme Passed SANITY:"  + theme);
	}

	@Parameters({"theme"})
	@Test(groups={"SMOKE"})
	public void method2(@Optional("default") String theme){
		System.out.println("Theme Passed SMOKE:"  + theme);
	}

	@Parameters({"theme"})
	@Test
	public void method3(@Optional("default") String theme){
		System.out.println("Theme Passed Without Group:"  + theme);
	}

	@Parameters("theme")
	@Test(groups={"SANITY","SMOKE"})
	public void method4(@Optional("default") String theme){
		System.out.println("Theme Passed SANITY and SMOKE :"  + theme);
	}

	@Parameters({"theme"})
	@Test(groups={"SMOKE","SANITY","REGRESSION"})
	public void method5(@Optional("default") String theme)
	{
		System.out.println("Theme Passed SMOKE,SANITY and REGRESSION:"  + theme);
	}

	@Parameters({"theme"})
	@Test(groups={"SANITY","REGRESSION"})
	public void method6(@Optional("default") String theme)
	{
		System.out.println("Theme Passed SANITY and REGRESSION:"  + theme);
	}


	@Parameters({"theme"})
	@Test(groups={"SMOKE","REGRESSION"})
	public void method7(@Optional("default") String theme)
	{
		System.out.println("Theme Passed SMOKE and REGRESSION:"  + theme);
	}

}


