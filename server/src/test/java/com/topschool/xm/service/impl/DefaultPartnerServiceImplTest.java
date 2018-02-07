package com.topschool.xm.service.impl;

import com.topschool.xm.BaseTest;
import com.topschool.xm.service.PartnerService;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultPartnerServiceImplTest extends BaseTest {

    PartnerService partnerService = getBean(DefaultPartnerServiceImpl.class);

    @Test
    public void register() {
        partnerService.register("abcdefg", "张三", "xxxx", "xxxxxxx", 1);
    }

    @Test
    public void getAllPartner() {
    }

    @Test
    public void deletePartner() {
    }

    @Test
    public void isExist(){
        assert partnerService.uidExist("abcdefg");
    }
}