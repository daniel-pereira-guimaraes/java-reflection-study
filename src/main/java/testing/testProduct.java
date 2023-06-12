package testing;

public class testProduct {
	
	public void beforeClass() {
		System.out.println("beforeClass");
	}

	public void afterClass() {
		System.out.println("\nafterClass");
	}
	
	public void setupTest() {
		System.out.println("\nsetupTest");
	}
	
	private void test() {
		System.out.println("test");
	}
	
	private void testOne() {
		System.out.println("testOne");
	}
	
	private void testTwo() {
		System.out.println("testTwo");
	}
	
	public void otherMethod() {
		System.out.println("otherMethod");
	}
}
