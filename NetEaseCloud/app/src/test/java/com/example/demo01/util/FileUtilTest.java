package com.example.demo01.util;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * 测试文件工具类
 */
public class FileUtilTest {

    /**
     * 文件大小格式化
     */
    @Test
    public void testFormatFileSize() {
        //第一个参数等于第二个参数
        assertEquals(FileUtil.formatFileSize(1), "1.00Byte");


        //第一个参数不等于第二个参数
        //1234/1024 = 1.205....要四舍五入
        assertEquals(FileUtil.formatFileSize(1234), "1.21K");

        //第一个参数等于第二个参数
        assertNotEquals(FileUtil.formatFileSize(1234), "1K");
    }
}
