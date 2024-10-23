package edu.du.sb1023_prob.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@ToString
public class Choice {
    @Id
    @GeneratedValue
    @Column(name="choice_id")
    @ToString.Exclude
    private int id;
    private String first;
    private String second;
    private String third;
}
