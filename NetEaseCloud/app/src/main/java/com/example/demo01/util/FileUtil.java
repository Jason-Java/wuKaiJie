package com.example.demo01.util;

/**
 *文件工具类
 */
public class FileUtil {

    /**
     * 文件大小格式化
     * @param value
     * @return
     */
    public static String formatFileSize(long value){

        double size = (double) value;

        double kiloByte = size / 1024;
        if (kiloByte < 1 && kiloByte > 0){
            return String.format("%.2fByte",size);//不要使用+，会造成内存泄漏
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1){
            return String.format("%.2fK",kiloByte);
        }

        double gigaaByte = megaByte / 1024;
        if (megaByte < 1){
            return String.format("%.2fM",megaByte);
        }

        double teraByte = gigaaByte / 1024;
        if (megaByte < 1){
            return String.format("%.2fG",gigaaByte);
        }

        return String.format("%.2fT",teraByte);
    }
}
