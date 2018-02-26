package com.topschool.xm.util;

import org.junit.Test;


public class AESTest {
    @Test
    public void aesDecodeTest() throws Exception {
        String data = "lS7DgKOIfBcmvRrdouisAfgjmO3e1lZFGuSSxnV78aZCPdDiB+YQYoALK1PRq4GdnEcDogks1vG7TH4jE9fpXr/NXiy+lAPzctVnHSrQ17aBrNgXT0Te+gZviC3DGa0Gu5CVy0pxSRXLGiUgaCQ6TKTU3+LoZQ+GInYc5KSZqidtqageOLB838NxmS2tFq568ibtAm1NaBzp7XwWKY9+MDzitrm74qXfjgTHor04FHHJt5Y372xUFSeCLgUufE+F/lwPX/1ucYupNhggh+khZ8y3HNUefN43Kyb0cvXu7Ank48QZaihQL8YxyBA3hHIOa0Q8787tbMbvyRnUFmjLRKzgR2vZsu8zDOE2ivUX7zUZMcEV2A18WK3lZXzF4273VtI/Bd0nTK5dQ/KX432kHZaSSwhdS1ggUOaNKDa2QI9cXVzWpzcCHcB8HDRbvAgZrHEfbdq5qd91VJYyzqGLVO25S9DtdGT5Q+KR8R4up34MAw4+v4m+csnZ7dYyjbralOuHCNcrEo9KjjsKR5J96g==";
        String sessionKey = "ykjQ+7B+5w+d49D0AEDrjA==";
        String iv = "iL1DTnPx6OhosLn+uo9Aqg==";
        String result = AESUtil.decrypt(data, sessionKey, iv, "utf-8");
        System.out.println(result);
    }
}
