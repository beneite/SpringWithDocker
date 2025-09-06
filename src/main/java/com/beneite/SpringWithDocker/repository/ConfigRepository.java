package com.beneite.SpringWithDocker.repository;

import com.beneite.SpringWithDocker.entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfigRepository extends JpaRepository<ConfigEntity, Long> {

    Optional<ConfigEntity> findByKey(String configKey);

}
