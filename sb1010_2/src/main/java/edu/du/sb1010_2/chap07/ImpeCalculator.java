package edu.du.sb1010_2.chap07;

public class ImpeCalculator implements Calculator{

    @Override
    public long factorial(long num) {
        long start = System.nanoTime();//시작시간 check!
        long result = 1;
        for (long i = 1; i <= num; i++) {
            result *= i;
        }
        long end = System.nanoTime();//종료시간 check!
//        System.out.printf("ImpeCalculator factorial(%d): %d\n ", num ,(end - start));
        return result;
    }
}
