package Executor;

import org.testng.TestNG;
import org.testng.xml.*;

import java.util.*;

public class TestNGAPIImplementation9 {

	public interface PropertyObjects {
		public String getProperty();
	}

	public enum Theme implements PropertyObjects {
		MERCHANT4("merchant4"),
		ENHANCEDWEB("enhancedweb"),
		ENHANCEDWAP("enhancedwap");

		private Theme(String strPropertyValue) {
			this.strProperty = strPropertyValue;
		}

		String strProperty = "";

		public String getProperty() {
			return strProperty;
		}

	}


	public enum Group implements PropertyObjects {
		SMOKE("smoke"),
		SANITY("sanity"),
		REGRESSION("regression"),
		PGONLY("pgonly"),
		EXCLUDEALL("excludeall"),
		INCLUDEALL("includeall");

		private Group(String strPropertyValue) {
			this.strProperty = strPropertyValue;
		}

		String strProperty = "";

		public String getProperty() {
			return strProperty;
		}

	}

	private static XmlTest settingTest(XmlSuite xmlSuite, String testName/*,String theme*/) {
		XmlTest xmlTest = new XmlTest(xmlSuite);
		xmlTest.setName(testName);
		xmlTest.setParallel(XmlSuite.ParallelMode.TESTS);
		xmlTest.setThreadCount(3);
		xmlTest.setVerbose(1);
		return xmlTest;
	}

	private static void setTestParameter(XmlTest xmlTest, String theme) {
		xmlTest.addParameter("theme", theme);
	}

	private static XmlGroups settingGroup(XmlGroups xmlGroups, XmlRun xmlRun, String group) {
		xmlRun.onInclude(group);
		xmlGroups.setRun(xmlRun);
		return xmlGroups;
	}

	private static XmlGroups excludeGroup(XmlGroups xmlGroups, XmlRun xmlRun, String group) {
		;
		xmlRun.onExclude(group);
		xmlGroups.setRun(xmlRun);
		return xmlGroups;
	}


	private static List<XmlPackage> settingpackage() {
		XmlPackage xmlPackage = new XmlPackage();

		/*xmlPackage.setName("scripts.TestNG");
		List<String> packages = new ArrayList<String>(Arrays.asList("scripts.TestNG",
				"scripts.subscription"));
		*/

		List<XmlPackage> list1 = new ArrayList<XmlPackage>(Arrays.asList(new XmlPackage("scripts.TestNG"),new XmlPackage("scripts.subscription")));
		return list1;
	}

	private static List<String> getListener() {
		return new ArrayList(Arrays.asList("com.paytm.framework.reporting.listenerDecorators.StatusCounterListener",
				"com.paytm.framework.reporting.listeners.CustomizedEmailableReport",
				"io.qameta.allure.testng.AllureTestNg",
				"com.paytm.framework.reporting.listenerDecorators.ReportingListner",
				"com.paytm.framework.reporting.listenerDecorators.DefaultListener"));
	}


	private static XmlSuite getSuite() {
		XmlSuite xmlSuite = new XmlSuite();
		xmlSuite.setName("TestNG API Implementation");
		xmlSuite.setParallel("false");
		xmlSuite.setVerbose(1);
		xmlSuite.setListeners(getListener());
		return xmlSuite;
	}


	private static List<XmlSuite> linkTestSuites(List<XmlTest> xmlTest, XmlSuite xmlSuite) {
		xmlSuite.setTests(xmlTest);
		List<XmlSuite> list3 = new ArrayList<XmlSuite>();
		list3.add(xmlSuite);
		return list3;
	}


	private static Collection<String> getCollecton(String str1) {
		Collection<String> collection = new ArrayList<String>();
		String[] s = str1.split("&");
		for (String s1 : s) {
			if (s1.contains("!")) {
				String[] s2 = s1.split("!");
				for (String s3 : s2) {
					collection.add(s3);
				}
			} else {
				collection.add(s1);
			}
		}
		return collection;
	}

