package edu.du.sb1023_prob.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class AnsweredData {

	@Id
	@GeneratedValue
	@Column(name="ad_id")
	private int ad_id;
	@ElementCollection
	private List<String> responses;
	@ManyToOne
	private Respondent res;


}
