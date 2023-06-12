package testing;

public class Main {

	public static void main(String[] args) throws Throwable {
		
		TestingFramework testingFramework = new TestingFramework();
		testingFramework.runTestSuite(testProduct.class);
		
	}
}
