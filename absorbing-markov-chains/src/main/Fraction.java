package main;

public class Fraction {
    long numerator;
    long denominator;

    public Fraction(long numerator, long denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
		reduce();
    }
   
    public boolean isZero() {
    	return this.numerator == 0;
    }
    
    public boolean equals(Object o) {
    	if(!(o instanceof Fraction)) {
    		return false;
    	}
    	
    	Fraction other = (Fraction)o;
    	
		return this.numerator == other.numerator && this.denominator == other.denominator;
    }

    public long calculateGCD(long numerator, long denominator) {
		if (numerator % denominator == 0) {
	             return denominator;
	        }
		return calculateGCD(denominator, numerator % denominator);
	}

    void reduce() {
		long gcd = calculateGCD(numerator, denominator);
		numerator /= gcd;
		denominator /= gcd;
    }

    public Fraction add(Fraction fractionTwo) {
		long newNumerator = (numerator * fractionTwo.denominator) + 
	                            (fractionTwo.numerator * denominator);
		long newDenominator = denominator * fractionTwo.denominator;
		return new Fraction(newNumerator, newDenominator);
    }

    public Fraction subtract(Fraction fractionTwo) {
        long newNumerator = (numerator * fractionTwo.denominator) - 
                                 (fractionTwo.numerator * denominator);
		long newDenominator = denominator * fractionTwo.denominator;
		Fraction result = new Fraction(newNumerator, newDenominator);
		return result;
    }

    public Fraction multiply(Fraction fractionTwo) {
		long newNumerator = numerator * fractionTwo.numerator;
		long newDenominator = denominator * fractionTwo.denominator;
		Fraction result = new Fraction(newNumerator, newDenominator);
		return result;
    }
 
    public Fraction divide(Fraction fractionTwo) {
		long newNumerator = numerator * fractionTwo.denominator;
		long newDenominator = denominator * fractionTwo.numerator;
		Fraction result = new Fraction(newNumerator, newDenominator);
		return result;
    }

    @Override
    public String toString() {
    	return this.numerator + "/" + this.denominator;
    }
 
}