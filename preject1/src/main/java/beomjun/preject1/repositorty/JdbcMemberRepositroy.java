package beomjun.preject1.repositorty;

import beomjun.preject1.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

import static java.sql.DriverManager.getConnection;

public class JdbcMemberRepositroy implements  MemberRepository{

    private final DataSource dataSource;

    public JdbcMemberRepositroy(DataSource dataSource) {
        this.dataSource = dataSource;
        // dataSource.getConnection(); //데이터 받기 sql문
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, member.getName());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                member.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            clone(conn,rs,pstmt);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);

    }

    private void clone(Connection conn, ResultSet rs, PreparedStatement pstmt) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void clone(Connection conn) {
        DataSourceUtils.releaseConnection(conn,dataSource);
    }


    @Override
    public Optional<Member> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
