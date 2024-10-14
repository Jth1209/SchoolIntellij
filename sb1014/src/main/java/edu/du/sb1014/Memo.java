package edu.du.sb1014;

import org.hibernate.annotations.SQLUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Memo {//이렇게 하면 memo라는 이름의 테이블을 만들어줌
    @Id
    private long id;//primary key
    private String text;

}
