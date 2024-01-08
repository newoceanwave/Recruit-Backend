package com.smlikelion.webfounder.admin.repository;

import com.smlikelion.webfounder.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository <Admin, Long> {
    boolean existsByAccountId(String accountId);
    boolean existsByName(String name);

}
