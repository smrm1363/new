package com.mohammadreza.mirali.energyconsumption.domain.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * This is a repository for ProfileEntity
 */
@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity,String> {
}
