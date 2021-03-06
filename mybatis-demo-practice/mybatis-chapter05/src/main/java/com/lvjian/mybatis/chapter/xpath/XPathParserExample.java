package com.lvjian.mybatis.chapter.xpath;

import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.junit.Test;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Title: XPathParserExample
 * Description: Mybatis通过XPathParser工具类封装了对XML的解析操作，同时使用XNode类增强了对XML节点的操作。
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/24 22:59
 */
public class XPathParserExample {

    @Test
    public void testXPathParser() throws Exception {
        Reader resource = Resources.getResourceAsReader("users.xml");
        XPathParser parser = new XPathParser(resource);

        // 注册日期转换器
        DateConverter dateConverter = new DateConverter(null);
        dateConverter.setPattern("yyyy-MM-dd HH:mm:ss");
        ConvertUtils.register(dateConverter, Date.class);

        List<UserEntity> userList = new ArrayList<>();

        // 调用evalNodes（）方法获取XNode列表
        List<XNode> nodes = parser.evalNodes("/users/*");

        // 对XNode对象进行遍历，获取user相关信息
        for (XNode node : nodes) {
            UserEntity userEntity = new UserEntity();
            Long id = node.getLongAttribute("id");
            BeanUtils.setProperty(userEntity, "id", id);
            List<XNode> childNods = node.getChildren();
            for (XNode childNode : childNods) {
                BeanUtils.setProperty(userEntity, childNode.getName(),childNode.getStringBody());
            }

            userList.add(userEntity);
        }
        System.out.println(JSON.toJSONString(userList));
    }

}
