package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangkx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Book {
    private String bookName;
    private String author;
    private String time;
}
