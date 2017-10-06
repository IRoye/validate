package com.roye.service;

import java.util.HashMap;

/**
 * 发送验证码的service
 *
 */
public interface sendSMSService {

    HashMap<String, String> sendSMS();

}
