package edu.du.sb1011prob.dto;

import lombok.*;
import org.attoparser.dom.Text;

import javax.persistence.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.Date;

@Entity
@AllArgsConstructor
@ToString
@Table(name = "t_board")
@NoArgsConstructor
@Builder
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boardIdx;

	private String title;

	private String contents;

	private Integer hitCnt;

	private String creatorId;

	private String createdDatetime;

	private String updaterId;

	private Date updatedDatetime;

	private String deletedYn;
}
