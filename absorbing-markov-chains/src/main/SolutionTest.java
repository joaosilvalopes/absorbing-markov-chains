package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SolutionTest {

	Fraction f(long i) {
		return new Fraction(i, 1);
	}
	
	Fraction f(long i,long j) {
		return new Fraction(i, j);
	}
	
	@Test
	void testMultiplyMatrix() {
		Fraction [][] m1 = new Fraction[][]{
			{f(1),f(2),f(3)},
			{f(4),f(5),f(6)},
			{f(7),f(8),f(9)}
		};
		Fraction [][] m2 = new Fraction[][]{
			{f(3),f(2),f(1)},
			{f(6),f(5),f(4)},
			{f(9),f(8),f(7)},
		};
		
		assertArrayEquals(new Fraction[][] {
			{f(42),f(36),f(30)},
			{f(96),f(81),f(66)},
			{f(150),f(126),f(102)}
		}, Solution.multiplyMatrix(m1, m2));
	}

	@Test
	void testIdentityMatrix() {
		assertArrayEquals(new Fraction[][] {
			{f(1),f(0),f(0)},
			{f(0),f(1),f(0)},
			{f(0),f(0),f(1)}
		}, Solution.identityMatrix(3));
	}

	@Test
	void testInvertMatrix() {
		Fraction[][] m = new Fraction[][] {
			{f(0),f(2),f(0)},
			{f(1),f(0),f(0)},
			{f(0),f(0),f(3)}
		};
		assertArrayEquals(Solution.identityMatrix(3), Solution.multiplyMatrix(m, Solution.invertMatrix(m)));

		m = new Fraction[][] {
			{f(1),f(3),f(3)},
			{f(1),f(4),f(3)},
			{f(1),f(3),f(4)}
		};
		assertArrayEquals(Solution.identityMatrix(3), Solution.multiplyMatrix(m, Solution.invertMatrix(m)));
		
		m = new Fraction[][] {
			{f(1,1), f(0,1), f(1,-3), f(0,1), f(5,-12), f(0,1), f(0,1), f(0,1), f(1,-36), f(-2,9)}, 
			{f(0,1), f(1,1), f(-3,4), f(0,1), f(0,1), f(7,-80), f(13,-80), f(0,1), f(0,1), f(0,1)},
			{f(0,1), f(3,-8), f(1,1), f(1,-5), f(-7,40), f(0,1), f(0,1), f(1,-40), f(9,-40), f(0,1)},
			{f(-23,24), f(0,1), f(0,1), f(1,1), f(0,1), f(1,-24), f(0,1), f(0,1), f(0,1), f(0,1)},
			{f(37,-96), f(35,-96), f(0,1), f(0,1), f(1,1), f(0,1), f(1,-32), f(-7,32), f(0,1), f(0,1)},
			{f(0,1), f(0,1), f(0,1), f(0,1), f(0,1), f(-1,1), f(0,1), f(0,1), f(0,1), f(0,1)},
			{f(0,1), f(0,1), f(0,1), f(0,1), f(0,1), f(0,1), f(-1,1), f(0,1), f(0,1), f(0,1)},
			{f(0,1), f(0,1), f(0,1), f(0,1), f(0,1), f(0,1), f(0,1), f(-1,1), f(0,1), f(0,1)},
			{f(0,1), f(0,1), f(0,1), f(0,1), f(0,1), f(0,1), f(0,1), f(0,1), f(-1,1), f(0,1)},
			{f(0,1), f(0,1), f(0,1), f(0,1), f(0,1), f(0,1), f(0,1), f(0,1), f(0,1), f(-1,1)}
		};
		
		assertArrayEquals(Solution.identityMatrix(m.length), Solution.multiplyMatrix(m, Solution.invertMatrix(m)));
	}

	@Test
	void testProbabilityMatix() {
		int[][] in = new int[][]{
		  {0,1,0,0,0,1},
		  {4,0,0,3,2,0},
		  {0,0,0,0,0,0},
		  {0,0,0,0,0,0},
		  {0,0,0,0,0,0},
		  {0,0,0,0,0,0}
		};
		
		Fraction[][] pm = Solution.probabilityMatrix(in);
		Fraction[][] expected = new Fraction[][] {
			  {f(0),f(1,2),f(0),f(0),f(0),f(1,2)},
			  {f(4,9),f(0),f(0),f(1,3),f(2,9),f(0)},
			  {f(0),f(0),f(2),f(0),f(0),f(0)},
			  {f(0),f(0),f(0),f(2),f(0),f(0)},
			  {f(0),f(0),f(0),f(0),f(2),f(0)},
			  {f(0),f(0),f(0),f(0),f(0),f(2)}
		};

		assertArrayEquals(pm, expected);
	}
	
	@Test
	void test() {
		assertArrayEquals(Solution.solution(new int[][]{
			{0,1,0,0,0,1},
			{4,0,0,3,2,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0}
		}), new int[] {0, 3, 2, 9, 14});

		assertArrayEquals(Solution.solution(new int[][]{
			{0, 2, 1, 0, 0},
			{0, 0, 0, 3, 4},
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0}
		}), new int[] {7, 6, 8, 21});


		assertArrayEquals(Solution.solution(new int[][]{
	        {1, 2, 3, 0, 0, 0},
	        {4, 5, 6, 0, 0, 0},
	        {7, 8, 9, 1, 0, 0},
	        {0, 0, 0, 0, 1, 2},
	        {0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0}
		}), new int[] {1, 2, 3});
		
		assertArrayEquals(Solution.solution(new int[][]{
			{0}
		}), new int[] {1, 1});

		assertArrayEquals(Solution.solution(new int[][]{
	        {0, 0, 12, 0, 15, 0, 0, 0, 1, 8},
	        {0, 0, 60, 0, 0, 7, 13, 0, 0, 0},
	        {0, 15, 0, 8, 7, 0, 0, 1, 9, 0},
	        {23, 0, 0, 0, 0, 1, 0, 0, 0, 0},
	        {37, 35, 0, 0, 0, 0, 3, 21, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		}), new int[] {1, 2, 3, 4, 5, 15});

		assertArrayEquals(Solution.solution(new int[][]{
	        {0, 7, 0, 17, 0, 1, 0, 5, 0, 2},
	        {0, 0, 29, 0, 28, 0, 3, 0, 16, 0},
	        {0, 3, 0, 0, 0, 1, 0, 0, 0, 0},
	        {48, 0, 3, 0, 0, 0, 17, 0, 0, 0},
	        {0, 6, 0, 0, 0, 1, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		}), new int[] {4, 5, 5, 4, 2, 20});

		assertArrayEquals(Solution.solution(new int[][]{
	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		}), new int[] {1, 1, 1, 1, 1, 5});
		
		assertArrayEquals(Solution.solution(new int[][]{
	        {1, 1, 1, 0, 1, 0, 1, 0, 1, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {1, 0, 1, 1, 1, 0, 1, 0, 1, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {1, 0, 1, 0, 1, 1, 1, 0, 1, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {1, 0, 1, 0, 1, 0, 1, 1, 1, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		}), new int[] {2, 1, 1, 1, 1, 6});
		
		assertArrayEquals(Solution.solution(new int[][]{
	        {0, 86, 61, 189, 0, 18, 12, 33, 66, 39},
	        {0, 0, 2, 0, 0, 1, 0, 0, 0, 0},
	        {15, 187, 0, 0, 18, 23, 0, 0, 0, 0},
	        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		}), new int[] {6, 44, 4, 11, 22, 13, 100});
		
		assertArrayEquals(Solution.solution(new int[][]{
	        {0, 0, 0, 0, 3, 5, 0, 0, 0, 2},
	        {0, 0, 4, 0, 0, 0, 1, 0, 0, 0},
	        {0, 0, 0, 4, 4, 0, 0, 0, 1, 1},
	        {13, 0, 0, 0, 0, 0, 2, 0, 0, 0},
	        {0, 1, 8, 7, 0, 0, 0, 1, 3, 0},
	        {1, 7, 0, 0, 0, 0, 0, 2, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		}), new int[] {1, 1, 1, 2, 5});
	}
	
}
