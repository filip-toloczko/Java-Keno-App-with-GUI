import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.control.Button;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashSet;

class MyTest {

	gameLogic gameLogicTest = new gameLogic();
	@BeforeEach
	void init() {

	}

	// Test the addNumber method
	@Test
	void addNumberTest(){
		gameLogicTest.addNumber(1);
		assertEquals(1, gameLogicTest.userNumbers.size());
	}

	// Test the addNumber method with more values
	@Test
	void addNumberTestTwo(){
		gameLogicTest.addNumber(1);
		gameLogicTest.addNumber(2);
		gameLogicTest.addNumber(3);
		gameLogicTest.addNumber(4);
		gameLogicTest.addNumber(5);
		assertEquals(5, gameLogicTest.userNumbers.size());
	}

	// Test the setSpotCount method
	@Test
	void setSpotTestOne(){
		gameLogicTest.setSpotCount(1);
		assertEquals(1, gameLogicTest.userNumSpots, "Incorrect Number of Spots");
	}
	@Test
	void setSpotTestFour() {

		gameLogicTest.setSpotCount(4);
		assertEquals(4, gameLogicTest.userNumSpots, "Incorrect Number of Spots");
	}
	@Test
	void setSpotTestEight(){
		gameLogicTest.setSpotCount(8);
		assertEquals(8, gameLogicTest.userNumSpots, "Incorrect Number of Spots");
	}
	@Test
	void setSpotTestTen(){
		gameLogicTest.setSpotCount(10);
		assertEquals(10, gameLogicTest.userNumSpots, "Incorrect Number of Spots");
	}

	//Test the getSpotCount method
	@Test
	void getSpotTestOne(){
		gameLogicTest.setSpotCount(1);
		assertEquals(1, gameLogicTest.getSpotCount(), "Incorrect Number of Spots");
	}
	@Test
	void getSpotTestFour() {

		gameLogicTest.setSpotCount(4);
		assertEquals(4, gameLogicTest.getSpotCount(), "Incorrect Number of Spots");
	}
	@Test
	void getSpotTestEight(){
		gameLogicTest.setSpotCount(8);
		assertEquals(8, gameLogicTest.getSpotCount(), "Incorrect Number of Spots");
	}
	@Test
	void getSpotTestTen(){
		gameLogicTest.setSpotCount(10);
		assertEquals(10, gameLogicTest.getSpotCount(), "Incorrect Number of Spots");
	}

	// Test the setDrawCount method
	@Test
	void setDrawTestOne(){
		gameLogicTest.setDrawCount(1);
		assertEquals(1, gameLogicTest.userNumDraws, "Incorrect Number of Draws");
	}
	@Test
	void setDrawTestTwo() {

		gameLogicTest.setDrawCount(2);
		assertEquals(2, gameLogicTest.userNumDraws, "Incorrect Number of Draws");
	}
	@Test
	void setDrawTestThree(){
		gameLogicTest.setDrawCount(3);
		assertEquals(3, gameLogicTest.userNumDraws, "Incorrect Number of Draws");
	}
	@Test
	void setDrawTestFour(){
		gameLogicTest.setDrawCount(4);
		assertEquals(4, gameLogicTest.userNumDraws, "Incorrect Number of Draws");
	}

	// Test the getDrawCount method
	@Test
	void getDrawTestOne(){
		gameLogicTest.setDrawCount(1);
		assertEquals(1, gameLogicTest.getDrawCount(), "Incorrect Number of Draws");
	}
	@Test
	void getDrawTestTwo() {

		gameLogicTest.setDrawCount(2);
		assertEquals(2, gameLogicTest.getDrawCount(), "Incorrect Number of Draws");
	}
	@Test
	void getDrawTestThree(){
		gameLogicTest.setDrawCount(3);
		assertEquals(3, gameLogicTest.getDrawCount(), "Incorrect Number of Draws");
	}
	@Test
	void getDrawTestFour(){
		gameLogicTest.setDrawCount(4);
		assertEquals(4, gameLogicTest.getDrawCount(), "Incorrect Number of Draws");
	}

