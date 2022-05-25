package com.lzh.graduationdesign.service;

/**
 * @InterfaceName MailService
 * @Author HackerLZH
 * @Date 2022/4/15 9:57
 * @Description
 */
public interface MailService {
    /**
     * 发送邮件
     * @Param [to, subject, text]
     * @return void
     */
    void send(String to, String subject, String text);
}
