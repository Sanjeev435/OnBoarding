package com.code.onboarding.service.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.code.onboarding.entity.Project;
import com.code.onboarding.service.specifications.SearchCriteria.OperationType;

public class ProjectSpecification implements Specification<Project> {

  private static final long serialVersionUID = 1L;
  private SearchCriteria searchCriteria;

  public ProjectSpecification(SearchCriteria searchCriteria) {
    this.searchCriteria = searchCriteria;
  }

  @Override
  public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query,
      CriteriaBuilder cb) {
    if (searchCriteria.getOperation().equals(OperationType.GT_THEN_EQL_TO)) {
      return cb.greaterThanOrEqualTo(root.<String>get(searchCriteria.getKey()),
          searchCriteria.getValue().toString());
    } else if (searchCriteria.getOperation().equals(OperationType.LESS_THEN_EQL_TO)) {
      return cb.lessThanOrEqualTo(root.<String>get(searchCriteria.getKey()),
          searchCriteria.getValue().toString());
    } else if (searchCriteria.getOperation().equals(OperationType.LIKE)) {
      if (root.get(searchCriteria.getKey()).getJavaType() == String.class) {
        return cb.like(root.<String>get(searchCriteria.getKey()),
            "%" + searchCriteria.getValue() + "%");
      } else {
        return cb.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
      }
    } else if (searchCriteria.getOperation().equals(OperationType.EQ)) {
      return cb.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
    }
    return null;
  }

  /**
   * Use this class for complex Project specifications
   * 
   * @author Sanjeev
   */
  public static class ComplexSpecifications {

    // restrict default constructor
    private ComplexSpecifications() {
    }

    public static Specification<Project> getProjectByKeyword(String keyword) {
      return new Specification<Project>() {
        private static final long serialVersionUID = 1L;

        @Override
        public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query,
            CriteriaBuilder cb) {
          return query.where(cb.or(
              cb.like(cb.lower(root.get("projectAppliCode")),
                  "%" + keyword.toLowerCase() + "%"),
              cb.like(cb.lower(root.get("projectLongName")),
                  "%" + keyword.toLowerCase() + "%"),
              cb.like(cb.lower(root.get("projectShortName")),
                  "%" + keyword.toLowerCase() + "%"),
              cb.like(cb.lower(root.get("unitName")), "%" + keyword.toLowerCase() + "%")))
              .getRestriction();
        }
      };
    }
  }
}
