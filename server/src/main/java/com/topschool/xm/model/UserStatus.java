package com.topschool.xm.model;

/**
 * @author 小强
 */
public class UserStatus {

    private Long id;
    
    private boolean allowOrdering;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public boolean getAllowOrdering() {
        return allowOrdering;
    }

    public void setAllowOrdering(boolean allowOrdering) {
        this.allowOrdering = allowOrdering;
    }
}
