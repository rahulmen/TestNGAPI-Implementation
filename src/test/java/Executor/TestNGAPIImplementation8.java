package Executor;

import org.testng.TestNG;
import org.testng.xml.*;
import scripts.Native.ClassDemoTest;

import java.util.*;

public class TestNGAPIImplementation8 {

	public interface PropertyObjects {
		public String getProperty();
	}

	public enum Theme implements PropertyObjects{
		MERCHANT4("merchant4"),
		ENHANCEDWEB("enhancedweb"),
		ENHANCEDWAP("enhancedwap");

		private Theme(String strPropertyValue){
			this.strProperty = strPropertyValue;
		}

		String strProperty = "";

		public String getProperty(){
			return strProperty;
		}

	}


	public enum Group implements PropertyObjects{
		SMOKE("smoke"),
		SANITY("sanity"),
		REGRESSION("regression");

		private Group(String strPropertyValue){
			this.strProperty = strPropertyValue;
		}

		String strProperty = "";

		public String getProperty(){
			return strProperty;
		}

	}

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
		XmlPackage xmlPackage = new XmlPackage();
		xmlPackage.setName("scripts.Native");
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

	private static Collection<String> getCollecton(String str1){
		Collection<String> collection = new ArrayList<String>();
		String[] s = str1.split("&");
		for(String s1:s){
			collection.add(s1);
		}
		return collection;
	}


	public static void main(String... args)  throws Exception{
		Scanner scan = new Scanner(System.in);
	    System.out.print("Provide the group based theme to be executed: ");
        String group = scan.next();
	    XmlSuite xmlSuite = getSuite();
	    List<XmlTest> totaltest = new ArrayList<XmlTest>();

	    List<String> validTheme = new ArrayList<String>();
		Theme[] themes = Theme.values();
		for(int i=0;i<themes.length;i++){
			validTheme.add(themes[i].getProperty());
		}

		List<String> validGroup=new ArrayList<String>();
		Group[] groups = Group.values();
		for(int i=0;i<groups.length;i++){
			validGroup.add(groups[i].getProperty());
		}

	    StringTokenizer stringTokenizer = new StringTokenizer(group,",");

	    while(stringTokenizer.hasMoreElements()) {
			String token = stringTokenizer.nextToken();
			String[] splittoken = token.split(":");
			XmlTest xmlTest = new XmlTest();
			XmlGroups xmlGroups = new XmlGroups();
			l1:
			for (int str1 = 0; str1 < splittoken.length; str1++) {

				if (validGroup.contains(splittoken[str1])) {
					xmlTest = settingTest(xmlSuite, "Group " + splittoken[str1] + " Implementation" + new Random().nextInt());
					xmlGroups = settingGroup(splittoken[str1]);
					List<XmlPackage> packages = settingpackage();
					xmlTest.setPackages(packages);
					xmlTest.setGroups(xmlGroups);
					continue l1;
				}
				else if (splittoken[str1].contains("&")&&validGroup.containsAll(getCollecton(splittoken[str1]))) {
					xmlTest = settingTest(xmlSuite, "Group " + splittoken[str1] + " Implementation" + new Random().nextInt());
					StringTokenizer stringTokenizer1 = new StringTokenizer(splittoken[str1], "&");
					while (stringTokenizer1.hasMoreTokens()) {
						xmlGroups = settingGroup(stringTokenizer1.nextToken());
					}
					List<XmlPackage> packages = settingpackage();
					xmlTest.setPackages(packages);
					xmlTest.setGroups(xmlGroups);
					continue l1;
				}
				else if(validTheme.contains(splittoken[str1])){
					setTestParameter(xmlTest,splittoken[str1]);
					continue l1;
	            }
	            else{
	                throw new TestNGException("Incorrect Group or Theme Passed");
                }
	            }

			totaltest.add(xmlTest);

            }

        TestNG tng = new TestNG();
        tng.setXmlSuites(linkTestSuites(totaltest,xmlSuite));
        tng.run();

        }

	}



