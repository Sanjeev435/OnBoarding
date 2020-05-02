package com.code.onboarding.repository;

import org.springframework.stereotype.Repository;
import com.code.onboarding.entity.OnBoardingProcess;

@Repository
public interface OnBoardingProcessRepository extends AbstractCommonRepository<OnBoardingProcess, Integer> {
}
