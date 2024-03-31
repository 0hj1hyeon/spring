package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id"); //table 명 key 명

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters)); // 키값 받기
        member.setId(key.longValue());
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper()); //
    }
    @Override
    public Optional<Member> findByName(String name) { //템플릿을 사용하여 멤버 조회 실행결과를 memberrowmapper를 사용함
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name); //Member 객체여도 상관 없음
        return result.stream().findAny(); //임의의 결과값 반환
    }
    private RowMapper<Member> memberRowMapper() { // 각행을 객체로 매핑
        return (rs, rowNum) -> { // resultset과 행 번호가 매개변수, 멤버 객체 반환
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name")); //
            return member;
        };
    }
}