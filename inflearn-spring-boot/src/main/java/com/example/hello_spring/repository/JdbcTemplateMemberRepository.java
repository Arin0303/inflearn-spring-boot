package com.example.hello_spring.repository;

import com.example.hello_spring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{


    private final JdbcTemplate jdbcTemplate;


    // 생성자(Constructor)
    // DataSource: db 연결 정보를 담고 있는 객체
    // DataSource 의존성 주입(DI)
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        // JdbcTemplate 객체 생성 -> jdbcTemplate 필드를 통해 db접근 가능
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 메서드
    @Override
    public Member save(Member member) {
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper());
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return List.of();
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> { // 람다 스타일로 변경
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
