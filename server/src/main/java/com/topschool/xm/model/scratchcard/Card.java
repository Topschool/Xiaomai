package com.topschool.xm.model.scratchcard;

import java.math.BigDecimal;

/**
 * @author 小强
 */
public class Card {
    private long id;
    private BigDecimal price;
    private long uid;
    private String name;
    private boolean scratched;

    public Card() {
    }

    public Card(BigDecimal price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getScratched() {
        return scratched;
    }

    public void setScratched(boolean scratched) {
        this.scratched = scratched;
    }
}
