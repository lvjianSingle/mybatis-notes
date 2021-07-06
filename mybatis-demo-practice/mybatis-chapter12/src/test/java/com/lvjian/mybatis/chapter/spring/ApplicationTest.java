package com.lvjian.mybatis.chapter.spring;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * Title: ApplicationTest
 * Description: TODO 描述一下这个类是干嘛的
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/7/6 0:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class ApplicationTest {

    protected MockMvc mockMvc;
    @Resource
    private WebApplicationContext applicationContext;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

}
