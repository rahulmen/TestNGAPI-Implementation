package Executor;

import org.testng.TestNG;
import org.testng.xml.*;
import scripts.Native.ClassDemoTest;

import java.util.ArrayList;
import java.util.List;

public class TestNGAPIImplementation4 {

    private TestNGAPIImplementation4(){
        System.out.print("Reflection Class");
    }

	private static XmlTest settingTest(XmlSuite xmlSuite,String testName){
		XmlTest xmlTest = new XmlTest(xmlSuite);
		xmlTest.setName(testName);
		xmlTest.setParallel(XmlSuite.ParallelMode.TESTS);
		xmlTest.setThreadCount(3);
		xmlTest.addParameter("theme","enhancedWeb");
		return xmlTest;
	}

	private static XmlGroups settingGroup(){
		XmlGroups xmlGroups = new XmlGroups();
		XmlRun xmlRun = new XmlRun();
		xmlRun.onInclude("Sanity");
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
		xmlSuite.setName("TestNG API Implementation");
		xmlSuite.setParallel("false");
		xmlSuite.setVerbose(1);
		return xmlSuite;
	}

	private static List<XmlSuite> linkTestSuite(XmlTest xmlTest,XmlSuite xmlSuite){
		xmlSuite.setName("TestNG API Implementation");
		xmlSuite.setParallel("false");
		xmlSuite.setVerbose(1);
		List<XmlTest> list2 = new ArrayList<XmlTest>();
		list2.add(xmlTest);
		xmlSuite.setTests(list2);
		List<XmlSuite> list3 = new ArrayList<XmlSuite>();
		list3.add(xmlSuite);
		return list3;
	}


	public static void main(String... args)  throws Exception{
		XmlSuite xmlSuite = new XmlSuite();
		XmlTest xmlTest = settingTest(xmlSuite,"Group Smoke Implementation");
		XmlGroups xmlGroups = settingGroup();
		List<XmlClass> classes = settingClass();
		xmlTest.setClasses(classes);
		xmlTest.setGroups(xmlGroups);

		TestNG tng = new TestNG();
		tng.setXmlSuites(linkTestSuite(xmlTest,xmlSuite));
		tng.run();

	}

	}


