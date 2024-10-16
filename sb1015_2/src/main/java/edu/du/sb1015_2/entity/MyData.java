package edu.du.sb1015_2.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="mydata")
public class MyData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length=50, nullable=false)
    private String name;

    @Column(length=200, nullable=true)
    private String email;

    private String age;

    private String memo;

    public MyData(String name, String email, String age, String memo) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.memo = memo;
    }
}
