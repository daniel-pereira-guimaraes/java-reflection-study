import java.util.Arrays;
import java.util.List;

public class ClassAnalyzer {
    private static final List<String> JDK_PACKAGE_PREFIXES =
                Arrays.asList("com.sun.", "java", "javax", "jdk", "org.w3c", "org.xml");
                
    public static PopupTypeInfo createPopupTypeInfoFromClass(Class<?> inputClass) {
        PopupTypeInfo popupTypeInfo = new PopupTypeInfo();
        
        popupTypeInfo.setPrimitive(inputClass.isPrimitive())
            .setInterface(inputClass.isInterface())
            .setEnum(inputClass.isEnum())
            .setArray(inputClass.isArray())
            .setName(inputClass.getSimpleName())
            .setJdk(isJdkClass(inputClass))
            .addAllInheritedClassNames(getAllInheritedClassNames(inputClass));
        
        return popupTypeInfo;
    }
    
    /*********** Helper Methods ***************/
    
    public static boolean isJdkClass(Class<?> inputClass) {
    	if (inputClass.isPrimitive()) {
    		return true;
    	}
    	if (inputClass.getPackage() != null) {
	    	final String packageName = inputClass.getPackage().getName();
	    	for (String prefix : JDK_PACKAGE_PREFIXES) {
	    		if (packageName.startsWith(prefix)) {
	    			return true;
	    		}
	    	}
    	}
    	return false;
    }
    
    public static String[] getAllInheritedClassNames(Class<?> inputClass) {
    	String[] result = null;
    	if (inputClass.isInterface()) {
    		result = new String[inputClass.getInterfaces().length];
    		for (int i = 0; i < result.length; i++) {
    			result[i] = inputClass.getInterfaces()[i].getSimpleName();
    		}
    	} 
    	else if (inputClass.getSuperclass() != null) {
    		result = new String[1];
    		result[0] = inputClass.getSuperclass().getSimpleName();
    	}
    	return result;
    }
}