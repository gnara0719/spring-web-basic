package com.codeit.springwebbasic.common.log;

import com.codeit.springwebbasic.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LogExampleTest {

    @Autowired
    LogExample logExample;

    @Test
    void logTest() {
        logExample.showLog();
    }
}