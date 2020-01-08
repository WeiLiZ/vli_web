package com.vli.from;

import lombok.Data;

/**
 * @author ZL
 * Created on 2019/11/20.
 */
@Data
public class CommentForm {

    private Integer id;

    private Integer articleId;

    private String nickName;

    private String mailbox;

    private String qqNumber;

    private String headPortrait;

    private String content;

    private Integer userId;
}
