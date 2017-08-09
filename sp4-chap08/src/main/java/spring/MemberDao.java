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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

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
	
	public void insert(final Member member){
		//GeneratedKeyHolder 클래스는 자동 생성된 키 값을 구해주는 KeyHolder 구현 클래스이다.
		KeyHolder keyHolder = new GeneratedKeyHolder();
		//update 메서드는 PreparedStatementCreator 객체와 KeyHolder 객체를 파라미터로 갖는 메서드이다.
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE)" +
						"values (?,?,?,?)",
						new String[]{"ID"}
						); //두 번째 파라미터로 String 배열인 {"ID"}를 주었는데, 이 두 번째 파라미터는 자동 생성되는 키 칼럼 목록을 지정할 때 사용되며,
				 		   //우리가 생성한 MEMBER 테이블의 경우 ID칼럼이 자동 증가 키 칼럼이므로  두 번째 파라미터 값으로 {"ID"}를 주었다.
				pstmt.setString(1,  member.getEmail());
				pstmt.setString(2,  member.getPassword());
				pstmt.setString(3,  member.getName());
				pstmt.setTimestamp(4,  new Timestamp(member.getRegisterDate().getTime()));
				
				return pstmt;
			}
		}, keyHolder); //단순히 보면 keyHolder는 update() 메서드의 두 번째 파라미터로 전달된 것이다.
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}
	
	//기존 update 메서드
	public void update(Member member){
		jdbcTemplate.update(
				"update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
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
				"select count(*) from MEMBER", Integer.class);
		return count;
	}
	
}
