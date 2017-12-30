package vong.piler.her.vongvirtualmachine;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import vong.piler.her.vongruntime.virtualmachine.Steakmachine;

public class OpCodeTests {
	
	Steakmachine vvm = new Steakmachine();
	ByteArrayOutputStream standardOut = new ByteArrayOutputStream();
	ByteArrayOutputStream errorOut = new ByteArrayOutputStream();
	
	
	@Before
	public void init() {
		vvm.setStandardOut(new PrintStream(standardOut));
		vvm.setErrorOut(new PrintStream(errorOut));
		vvm.init();
	}
	
	@Test
	public void testAddWithPositives(){
		loadFile("addWithPositives.vch");
		vvm.run();
		assertEquals("zal: 16.0", standardOut.toString());
	}
	
	@Test
	public void testAddWithNegatives(){
		loadFile("addWithNegatives.vch");
		vvm.run();
		assertEquals("zal: -16.0", standardOut.toString());
	}
	
	@Test
	public void testAddWithNegativeAndPositive(){
		loadFile("addWithNegativeAndPositive.vch");
		vvm.run();
		assertEquals("zal: -2.0", standardOut.toString());
	}
	
	private void loadFile(String name) {
		File file = new File(getClass().getClassLoader().getResource(name).getFile());
		vvm.load(file);
	}

}
