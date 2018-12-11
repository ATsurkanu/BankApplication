package com.Anton.dao;

import com.Anton.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Anton
 */
public interface AccountRepository extends JpaRepository<Account, String> {


    @Modifying
    @Query(value = "INSERT INTO `bankdb`.`account`\n" +
            "(`account_type`,\n" +
            "`account_id`,\n" +
            "`creation_date`,\n" +
            "`discount`,\n" +
            "`overdraft`,\n" +
            "`interest_rate`,\n" +
            "`customer_code`)\n" +
            "VALUES\n" +
            "('SA', :accountId, current_time(),'0.0', '0.0', '0.0', :customerCode)", nativeQuery = true)
    void insertNewAccountForExistingCustomer(@Param(value = "accountId") String accountId,
                                             @Param(value = "customerCode") long customerCode);

}
