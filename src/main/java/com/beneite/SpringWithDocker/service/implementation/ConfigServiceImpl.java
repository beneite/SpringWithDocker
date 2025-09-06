package com.beneite.SpringWithDocker.service.implementation;

import com.beneite.SpringWithDocker.entity.ConfigEntity;
import com.beneite.SpringWithDocker.exception.ResourceNotFoundException;
import com.beneite.SpringWithDocker.repository.ConfigRepository;
import com.beneite.SpringWithDocker.service.ConfigService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConfigServiceImpl implements ConfigService {

    private ConfigRepository configRepository;

    public boolean isValidConfigValue(String configKey, String valueExpected) {

        ConfigEntity configEntity = configRepository.findByKey(configKey).orElseThrow(
                () -> new ResourceNotFoundException(configKey)
        );
        String valuesString = configEntity.getValues();
        List<String> configValuesList;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            configValuesList = objectMapper.readValue(valuesString, new TypeReference<List<String>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (configValuesList.contains(valueExpected))
            return true;
        else {
            throw new ResourceNotFoundException(configKey + "=" + valueExpected);
        }
    }

}
