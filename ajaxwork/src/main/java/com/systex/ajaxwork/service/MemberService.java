package com.systex.ajaxwork.service;

import org.springframework.stereotype.Service;

import com.systex.ajaxwork.error.UserAlreadyExistsException;
import com.systex.ajaxwork.error.UserNotFoundException;
import com.systex.ajaxwork.model.MemberModel;
import com.systex.ajaxwork.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberModel findByUsername(String username) throws UserNotFoundException {
        MemberModel member = memberRepository.findByUsername(username);

        if (member == null) {
            throw new UserNotFoundException("找不到用戶");
        }

        return member;
    }

    public void checkIfUserExists(String username) throws UserAlreadyExistsException {
        MemberModel member = memberRepository.findByUsername(username);

        if (member != null) {
            throw new UserAlreadyExistsException("用戶已存在");
        }
    }

    public void save(MemberModel member) {
        memberRepository.save(member);
    }
}
