package edu.du.sb1008.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class SimpleBbsDto {
    private int id;
    private String writer;
    private String title;
    private String content;

    public SimpleBbsDto(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}
