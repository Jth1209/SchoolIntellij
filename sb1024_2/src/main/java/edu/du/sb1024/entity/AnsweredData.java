package edu.du.sb1024.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnsweredData {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEMBER_ID")
	private int id;

	@ElementCollection
	@CollectionTable(
			name = "responses",//데이터베이스 이름
			joinColumns = @JoinColumn(name = "MEMBER_ID")//이 테이블의 pk
	)
	@OrderColumn
	@Column(name = "seq")
	private List<String> responses;

	@OneToOne
	@JoinColumn(name = "RESPONDENT_ID")
	private Respondent res;

	private String uname;

}
