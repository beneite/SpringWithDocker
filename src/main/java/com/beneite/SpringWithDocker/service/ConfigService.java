package com.beneite.SpringWithDocker.service;


public interface ConfigService {

    boolean isValidConfigValue(String configKey, String valueExpected);

}
