package edu.du.sb1014_2.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_board")
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardIdx;

    private String title;

    private String contents;

    private Integer hitCnt;

    private String creatorId;

    private Date createdDatetime;

    private String updaterId;

    private Date updatedDatetime;

    private String deletedYn;

    public Board(String title, String contents, Integer hitCnt, String creatorId, String updaterId, Date createdDatetime, Date updatedDatetime, String deletedYn) {
        this.title = title;
        this.contents = contents;
        this.hitCnt = hitCnt;
        this.creatorId = creatorId;
        this.updaterId = updaterId;
        this.createdDatetime = createdDatetime;
        this.updatedDatetime = updatedDatetime;
        this.deletedYn = deletedYn;
    }
}
