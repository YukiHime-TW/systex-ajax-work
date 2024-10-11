package com.systex.ajaxwork.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.systex.ajaxwork.model.MemberModel;
import com.systex.ajaxwork.repository.MemberRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private final MemberRepository memberRepository;

    public DataLoader(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 檢查是否已存在該測試帳號
        if (memberRepository.findByUsername("test") == null) {
            // 加密密碼
            String encryptedPassword = BCrypt.hashpw("test", BCrypt.gensalt());

            // 新增測試帳號
            MemberModel testMember = new MemberModel();
            testMember.setUsername("test");
            testMember.setPassword(encryptedPassword);

            memberRepository.save(testMember);
        }
    }
}