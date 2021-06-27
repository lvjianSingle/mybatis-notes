package com.lvjian.mybatis.chapter;

import org.apache.ibatis.jdbc.SQL;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Title: SqlExample
 * Description: MyBatis中的SQL工具类动态构建SQL语句
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/22 21:42
 */
public class SqlExample {

    @Test
    public void testSelectSQL() {
        //字符串拼接
        String orgSql = "SELECT P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME, P.LAST_NAME, P.CREATED_ON, P.UPDATED_ON\n" +
                "FROM PERSON P, ACCOUNT A\n" +
                "INNER JOIN DEPARTMENT D on D.ID = P.DEPARTMENT_ID\n" +
                "INNER JOIN COMPANY C on D.COMPANY_ID = C.ID\n" +
                "WHERE (P.ID = A.ID AND P.FIRST_NAME like ?) \n" +
                "OR (P.LAST_NAME like ?)\n" +
                "GROUP BY P.ID\n" +
                "HAVING (P.LAST_NAME like ?) \n" +
                "OR (P.FIRST_NAME like ?)\n" +
                "ORDER BY P.ID, P.FULL_NAME";

        //SQL类构建
        SQL sql = new SQL();
        sql.SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME");
        sql.SELECT("P.LAST_NAME, P.CREATED_ON, P.UPDATED_ON");
        sql.FROM("PERSON P");
        sql.FROM("ACCOUNT A");
        sql.INNER_JOIN("DEPARTMENT D on D.ID = P.DEPARTMENT_ID");
        sql.INNER_JOIN("COMPANY C on D.COMPANY_ID = C.ID");
        sql.WHERE("P.ID = A.ID");
        sql.WHERE("P.FIRST_NAME like ?");
        sql.OR();
        sql.WHERE("P.LAST_NAME like ?");
        sql.GROUP_BY("P.ID");
        sql.HAVING("P.LAST_NAME like ?");
        sql.OR();
        sql.HAVING("P.FIRST_NAME like ?");
        sql.ORDER_BY("P.ID");
        sql.ORDER_BY("P.FULL_NAME");

        assertEquals(orgSql, sql.toString());
    }

    @Test
    public void testDynamicSQL() {
        System.out.println(selectPerson(null, null, null));
    }

    public String selectPerson(final String id, final String firstName, final String lastName) {
        return new SQL() {{
            SELECT("P.ID, P.USERNAME, P.PASSWORD");
            SELECT("P.FIRST_NAME, P.LAST_NAME");
            FROM("PERSON P");
            if (id != null) {
                WHERE("P.ID = #{id}");
            }
            if (firstName != null) {
                WHERE("P.FIRST_NAME = #{firstName}");
            }
            if (lastName != null) {
                WHERE("P.LAST_NAME = #{lastName}");
            }
            ORDER_BY("P.LAST_NAME");
        }}.toString();
    }

    @Test
    public void testInsertSql() {
        String insertSql = new SQL().
                INSERT_INTO("PERSON").
                VALUES("ID, FIRST_NAME", "#{id}, #{firstName}").
                VALUES("LAST_NAME", "#{lastName}").toString();
        System.out.println(insertSql);
    }

    @Test
    public void testDeleteSql() {
        String deleteSql = new SQL() {{
            DELETE_FROM("PERSON");
            WHERE("ID = #{id}");
        }}.toString();
        System.out.println(deleteSql);
    }

    @Test
    public void testUpdateSql() {
        String updateSql = new SQL() {{
            UPDATE("PERSON");
            SET("FIRST_NAME = #{firstName}");
            WHERE("ID = #{id}");
        }}.toString();
        System.out.println(updateSql);
    }

}
