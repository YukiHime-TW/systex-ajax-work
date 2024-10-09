package com.systex.ajaxwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systex.ajaxwork.model.MemberModel;
import com.systex.ajaxwork.model.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public MemberModel findByUsername(String username) {
        MemberModel member = memberRepository.findByUsername(username);

        if (member == null) {
            throw new RuntimeException("找不到用戶");
        }

        return member;
    }
}
