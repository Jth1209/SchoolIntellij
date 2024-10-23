package edu.du.sb1023.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
    @Id
    @Column(name="member_id")
    private String id;

    private String username;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
