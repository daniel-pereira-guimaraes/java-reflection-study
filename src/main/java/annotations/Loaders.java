package annotations;

public class Loaders {

	@Loader
	public void methodOne() {
		System.out.println("\tmethodOne running...");
	}
	
	public void methodTwo() {
		System.out.println("\tmethodTwo running...");
	}
	
	@Loader
	public void methodThree() {
		System.out.println("\tmethodThree running...");
	}
	
}
