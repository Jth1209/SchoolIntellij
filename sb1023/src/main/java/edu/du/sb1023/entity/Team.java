package edu.du.sb1023.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Team {
    @Id
    @Column(name="team_id")
    private String id;

    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public Team(String id,String name){
        this.id = id;
        this.name = name;
    }
}
