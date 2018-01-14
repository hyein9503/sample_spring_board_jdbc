package com.simpolor.app.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import com.simpolor.app.member.vo.MemberVO;

public class SearchDAO {
	
	private JdbcTemplate jdbcTemplate;

	public SearchDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public MemberVO selectMemberSearch( String member_name, String email ){
		
		String query = ""; 
		query += " SELECT * ";
		query += " FROM member ";
		query += " WHERE member_name = ? AND email = ? ";
		
		List<MemberVO> results = jdbcTemplate.query(
				query
				, new Object[]{member_name, email}
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
	
	public int updateMemberChange( final MemberVO memberVO ){
		
		System.out.println("member_pw DAO : " + memberVO.getMember_pw());
		System.out.println("member_id DAO : " + memberVO.getMember_id());
		
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						" UPDATE member "+
						" SET member_pw=?, mod_date=now() "+
						" WHERE member_id=? ");
				pstmt.setString(1, memberVO.getMember_pw());
				pstmt.setString(2, memberVO.getMember_id());
				
				return pstmt;
			}
		});
		return result;
	}
}
