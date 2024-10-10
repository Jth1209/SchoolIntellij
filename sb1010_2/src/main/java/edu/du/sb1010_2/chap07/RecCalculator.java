package edu.du.sb1010_2.chap07;

public class RecCalculator implements Calculator {
    @Override
    public long factorial(long num) {//재귀함수 사용
        long start = System.nanoTime();//시작시간 check!

            if (num == 0)
                return 1;
            else
                return num * factorial(num - 1);
    }

}
