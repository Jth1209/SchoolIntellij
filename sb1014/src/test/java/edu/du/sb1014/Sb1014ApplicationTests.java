package edu.du.sb1014;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class Sb1014ApplicationTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    void insert_Memo() {
//        System.out.println(memoRepository.getClass());
        IntStream.range(0,10).forEach(i->{
//            System.out.println(i);
            Memo memo = Memo.builder().text("샘플"+i).build();
            memoRepository.save(memo);//insert문 역할.
        });
    }

    @Test
    void select_Memo(){
        insert_Memo();
        Long mno = 9L;//아이디 값
        Optional<Memo> result = memoRepository.findById(mno);
        if(result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }
    @Test
    void find_All(){
//        memoRepository.findAll().forEach(System.out::println); Stream 방식
        List<Memo> memos = memoRepository.findAll();
        for(Memo memo : memos) {
            System.out.println(memo);
        }
    }

    @Test
    void update_Memo(){
        IntStream.range(0,10).forEach(i->{
            Long mno = Long.parseLong(i+"L");
            Memo memo = Memo.builder().id(mno).text("샘플00"+i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    void delete_Memo(){
        Long mno = 1L;
        memoRepository.deleteById(mno);
    }

    @Test
    void 테스트_쿼리메소드(){
        List<Memo> memos = memoRepository.findByIdBetween(2L,7L);
        for(Memo memo : memos) {
            System.out.println(memo);
        }
    }

    void 테스트_쿼리메소드2(){
        List<Memo> memos = memoRepository.findByIdBetweenOrderByIdDesc(3L,8L);
        for(Memo memo : memos) {
            System.out.println(memo);
        }
    }

    void 테스트_쿼리애노테이션(){
        List<Memo> memos = memoRepository.getListDesc();
        for(Memo memo : memos) {
            System.out.println(memo);
        }
    }

    void 카운트(){
        int count = memoRepository.getCount();
        System.out.println(count);
    }
}
