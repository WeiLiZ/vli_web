package com.vli.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author ZL
 * Created on 2019/11/20.
 */
@Data
@Table(name = "v_comment")
public class Comment extends Currency {

    @Column(name = "article_id")
    private  Integer articleId;

    @Column(name = "content")
    private String content;

    @Column(name = "qq_number")
    private String qqNumber;

    @Column(name = "mailbox")
    private String mailbox;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "head_portrait")
    private String headPortrait;

    @Column(name = "identity")
    private Integer identity;

    @Column(name = "ip_address")
    private String ipAddress;
}
