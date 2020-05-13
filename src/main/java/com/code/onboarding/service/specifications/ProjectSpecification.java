package com.code.onboarding.service.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.code.onboarding.entity.Project;

public class ProjectSpecification {
    
    public static Specification<Project> getProjectByKeyword(String keyword) {
	return new Specification<Project>() {
	    private static final long serialVersionUID = 1L;

	    @Override
	    public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query,
		    CriteriaBuilder cb) {
	      return query.where(cb.or(cb.like(cb.lower(root.get("projectAppliCode")), "%"+keyword.toLowerCase()+"%" ),
	            cb.like(cb.lower(root.get("projectLongName")), "%"+keyword.toLowerCase()+"%" ),
	            cb.like(cb.lower(root.get("projectShortName")), "%"+keyword.toLowerCase()+"%" ),
	            cb.like(cb.lower(root.get("unitName")), "%"+keyword.toLowerCase()+"%" ))).getRestriction();
	    }
	};
    }
}
