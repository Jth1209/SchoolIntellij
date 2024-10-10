package edu.du.sb1010_2.aop2;

public class MorningGreet implements Greet {

    @Override
    public void greeting() {
        System.out.println("--------------------------");
        System.out.println("좋은 아침입니다.");
        System.out.println("--------------------------");
    }
}
