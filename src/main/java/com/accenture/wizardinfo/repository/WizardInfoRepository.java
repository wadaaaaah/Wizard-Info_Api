package com.accenture.wizardinfo.repository;

import com.accenture.wizardinfo.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WizardInfoRepository extends JpaRepository<Demo,Long> {

    Demo findByName(String name);
}
