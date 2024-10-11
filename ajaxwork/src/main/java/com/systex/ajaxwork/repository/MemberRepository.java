package com.systex.ajaxwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systex.ajaxwork.model.MemberModel;

@Repository
public interface MemberRepository extends JpaRepository<MemberModel, Integer> {
    MemberModel findByUsername(String username);
}
