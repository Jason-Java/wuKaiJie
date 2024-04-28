package com.example.demo01.util;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 *字符串工具类的测试
 */
public class StringUtilTest {
    /**
     * 测试是否为手机号
     */
    @Test
    public void testIsPhone(){

        //这是一个正确的手机号格式
        //所以用断言判断结果未true
        //只有结果为true才表示测试通过


        assertTrue(StringUtil.isPhone("15868371111"));

        assertFalse(StringUtil.isPhone("1111"));
    }
}