	public static void main(String... args) throws Exception {
		System.out.print("Provide the group based theme to be executed: ");
		Scanner scan = new Scanner(System.in);
		String group = scan.next();
		XmlSuite xmlSuite = getSuite();
		List<XmlTest> totaltest = new ArrayList<XmlTest>();

		List<String> validTheme = new ArrayList<String>();
		Theme[] themes = Theme.values();
		for (int i = 0; i < themes.length; i++) {
			validTheme.add(themes[i].getProperty());
		}

		List<String> validGroup = new ArrayList<String>();
		Group[] groups = Group.values();
		for (int i = 0; i < groups.length; i++) {
			validGroup.add(groups[i].getProperty());
		}

		StringTokenizer stringTokenizer = new StringTokenizer(group, ",");

		while (stringTokenizer.hasMoreElements())
		{
			String token = stringTokenizer.nextToken();
			String[] splittoken = token.split(":");
			XmlTest xmlTest = new XmlTest();
			XmlGroups xmlGroups = new XmlGroups();
			XmlRun xmlRun = new XmlRun();
			l1:
			for (int str1 = 0; str1 < splittoken.length; str1++)
			{

				if (validGroup.containsAll(getCollecton(splittoken[str1])))
				{

					if (splittoken[str1].contains("&") && !splittoken[str1].contains("!"))
					{
						xmlTest = settingTest(xmlSuite, "Group " + splittoken[str1] + " Implementation" + new Random().nextInt());
						StringTokenizer stringTokenizer1 = new StringTokenizer(splittoken[str1], "&");
						while (stringTokenizer1.hasMoreTokens())
						{
							xmlGroups = settingGroup(xmlGroups, xmlRun, stringTokenizer1.nextToken());
							xmlTest.setGroups(xmlGroups);
						}
						List<XmlPackage> packages = settingpackage();
						xmlTest.setPackages(packages);
						xmlTest.setGroups(xmlGroups);
						continue l1;
					}
					else if (splittoken[str1].contains("&") && splittoken[str1].contains("!"))
					{
						xmlTest = settingTest(xmlSuite, "Group " + splittoken[str1] + " Implementation" + new Random().nextInt());
						StringTokenizer stringTokenizer1 = new StringTokenizer(splittoken[str1], "!");
						while (stringTokenizer1.hasMoreTokens())
						{
							String nextToken = stringTokenizer1.nextToken();
							if (nextToken.contains("&"))
							{
								StringTokenizer stringTokenizer2 = new StringTokenizer(nextToken, "&");
								while (stringTokenizer2.hasMoreElements())
								{
									xmlGroups = settingGroup(xmlGroups, xmlRun, stringTokenizer2.nextToken());
									xmlTest.setGroups(xmlGroups);
								}
							}
							else
							{
								if (nextToken.equals("excludeall"))
								{
									l3:
									for (int i = 0; i < groups.length; i++)
									{
										if(xmlTest.getIncludedGroups().contains(groups[i].getProperty()))
										{
											continue l3;
										}
										else
										{
										xmlGroups = excludeGroup(xmlGroups, xmlRun, validGroup.get(i));
										xmlTest.setGroups(xmlGroups);
										}
									}
								}
								else
								{
									xmlGroups = excludeGroup(xmlGroups, xmlRun, nextToken);
									xmlTest.setGroups(xmlGroups);
								}
							}
						}
						List<XmlPackage> packages = settingpackage();
						xmlTest.setPackages(packages);
						continue l1;
					}
					else if (!splittoken[str1].contains("&") && !splittoken[str1].contains("!"))
					{
						xmlTest = settingTest(xmlSuite, "Group " + splittoken[str1] + " Implementation" + new Random().nextInt());
						if(splittoken[str1].equals("includeall"))
						{
							for(int i=0;i<groups.length;i++)
							{
								xmlGroups = settingGroup(xmlGroups, xmlRun, groups[i].getProperty());
								xmlTest.setGroups(xmlGroups);
							}
						}
						else
						{
							xmlGroups = settingGroup(xmlGroups, xmlRun, splittoken[str1]);
							xmlTest.setGroups(xmlGroups);
						}
						List<XmlPackage> packages = settingpackage();
						xmlTest.setPackages(packages);
						xmlTest.setGroups(xmlGroups);
						continue l1;

					}
					else if(!splittoken[str1].contains("&") && splittoken[str1].contains("!"))
					{
						xmlTest = settingTest(xmlSuite, "Group " + splittoken[str1] + " Implementation" + new Random().nextInt());
						String[] strings = splittoken[str1].split("!");
						for(int i=0;i<strings.length;i++)
						{
							if(i==0)
							{
								xmlGroups = settingGroup(xmlGroups, xmlRun, strings[0]);
								xmlTest.setGroups(xmlGroups);
							}
							else if(i==0&&strings[i].equals("excludeall"))
							{
								throw new TestNGException("All testCase are excluded");
							}
							else
							{
								if (strings[i].equals("excludeall"))
								{
									l3:
									for (int i2 = 0; i2 < groups.length; i2++)
									{
										if(xmlTest.getIncludedGroups().contains(groups[i2].getProperty()))
										{
											continue l3;
										}
										else
										{
											xmlGroups = excludeGroup(xmlGroups, xmlRun, validGroup.get(i2));
											xmlTest.setGroups(xmlGroups);
										}
									}
								}
								else
								{
									xmlGroups = excludeGroup(xmlGroups, xmlRun, strings[i]);
									xmlTest.setGroups(xmlGroups);
								}
							}
						}
						List<XmlPackage> packages = settingpackage();
						xmlTest.setPackages(packages);
						xmlTest.setGroups(xmlGroups);
						continue l1;
					}
				}
				else if (validTheme.contains(splittoken[str1]))
				{
					if (str1 == 0)
					{
						throw new TestNGException("No Group Specified to trigger");
					}
					setTestParameter(xmlTest, splittoken[str1]);
					continue l1;
				}
				else
				{
					throw new TestNGException("Incorrect Group or Theme Passed");
				}
			}
			totaltest.add(xmlTest);
		}

		TestNG tng = new TestNG();
		tng.setXmlSuites(linkTestSuites(totaltest, xmlSuite));
		tng.run();

	}
}


