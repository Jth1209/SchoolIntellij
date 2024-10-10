package edu.du.sb1010_2.main;

import edu.du.sb1010_2.chap07.Calculator;
import edu.du.sb1010_2.chap07.ExeTimeCalculator;
import edu.du.sb1010_2.chap07.ImpeCalculator;
import edu.du.sb1010_2.chap07.RecCalculator;

public class TestMain {
    public static void main(String[] args) {
        Calculator calc = new ImpeCalculator();
        long result =calc.factorial(5);
        System.out.println(result);

        Calculator calc2 = new RecCalculator();
        long result2 = calc2.factorial(5);
        System.out.println(result2);

        ImpeCalculator impe = new ImpeCalculator();
        ExeTimeCalculator exe = new ExeTimeCalculator(impe);
        long result3 = exe.factorial(5);
        System.out.println(result3);
    }
}
