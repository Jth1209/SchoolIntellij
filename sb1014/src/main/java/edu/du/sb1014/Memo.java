package edu.du.sb1014;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLUpdate;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity//이게 붙으면 이 객체 자체가 db라고 생각.
@Table(name="tbl_memo")
public class Memo {//이렇게 하면 memo라는 이름의 테이블을 만들어줌
    @Id//primary key annotation
    @GeneratedValue(strategy = GenerationType.IDENTITY)//autoIncrement 옵션.
    private long id;//primary key

    @Column(length = 100, nullable=false)
    private String text;

}
