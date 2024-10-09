package com.systex.ajaxwork.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberModel, Integer> {
    MemberModel findByUsername(String username);
}
