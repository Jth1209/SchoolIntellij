package edu.du.sb1023_prob.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="answer")
public class AnsweredData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ad_id")
	private int ad_id;

	@ElementCollection
	@Column(name="responses")
	private List<String> responses;

	@OneToOne
	@JoinColumn(name="res_id")
	private Respondent res;


}
