package com.Anton.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Anton.entities.Operation;

/**
 * @author Anton
 */
public interface OperationRepository extends JpaRepository<Operation, Long> {

    @Query("select op from Operation op where op.account.accountId = :x order by op.operationDate desc")
    Page<Operation> getAccountOperationsByPage(@Param("x") String accountId, Pageable pageable);

}
