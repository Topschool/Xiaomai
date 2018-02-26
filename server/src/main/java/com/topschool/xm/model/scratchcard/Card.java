package com.topschool.xm.model.scratchcard;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author 小强
 */
@Component
@Scope("request")
public class Card {
    private String uid;
    private String nickname;
    private Integer price;
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
