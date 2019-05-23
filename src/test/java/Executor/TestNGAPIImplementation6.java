package Executor;

import org.testng.TestNG;
import org.testng.xml.*;
import scripts.Native.ClassDemoTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TestNGAPIImplementation6 {

	private static XmlTest settingTest(XmlSuite xmlSuite,String testName/*,String theme*/){
		XmlTest xmlTest = new XmlTest(xmlSuite);
		xmlTest.setName(testName);
		xmlTest.setParallel(XmlSuite.ParallelMode.TESTS);
		xmlTest.setThreadCount(3);
		//xmlTest.addParameter("theme",theme);
		return xmlTest;
	}

	private static void setTestParameter(XmlTest xmlTest,String theme){
        xmlTest.addParameter("theme",theme);
    }

	private static XmlGroups settingGroup(String group){
		XmlGroups xmlGroups = new XmlGroups();
		XmlRun xmlRun = new XmlRun();
		xmlRun.onInclude(group);
		xmlGroups.setRun(xmlRun);
		return xmlGroups;
	}

	private static List<XmlClass> settingClass(){
		XmlClass xmlClass = new XmlClass(ClassDemoTest.class);
		List<XmlClass> list1 = new ArrayList<XmlClass>();
		list1.add(xmlClass);
		return list1;
	}

	private static XmlSuite getSuite(){
		XmlSuite xmlSuite = new XmlSuite();
		xmlSuite.setName("Groups Based Configuration");
		xmlSuite.setParallel("false");
		xmlSuite.setVerbose(1);
		return xmlSuite;
	}

	private static List<XmlSuite> linkTestSuite(XmlTest xmlTest,XmlSuite xmlSuite){
		List<XmlTest> list2 = new ArrayList<XmlTest>();
		list2.add(xmlTest);
		xmlSuite.setTests(list2);
		List<XmlSuite> list3 = new ArrayList<XmlSuite>();
		list3.add(xmlSuite);
		return list3;
	}


	public static void main(String... args)  throws Exception{

	    Scanner scan = new Scanner(System.in);
	    System.out.print("Provide the group based theme to be executed: ");
        String group = scan.next();
	    XmlSuite xmlSuite = getSuite();
        XmlGroups xmlGroups=null;
        XmlClass xmlClass=null;
	    //String str = new String("smoke:merchant4&sanity:enhancedweb");
	    StringTokenizer stringTokenizer = new StringTokenizer(group,"&");

	    while(stringTokenizer.hasMoreTokens()){
			XmlTest xmlTest=null;
	        String token = stringTokenizer.nextToken();
	        String[] splittoken = token.split(":");

	        for(int str1=0;str1<splittoken.length;str1++){
                if(!splittoken[str1].equals("merchant4")||splittoken[str1].equals("enhancedweb")||splittoken[str1].equals("enhancedwap")) {
                    xmlTest = settingTest(xmlSuite, splittoken[str1] + " Test");
                    xmlGroups = settingGroup(splittoken[str1]);
                    List<XmlClass> xmlClasses= settingClass();
                    xmlTest.setClasses(xmlClasses);
					xmlTest.setGroups(xmlGroups);

                }
	            else if(splittoken[str1].equals("merchant4")||splittoken[str1].equals("enhancedweb")||splittoken[str1].equals("enhancedwap")){
	            setTestParameter(xmlTest,splittoken[str1]);
	            List<XmlSuite> xmlSuites = linkTestSuite(xmlTest,xmlSuite);
                }
	            else{
	                System.err.print("Invalid Group and Theme Passes");
                }
	            }

            }

        TestNG tng = new TestNG();
        //tng.setXmlSuites(linkTestSuite(xmlTest,xmlSuite));
        tng.run();

        }

/*
		Constructor<TestNGAPIImplementation4> constructor = TestNGAPIImplementation4.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		TestNGAPIImplementation4 testNGAPIImplementation4=constructor.newInstance();
*/

	}



