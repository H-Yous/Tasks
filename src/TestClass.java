import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestClass {

	Family fam = new Family();
	
	@Test
	public void test1() {
		assertTrue(fam.setParentsOf("Frank", "Morgan"));
		assertTrue(fam.setParentsOf("Frank", "Dylan"));
		assertTrue(fam.Male("Dylan"));
		assertTrue(fam.setParentsOf("Joy", "Frank"));
		assertTrue(fam.Male("Frank"));
		assertFalse(fam.Male("Morgan"));
		assertTrue(fam.setParentsOf("July", "Morgan"));
		assertFalse(fam.isMale("Joy"));
		assertFalse(fam.isFemale("Joy"));
		
		String[] names = {"Frank", "July"} ;
		//assertEquals(fam.getChildrenOf("Morgan"), names );
		
		assertTrue(fam.setParentsOf("Jennifer", "Morgan"));
		
		
		String[] names2 =  { "Frank" , "Jennifer", "July" }; 
		//assertEquals(fam.getChildrenOf("Morgan"), names2);
		//assertEquals(fam.getChildrenOf("Dylan"), "Frank");
		
		String[] names3 = { "Dylan", "Morgan" } ;
		//assertEquals(fam.getParentsOf("Frank"), names3);
		
		assertFalse(fam.setParentsOf("Morgan", "Frank"));
	}
	
	
	@Test
	public void test2() {
		assertTrue(fam.setParentsOf("Mark",  "Linda"));
		
	}
//	
//	@Test
//	public void test2() {
//	
//	}
//	
//	@Test
//	public void test3() {
//		
//		
//	}
//	
//	@Test
//	public void test4() {
//		
//		
//	}
//	
//	@Test
//	public void test5() {
//		
//		
//	}
//	
//	@Test
//	public void test6() {
//	
//				
//	}
//	
//	@Test
//	public void test7() {
//		
//		
//	}
//	
//	@Test
//	public void test8() {
//		
//		
//	}
//	
//	@Test
//	public void test9() {
//		
//	}
//	
//	@Test
//	public void test10() {
//		
//		
//	}
//	
//	@Test
//	public void test11() {
//	
//	
//	}
//	
//	@Test
//	public void test12() {
//		
//		
//		
//	}
//	
//	@Test
//	public void test13() {
//		
//	
//	}
//	
//	@Test
//	public void test14() {
//		
//		
//		
//	}
//	
//	@Test
//	public void test15() {
//		
//	}
//

}
