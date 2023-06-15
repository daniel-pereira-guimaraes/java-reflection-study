package annotations;

public class Loaders {

	@Loader
	public void methodOne() {
		System.out.println("methodOne running...");
	}
	
	public void methodTwo() {
		System.out.println("methodTwo running...");
	}
	
	@Loader
	public void methodThree() {
		System.out.println("methodThree running...");
	}
	
}
