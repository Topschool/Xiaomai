package com.topschool.xm.model.scratchcard;


import java.sql.Date;

public class ScratchLog {

    private Integer id;
    private String uid;
    private Integer result;
    private Date scratchDate;
    private Long createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Date getScratchDate() {
        return scratchDate;
    }

    public void setScratchDate(Date scratchDate) {
        this.scratchDate = scratchDate;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }
}
