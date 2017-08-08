package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

public class MemberDao {
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Member selectByEmail(String email){
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER where EMAIL = ?",
				
				new RowMapper<Member>(){
					@Override
					public Member mapRow(ResultSet rs, int nowNum) throws SQLException{
						Member member = new Member(rs.getString("EMAIL"),
							rs.getString("PASSWORD"),
							rs.getString("NAME"),
							rs.getTimestamp("REGDATE"));

						member.setId(rs.getLong("ID"));
						return member;
					}
				},
				email); //EAMIL = ? 에 들어갈 값이다.
		
		//지정한 이메일에 해당하는 MEMBER가 존재하면 해당 Member객체를 리턴하고, 그렇지 않으면 null을 리턴하도록 구현.
		return results.isEmpty() ? null : results.get(0);
	}
	
	public void insert(Member member){
		jdbcTemplate.update(
				"insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) values (?,?,?,?)",
				member.getEmail(), member.getPassword(), member.getName(),
				new Timestamp(member.getRegisterDate().getTime()));
	}
	
	//기존 update 메서드
	public void update(Member member){
		jdbcTemplate.update(
				"update MEMBER set NAME = ?, PASSWORD = ?, where EMAIL = ?",
				member.getName(), member.getPassword(), member.getEmail());
	}

	/*
	//PreparedStatement를 이용한 쿼리 실행
	public void update(final Member member){
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				//파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
				PreparedStatement pstmt = con.prepareStatement(
						"insert into MEMBER(EMAIL, PASSWORD, NAME, REGDATE) values (?,?,?,?)");
				//인덱스 파라미터 ㄱ밧 설정
				pstmt.setString(1,  member.getEmail());
				pstmt.setString(2,  member.getPassword());
				pstmt.setString(3,  member.getName());
				pstmt.setTimestamp(4,  new Timestamp(member.getRegisterDate().getTime()));
				//생성한 PreparedStatement 객체 리턴
				return pstmt;
			}
		});
	}
	*/
	
	public List<Member> selectAll(){
		List<Member> results = jdbcTemplate.query(
					"select * from MEMBER",
					new RowMapper<Member>(){
						@Override
						public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
							Member member = new Member(
									rs.getString("EMAIL"),
									rs.getString("PASSWORD"),
									rs.getString("NAME"),
									rs.getTimestamp("REGDATE"));
							member.setId(rs.getLong("ID"));
							return member;
						}
					}
				); 

		return results;
	}
	
	//새롭게 추가된 메서드
	public int count(){
		/*
		Member member = jdbcTemplate.queryForObejct(	
					"select * form MEMBER where ID = ?",
					new RowMapper<Member>(){
						@Override
						public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
							Member member = new Member(
									rs.getString("EMAIL"),
									rs.getString("PASSWORD"),
									rs.getString("NAME"),
									rs.getTimestamp("REGDATE")
									);
							member.setId(rs.getLong("ID"));
							return member;
						}
					},
					100);
					
		*/

		Integer count = jdbcTemplate.queryForObject(
				"select count(*) form MEMBER", Integer.class);
		return count;
	}
	
}
