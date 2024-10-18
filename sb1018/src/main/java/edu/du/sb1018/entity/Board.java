package edu.du.sb1018.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="t_board")
@Getter
@Setter
@ToString
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int board_idx;
    private String contents;
    private Date created_datetime;
    private String creator_id;
    private String deleted_yn;
    private int hit_cnt;
    private String title;
    private Date updated_datetime;
    private String updater_id;
}
