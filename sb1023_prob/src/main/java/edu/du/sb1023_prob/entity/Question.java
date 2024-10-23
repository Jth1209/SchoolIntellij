package edu.du.sb1023_prob.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.List;

@Data
public class Question {

	private String title;

	private List<String> options;

	public Question(String title, List<String> options) {
		this.title = title;
		this.options = options;
	}

	public Question(String title) {
		this(title, Collections.<String>emptyList());
	}


	public boolean isChoice() {
		return options != null && !options.isEmpty();
	}

}
