package Executor;

import org.testng.TestNG;
import org.testng.xml.*;
import scripts.Native.ClassDemoTest;

import java.util.*;

public class TestNGAPIImplementation7 {

	private static XmlTest settingTest(XmlSuite xmlSuite,String testName/*,String theme*/){
		XmlTest xmlTest = new XmlTest(xmlSuite);
		xmlTest.setName(testName);
		xmlTest.setParallel(XmlSuite.ParallelMode.TESTS);
		xmlTest.setThreadCount(3);
		xmlTest.setVerbose(1);
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


	private static List<XmlPackage> settingpackage(){
		XmlPackage xmlPackage = new XmlPackage("*");
		List<XmlPackage> list1 = new ArrayList<XmlPackage>();
		list1.add(xmlPackage);
		return list1;
	}


	private static XmlSuite getSuite(){
		XmlSuite xmlSuite = new XmlSuite();
		xmlSuite.setName("TestNG API Implementation");
		xmlSuite.setParallel("false");
		xmlSuite.setVerbose(1);
		return xmlSuite;
	}


	private static List<XmlSuite> linkTestSuites(List<XmlTest> xmlTest,XmlSuite xmlSuite){
		xmlSuite.setTests(xmlTest);
		List<XmlSuite> list3 = new ArrayList<XmlSuite>();
		list3.add(xmlSuite);
		return list3;
	}


	public static void main(String... args){

	    Scanner scan = new Scanner(System.in);
	    System.out.print("Provide the group based theme to be executed: ");
        String group = scan.next();
	    XmlSuite xmlSuite = getSuite();
	    List<XmlTest> totaltest = new ArrayList<XmlTest>();

	    //String str = new String("smoke:merchant4&sanity:enhancedweb");
	    StringTokenizer stringTokenizer = new StringTokenizer(group,"&");


	    while(stringTokenizer.hasMoreTokens()){
	        String token = stringTokenizer.nextToken();
	        String[] splittoken = token.split(":");
			XmlTest xmlTest = new XmlTest();
			XmlGroups xmlGroups = new XmlGroups();

			for(int str1=0;str1<splittoken.length;str1++){

                if(!(splittoken[str1].equalsIgnoreCase("merchant4")||splittoken[str1].equalsIgnoreCase("enhancedWeb")||
						splittoken[str1].equalsIgnoreCase("enhancedWap"))) {
					xmlTest = settingTest(xmlSuite,"Group "+splittoken[str1]+" Implementation");
                	if(splittoken[str1].contains(",")){
                		StringTokenizer stringTokenizer1 = new StringTokenizer(splittoken[str1].toUpperCase(),",");
                		while(stringTokenizer1.hasMoreTokens()){
							xmlGroups = settingGroup(stringTokenizer1.nextToken());
						}
					}
                	else{
						xmlGroups = settingGroup(splittoken[str1].toUpperCase());
					}
					List<XmlClass> classes = settingClass();
					xmlTest.setClasses(classes);
					xmlTest.setGroups(xmlGroups);
                }

	            else if(splittoken[str1].equalsIgnoreCase("merchant4")||splittoken[str1].equalsIgnoreCase("enhancedWeb")
						||splittoken[str1].equalsIgnoreCase("enhancedWap")){
					setTestParameter(xmlTest,splittoken[str1]);
	            }

	            else{
	                System.err.print("Invalid Theme passed");
                }

	            }

			totaltest.add(xmlTest);

            }

        TestNG tng = new TestNG();
        tng.setXmlSuites(linkTestSuites(totaltest,xmlSuite));
        tng.run();

        }

	}



