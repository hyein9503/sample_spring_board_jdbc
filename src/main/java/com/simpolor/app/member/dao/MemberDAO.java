package com.simpolor.app.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import com.simpolor.app.member.vo.MemberVO;

public class MemberDAO {
	
	private JdbcTemplate jdbcTemplate;

	public MemberDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public MemberVO selectMember( String member_id ){
		
		MemberVO memberVO = null;
		
		String query = ""; 
		query += " SELECT member_id, member_name, email, reg_date, mod_date ";
		query += " FROM member ";
		query += " WHERE member_id=? ";
		
		memberVO = jdbcTemplate.queryForObject(
				query
				, new Object[]{member_id}
				, new RowMapper<MemberVO>(){
					public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
						MemberVO  memberVO = new MemberVO();
						memberVO.setMember_id(rs.getString("member_id"));
						memberVO.setMember_name(rs.getString("member_name"));
						memberVO.setEmail(rs.getString("email"));
						memberVO.setReg_date(rs.getString("reg_date"));
						memberVO.setMod_date(rs.getString("mod_date"));
						
						return memberVO;
					}
				});
		return memberVO;
	}
	
	public int updateMember( final MemberVO memberVO ){
		
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						" UPDATE member "+
						" SET member_name=?, email=?, mod_date=now() "+
						" WHERE member_id=? ");
				pstmt.setString(1,  memberVO.getMember_name());
				pstmt.setString(2,  memberVO.getEmail());
				pstmt.setString(3,  memberVO.getMember_id());
				
				return pstmt;
			}
		});
		return result;
	}
	
	public int updateMemberPassword( final MemberVO memberVO ){
		
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						" UPDATE member "+
						" SET member_pw=?, mod_date=now() "+
						" WHERE member_id=? ");
				pstmt.setString(1,  memberVO.getMember_pw());
				pstmt.setString(2,  memberVO.getMember_id());
				
				return pstmt;
			}
		});
		return result;
	}
	
	public int selectMemberDupCheck( String member_id, String member_pw ){

		int result = 0;
		
		String query = ""; 
		query += " SELECT COUNT(*) ";
		query += " FROM member ";
		query += " WHERE member_id = ? AND member_pw = ? ";
		
		result = jdbcTemplate.queryForObject(query, Integer.class, member_id, member_pw );

		return result;
	}
}
