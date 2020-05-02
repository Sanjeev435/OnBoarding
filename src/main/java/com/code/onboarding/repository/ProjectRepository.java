package com.code.onboarding.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.code.onboarding.entity.Project;

@Repository
public interface ProjectRepository extends AbstractCommonRepository<Project, Integer>, Serializable {
}
