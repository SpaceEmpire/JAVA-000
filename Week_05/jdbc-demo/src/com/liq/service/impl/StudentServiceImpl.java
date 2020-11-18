package com.liq.service.impl;

import com.liq.bean.Address;
import com.liq.bean.Student;
import com.liq.dao.JdbcUtil;
import com.liq.service.StudentService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/18 12:13
 * version: 1.0
 */
public class StudentServiceImpl implements StudentService {
    @Override
    public List<Student> findByName(String name) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        List<Student> students = new ArrayList<Student>();
        try {
            conn = JdbcUtil.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from student where name=" + name);
            while (rs.next()) {
                Student student = new Student();
                student.setAge(rs.getInt("age"));
                student.setName(rs.getString("name"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, st, rs);
        }
        return students;
    }

    @Override
    public boolean add(Student student) {
        Connection conn = null;
        Statement st = null;
        Boolean result = false;
        try {
            conn = JdbcUtil.getConnection();
            st = conn.createStatement();
            result = st.execute("insert into student(id,age,name) value(" + student.getId() + "," + student.getAge() + ",'" + student.getName() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, st, null);
        }
        return result;
    }

    @Override
    public boolean del(String name) {
        Connection conn = null;
        Statement st = null;
        Boolean result = false;
        try {
            conn = JdbcUtil.getConnection();
            st = conn.createStatement();
            result = st.execute("delete from student where name='" + name + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, st, null);
        }
        return result;
    }

    @Override
    public boolean updateAge(Student student) {
        Connection conn = null;
        Statement st = null;
        Boolean result = false;
        try {
            conn = JdbcUtil.getConnection();
            st = conn.createStatement();
            result = st.execute("update student set age=" + student.getAge() + "  where name='" + student.getName() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(conn, st, null);
        }
        return result;
    }

    @Override
    public boolean register(Student student, Address address) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        int result1 = 0;
        int result2 = 0;
        try {
            String stuSql = "insert into student(id,name,age) value (?,?,?)";
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);

            pstmt = conn.prepareStatement(stuSql);
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setInt(3, student.getAge());
            result1 = pstmt.executeUpdate();
            System.out.println("学生信息插入结果：" + result1);

            String addSql = "insert into address(stuId,address) value (?,?)";
            pstmt = conn.prepareStatement(addSql);
            pstmt.setInt(1, address.getStuId());
            pstmt.setString(2, address.getAddress());
            result2 = pstmt.executeUpdate();
            System.out.println("学生地址信息插入结果：" + result2);
            conn.commit();
        } catch (SQLException e) {
            System.out.println("************事务处理出现异常***********");
            e.printStackTrace();
            try {
                conn.rollback();
                System.out.println("************事务回滚成功***********");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JdbcUtil.release(conn, pstmt, null);
        }

        return (result1 + result2) == 2;
    }
}
