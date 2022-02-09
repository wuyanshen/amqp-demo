package com.lvcoding.amqpdemo.jasypt;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @description 描述
 * @date   2022-02-08 下午3:46
 * @author  wuyanshen
 */
public class Test {

    public static void main(String[] args) {
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword("");

        String decrypt = basicTextEncryptor.decrypt("9/liGPfZ74nHCMM/2fg6rYWIUmnDFQ8b");
        System.out.println("decrypt = " + decrypt);
    }
}