	// Test the getWinningNums method
	@Test
	void getWinningNumsTest(){
		gameLogicTest.getWinningNums();
		assertEquals(20, gameLogicTest.getWinningNums().size(), "Not enough numbers drawn");
	}

	// Test the getUserNumbers method
	@Test
	void getUserNumbersTest(){
		for(int i = 1; i < 6; i++) {
			gameLogicTest.addNumber(i);
		}
		assertEquals(5, gameLogicTest.userNumbers.size(), "Wrong size");
	}
	@Test
	void getUserNumbersTestTwo(){
		ArrayList<Integer> testArray = new ArrayList<>();
		for(int i = 1; i < 6; i++) {
			gameLogicTest.addNumber(i);
			testArray.add(i);
		}
		assertEquals(testArray, gameLogicTest.getUserNumbers(), "Arrays not equal");

	}

	// Test randomNumGen method
	@Test
	void testRandomNum(){
		assertTrue(gameLogicTest.randomNumGen() <= 80);
		assertTrue(gameLogicTest.randomNumGen() >= 1);
	}

	//Test getRandUserNums method
	@Test
	void testRandUserNums(){
		gameLogicTest.setSpotCount(4);
		gameLogicTest.getRandUserNums();
		assertEquals(4, gameLogicTest.userNumbers.size(), "Wrong size");
	}
	@Test
	void testRandUserNumsTwo(){
		gameLogicTest.userNumbers.clear();
		ArrayList<Integer> test = new ArrayList<>();
		gameLogicTest.setSpotCount(4);
		gameLogicTest.getRandUserNums();
		for(int i = 0; i < 4; i++){
			assertFalse(test.contains(gameLogicTest.userNumbers.get(i)), "Duplicate drawn");
			test.add(gameLogicTest.userNumbers.get(i));
		}
	}

	// Test drawWinningNums method
	@Test
	void testDrawWinningNums(){
		assertEquals(20, gameLogicTest.drawWinningNums().size(), "Wrong size");
	}
	@Test
	void testDrawWinningNumsTwo(){
		ArrayList<Integer> test = new ArrayList<>();
		assertEquals(20, gameLogicTest.drawWinningNums().size(), "Wrong size");
		for(int i : gameLogicTest.drawWinningNums()){
			assertFalse(test.contains(i));
			test.add(i);
		}
	}

	// Test findMatchingNums method
	@Test
	void testFindMatchingNums(){
		gameLogicTest.allDraws.clear();
		gameLogicTest.userNumbers.clear();
		gameLogicTest.setDrawCount(1);
		gameLogicTest.setSpotCount(1);
		HashSet<Integer> test1 = new HashSet<>();
		ArrayList<Integer> test2 = new ArrayList<>();

		for(int i = 0; i < 20; i++){
			test1.add(i);
		}

		test2.add(1);

		gameLogicTest.allDraws.add(test1);
		gameLogicTest.userNumbers = test2;
		gameLogicTest.findMatchingNums();

		assertEquals(1, gameLogicTest.matchingNumbersDraw.size(), "Wrong size");
		assertEquals(2, gameLogicTest.totalMoneyWon, "Wrong total money");
	}

	@Test
	void testFindMatchingNumsTwo() {
		gameLogicTest.allDraws.clear();
		gameLogicTest.userNumbers.clear();
		gameLogicTest.setDrawCount(1);
		gameLogicTest.setSpotCount(1);
		HashSet<Integer> test1 = new HashSet<>();
		ArrayList<Integer> test2 = new ArrayList<>();

		for (int i = 0; i < 20; i++) {
			test1.add(i);
		}

		test2.add(21);

		gameLogicTest.allDraws.add(test1);
		gameLogicTest.userNumbers = test2;
		gameLogicTest.findMatchingNums();

		assertEquals(1, gameLogicTest.matchingNumbersDraw.size(), "Wrong size");
		assertEquals(0, gameLogicTest.totalMoneyWon, "Wrong total money");
	}

}
