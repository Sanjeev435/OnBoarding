package com.code.onboarding.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

@NoRepositoryBean
public interface AbstractCommonRepository<T, ID extends Serializable> extends CrudRepository<T, ID>,
		QueryByExampleExecutor<T>, PagingAndSortingRepository<T, ID>, JpaSpecificationExecutor<T>, Serializable {
}
