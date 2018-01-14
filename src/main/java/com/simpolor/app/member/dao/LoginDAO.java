package com.simpolor.app.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.simpolor.app.member.vo.MemberVO;

public class LoginDAO {
	
	private JdbcTemplate jdbcTemplate;

	public LoginDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public MemberVO selectMemberLogin( String member_id, String member_pw ){
		
		String query = ""; 
		query += " SELECT member_id, member_name, email ";
		query += " FROM member ";
		query += " WHERE member_id = ? ";
		query += " AND member_pw = ? ";
		
		List<MemberVO> results = jdbcTemplate.query(
				query
				, new Object[]{member_id, member_pw}
				, new RowMapper<MemberVO>(){
					public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
						MemberVO memberVO = new MemberVO();
						
						memberVO.setMember_id(rs.getString("member_id"));
						memberVO.setMember_name(rs.getString("member_name"));
						memberVO.setEmail(rs.getString("email"));
						
						return memberVO;
					}
				});
		return results.isEmpty() ? null : results.get(0);
	}
}