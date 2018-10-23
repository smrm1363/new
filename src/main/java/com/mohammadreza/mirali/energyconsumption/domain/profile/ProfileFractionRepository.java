package com.mohammadreza.mirali.energyconsumption.domain.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is a repository for ProfileFractionEntity
 */
@Repository
public interface ProfileFractionRepository extends JpaRepository<ProfileFractionEntity,ProfileFractionId> {
}
