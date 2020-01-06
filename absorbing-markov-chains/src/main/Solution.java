package main;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	
	public static Fraction[][] addMatrix(Fraction [][] m1, Fraction [][] m2, Fraction factor) {
		Fraction [][] out = new Fraction[m1.length][m1.length];

		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m1.length; j++) {
				out[i][j] = m1[i][j].add(m2[i][j].multiply(factor));
			}
		}
		
		return out;
	}
	
	public static Fraction[][] multiplyMatrix(Fraction [][] m1, Fraction [][] m2) {
		Fraction [][] out = new Fraction[m2.length][m2.length];

		for (int i = 0; i < m2.length; i++) {
			for (int j = 0; j < m2.length; j++) {
				out[i][j] = new Fraction(0,1);
				
				for (int k = 0; k < m2.length; k++) {
					out[i][j] = out[i][j].add(m2[k][j].multiply(m1[i][k]));
				}
			}
		}
		
		return out;
	}
	
	public static Fraction[][] identityMatrix(int size) {
		Fraction [][] out = new Fraction[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				out[i][j] = new Fraction(i == j ? 1 : 0, 1);
			}
		}
		
		return out;	
	}
	
	public static Fraction[][] copyMatrix(Fraction[][] m) {
		Fraction [][] copy = new Fraction[m.length][m.length];

		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				copy[i][j] = m[i][j];
			}
		}
		
		return copy;
	}
	
	public static <T> void swap(T[] m, int i1, int i2) {
		T aux = m[i1];
		
		m[i1] = m[i2];
		m[i2] = aux;
	}
	
	public static Fraction[][] invertMatrix(Fraction[][] mIn) {
		Fraction[][] m = copyMatrix(mIn);
		Fraction[][] im = identityMatrix(m.length);

		for (int i = 0; i < m.length; i++) {
			// Solve one line at a time
			
			// If diagonal is zero swap with another row from bellow with a non zero diagonal
			if(m[i][i].isZero()) {
				for (int j = i + 1; j < m.length; j++) {
					if(!m[j][i].isZero()) {
						swap(m, i, j);
						swap(im, i, j);
						break;
					}
				}
			}
			
			// Divide whole row by constant to get 1 in diagonal
			Fraction multiplier = new Fraction(1,1).divide(m[i][i]);
			
			for (int k = 0; k < m.length; k++) {
				m[i][k] = m[i][k].multiply(multiplier);
				im[i][k] = im[i][k].multiply(multiplier);
			}
			
			
			// Subtract this row with all other rows that have a non zero in i's column
			for (int j = 0; j < m.length; j++) {
				if(j != i && !m[j][i].isZero()) {
					Fraction mul = m[j][i];

					for (int k = 0; k < m.length; k++) {
						m[j][k] = m[j][k].subtract(m[i][k].multiply(mul));
						im[j][k] = im[j][k].subtract(im[i][k].multiply(mul));
					}
				}
			}
		}
		
		return im;
	}

	public static Fraction[][] probabilityMatrix(int[][] in) {
		Fraction[][] out = new Fraction[in.length][in.length];
		
		for (int i = 0; i < in.length; i++) {
			int rowSum = 0;
			for (int j = 0; j < in.length; j++) {
				rowSum += in[i][j];
			}
			
			if(rowSum != 0) {
				for (int j = 0; j < in.length; j++) {
					out[i][j] = new Fraction(in[i][j], rowSum);
				}
			} else {
				for (int j = 0; j < in.length; j++) {
					out[i][j] = new Fraction(i == j ? 2 : 0, 1);
				}
			}
		}
		
		return out;
	}
	
	public static int[] solution(int[][] m) {
		Fraction[][] m1 = Solution.probabilityMatrix(m);
		Fraction[][] m2 = Solution.addMatrix(Solution.identityMatrix(m1.length), m1, new Fraction(-1, 1));
		Fraction[][] m3 = Solution.invertMatrix(m2);
		List<Fraction> list = new ArrayList<>();
		
		int mdc = 0;
		for (int i = 0; i < m.length; i++) {
			int rowSum = 0;
			for (int j = 0; j < m.length; j++) {
				rowSum += m[i][j];
			}
			if(rowSum == 0) {
				list.add(m3[0][i]);
				int d = (int)Math.abs(m3[0][i].denominator);
				
				if(mdc % d == 0 || d % mdc == 0) {
					mdc = Math.max(mdc, d);	
				} else {
					mdc *= d;
				}
			}
		}
		
		int[] solution = new int[list.size() + 1];
		
		for (int i = 0; i < list.size(); i++) {
			Fraction f = list.get(i);
			solution[i] = (int)Math.abs(f.numerator * (mdc / f.denominator));
		}
		
		solution[solution.length - 1] = mdc;
		
		return solution;
	}
}
