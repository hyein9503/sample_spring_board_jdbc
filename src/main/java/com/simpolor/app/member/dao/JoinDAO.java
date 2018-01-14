package com.simpolor.app.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.simpolor.app.member.vo.MemberVO;

public class JoinDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JoinDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int selectMemberIdDupCheck( String member_id ){

		int result = 0;
		
		String query = ""; 
		query += " SELECT COUNT(*) ";
		query += " FROM member ";
		query += " WHERE member_id = ? ";
		
		result = jdbcTemplate.queryForObject(query, Integer.class, member_id);

		return result;
	}
	
	public int selectMemberEmailDupCheck( String email ){

		int result = 0;
		
		String query = ""; 
		query += " SELECT COUNT(*) ";
		query += " FROM member ";
		query += " WHERE email = ? ";
		
		result = jdbcTemplate.queryForObject(query, Integer.class, email);

		return result;
	}
	
	
	public int insertJoinMember( final MemberVO memberVO ){
		
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						" INSERT INTO member( member_id, member_pw, member_name, email, reg_date ) "+
						" VALUES( ?, ?, ?, ?, now() ) ");
				pstmt.setString(1,  memberVO.getMember_id());
				pstmt.setString(2,  memberVO.getMember_pw());
				pstmt.setString(3,  memberVO.getMember_name());
				pstmt.setString(4,  memberVO.getEmail());
				
				return pstmt;
			}
		});
		return result;
	}
}