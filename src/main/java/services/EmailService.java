package services;

// Visible for only this package!
class EmailService {
	
	public void sendMessage(String from, String to, String subject, String body) {
		System.out.println(getClass().getName());
		System.out.println("\tFrom: " + from);
		System.out.println("\tTo: " + to);
		System.out.println("\tSubject: " + subject);
		System.out.println("\tBody: " + body);
	}

}
