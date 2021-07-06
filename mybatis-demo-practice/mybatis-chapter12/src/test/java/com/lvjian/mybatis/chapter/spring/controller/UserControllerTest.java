package com.lvjian.mybatis.chapter.spring.controller;

import com.lvjian.mybatis.chapter.spring.ApplicationTest;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Title: UserControllerTest
 * Description: TODO 描述一下这个类是干嘛的
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/7/6 0:24
 */
public class UserControllerTest extends ApplicationTest {

    @Test
    public void testUserRegister() throws Exception {
        String response = mockMvc.perform(
                get("/user/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "jack")
                        .param("password", "12323")
                        .param("phone", "189000000")
                        .param("gender", "male")
                        .param("nickName", "mack")
        ).andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        System.out.println("返回数据 = " + response);
    }

    @Test
    public void testGetAllUser() throws Exception {
        String response = mockMvc.perform(
                get("/user/getAllUser")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        System.out.println("返回数据 = " + response);
    }

}
