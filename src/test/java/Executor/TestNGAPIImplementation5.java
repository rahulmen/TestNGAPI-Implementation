package Executor;

import org.testng.xml.*;
import scripts.Native.ClassDemoTest;

import java.util.ArrayList;
import java.util.List;

public class TestNGAPIImplementation5 {

	private static XmlTest settingTest(XmlSuite xmlSuite,String testName){
		XmlTest xmlTest = new XmlTest(xmlSuite);
		xmlTest.setName(testName);
		xmlTest.setParallel(XmlSuite.ParallelMode.TESTS);
		xmlTest.setThreadCount(3);
		xmlTest.addParameter("theme","enhancedWep");
		return xmlTest;
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


	public static void main(String... args) throws Exception{
		String str = new String("smoke merchant4 : sanity enhancedWeb");
		String[] str1 = str.split(" ");
		XmlSuite xmlSuite = new XmlSuite();
		XmlTest xmlTest=null;

		int start=0;
		int end=0;

		l1:
		for(int i=start;i<str1.length;i++){

			//Condition to check if first enter value is not :
			if(str1[i].equals(":") && i==0){
				{//Need to Put Exception Handing
				System.err.print("No Group Selected.Please check the command passed");
				break l1;
				}
			}
			l2:
			for(int i1=i;i1<str1.length;i1++){
				if(str1[i1].equals(":")){
					continue l2;
				}
				else{
					System.out.print(str1[i1]);
					}
				}

			}

/*
			if(!str1[i].equals(":")){
				end++;
			}else{
				start=i+1;
				continue;
			}
*/

		}



/*
		XmlGroups xmlGroups = settingGroup();
		List<XmlClass> classes = settingClass();
		xmlTest.setClasses(classes);
		xmlTest.setGroups(xmlGroups);

		TestNG tng = new TestNG();
		tng.setXmlSuites(linkTestSuite(xmlTest,xmlSuite));
		tng.run();
*/



	}




