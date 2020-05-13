package com.code.onboarding.service.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.code.onboarding.entity.Employee;
import com.code.onboarding.service.specifications.SearchCriteria.OperationType;

public class EmployeeSpecification implements Specification<Employee> {

  private static final long serialVersionUID = 1L;
  private SearchCriteria searchCriteria;
  
  public EmployeeSpecification(SearchCriteria searchCriteria) {
    this.searchCriteria = searchCriteria;
  }

  @Override
  public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query,
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
   * Use this class for complex Employee specifications
   * 
   * @author Sanjeev
   */
  public static class ComplexSpecifications {

    // restrict default constructor
    private ComplexSpecifications() {
    }

    public static Specification<Employee> getEmployeesByKeyword(String keyword) {
      return new Specification<Employee>() {
        private static final long serialVersionUID = 1L;

        @Override
        public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query,
            CriteriaBuilder cb) {
          return query.where(cb.or(
              cb.like(cb.lower(root.get("fName")), "%" + keyword.toLowerCase() + "%"),
              cb.like(cb.lower(root.get("role")), "%" + keyword.toLowerCase() + "%"),
              cb.like(cb.lower(root.get("lName")), "%" + keyword.toLowerCase() + "%"),
              cb.like(cb.lower(root.get("emailId")), "%" + keyword.toLowerCase() + "%"),
              cb.like(cb.lower(root.get("subGroup")), "%" + keyword.toLowerCase() + "%")))
              .getRestriction();
        }
      };
    }
  }

}
