package cacheproxy;

import java.io.IOException;
import java.lang.reflect.Proxy;

public class CacheProxyTest {
	
	public static void main(String[] args) throws IOException {
		
		final DatabaseReader reader = createProxy(new DatabaseReaderImpl());
		
		for (int i = 0; i < 5; i++) {
			reader.connectToDatabase();
			reader.readCustomerIdByName("Emma", "Smith");
			reader.countRowsInCustomersTable();
			reader.addCustomer("1", "Paul", "Smith");
			reader.readCustomerBirthday("1");
			System.out.println("---");
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T createProxy(Object originalObject) {
		final Class<?>[] interfaces = originalObject.getClass().getInterfaces();
		CachingInvocationHandler cachingInvocationHandler = new CachingInvocationHandler(originalObject);
		return (T) Proxy.newProxyInstance(originalObject.getClass().getClassLoader(), interfaces, cachingInvocationHandler);
	}

}
