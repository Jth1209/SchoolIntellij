package edu.du.sb1023_prob.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Respondent {

	@Id
	@GeneratedValue
	@Column(name="res_id")
	private int id;
	private int age;
	private String location;

	@OneToMany(mappedBy="ad_id")
	private List<AnsweredData> answeredData;


}
