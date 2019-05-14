package Executor;

import org.testng.TestNG;
import org.testng.xml.*;
import scripts.ClassDemoTest;

import java.util.ArrayList;
import java.util.List;

public class TestNGAPIImplementation3 {

	//private static XmlTest xmlTest;
	private static XmlSuite xmlSuite;
	private static XmlPackage xmlPackage;
	private static XmlGroups xmlGroups;
	private static XmlRun xmlRun;
	private static XmlClass xmlClass;

	public static void main(String[] args) {
//Suite will be common for all Test inside it
		xmlSuite = new XmlSuite();
		xmlSuite.setName("TestNG API Implementation");
		xmlSuite.setParallel("false");
		xmlSuite.setVerbose(1);

/////////////////////////////////////////////

//Test1 defined
		XmlTest xmlTest1 = new XmlTest(xmlSuite);
		xmlTest1.setName("Group Smoke Implementation");
		xmlTest1.setParallel(XmlSuite.ParallelMode.METHODS);
		xmlTest1.setThreadCount(3);
		xmlTest1.addParameter("theme","enhancedWep");

//Groups defined inside Test1

		XmlGroups xmlGroups1 = new XmlGroups();
		XmlRun xmlRun1 = new XmlRun();
		xmlRun1.onInclude("Sanity");
		xmlGroups1.setRun(xmlRun1);
		xmlTest1.setGroups(xmlGroups1);

//Test2 Defined

		XmlTest xmlTest2 = new XmlTest(xmlSuite);
		xmlTest2.setName("Group Sanity Implementation");
		xmlTest2.setParallel(XmlSuite.ParallelMode.METHODS);
		xmlTest2.setThreadCount(3);
		xmlTest2.addParameter("theme","merchant4");

//Group defined inside Test2

		XmlGroups xmlGroups2 = new XmlGroups();
		XmlRun xmlRun2 = new XmlRun();
		xmlRun2.onInclude("Smoke");
		xmlGroups2.setRun(xmlRun2);
		xmlTest2.setGroups(xmlGroups2);

///////////////////////////////////////////////////////////////


//Classes added inside Both test


		XmlClass xmlClass = new XmlClass(ClassDemoTest.class);
		List<XmlClass> list1 = new ArrayList<XmlClass>();
		list1.add(xmlClass);
		xmlTest1.setClasses(list1);
		xmlTest2.setClasses(list1);

//Common for both Test Suite anf Test Connection
		List<XmlTest> list2 = new ArrayList<XmlTest>();
		list2.add(xmlTest1);
		list2.add(xmlTest2);
		xmlSuite.setTests(list2);

		List<XmlSuite> list3 = new ArrayList<XmlSuite>();
		list3.add(xmlSuite);

		TestNG tng = new TestNG();
		//tng.setTestClasses(new Class[] { TestNGAPIImplementation.class});
//TestNG Runner and Suite Configuration
		tng.setXmlSuites(list3);
		tng.run();

	}

	}


