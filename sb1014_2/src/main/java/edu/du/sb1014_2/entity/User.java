package edu.du.sb1014_2.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    private String id;
    private String password;
    private String name;
    private String email;

//    public User(String id, String password, String name, String email) {
//        this.id = id;
//        this.password = password;
//        this.name = name;
//        this.email = email;
//    }
}
