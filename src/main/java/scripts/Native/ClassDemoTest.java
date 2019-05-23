package scripts.Native;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ClassDemoTest {

	@Parameters({"theme"})
	@Test(groups={"sanity"})
	public void method1(@Optional("default") String theme){
		System.out.println("Theme Passed sanity:"  + theme);
	}

	@Parameters({"theme"})
	@Test(groups={"smoke"})
	public void method2(@Optional("default") String theme){
		System.out.println("Theme Passed smoke:"  + theme);
	}

	@Parameters({"theme"})
	@Test
	public void method3(@Optional("default") String theme){
		System.out.println("Theme Passed Without Group:"  + theme);
	}

	@Parameters("theme")
	@Test(groups={"sanity","smoke"})
	public void method4(@Optional("default") String theme){
		System.out.println("Theme Passed sanity and smoke :"  + theme);
	}

	@Parameters({"theme"})
	@Test(groups={"smoke","sanity","regression"})
	public void method5(@Optional("default") String theme)
	{
		System.out.println("Theme Passed smoke,sanity and regression:"  + theme);
	}

	@Parameters({"theme"})
	@Test(groups={"sanity","regression"})
	public void method6(@Optional("default") String theme)
	{
		System.out.println("Theme Passed sanity and regression:"  + theme);
	}


	@Parameters({"theme"})
	@Test(groups={"smoke","regression"})
	public void method7(@Optional("default") String theme)
	{
		System.out.println("Theme Passed smoke and regression:"  + theme);
	}

}


