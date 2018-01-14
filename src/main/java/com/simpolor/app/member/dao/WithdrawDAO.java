package com.simpolor.app.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.simpolor.app.member.vo.MemberVO;

public class WithdrawDAO {
	
	private JdbcTemplate jdbcTemplate;

	public WithdrawDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int deleteMember( final MemberVO memberVO ){
		
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						" DELETE FROM member "+
						" WHERE member_id=? AND member_pw=? ");
				pstmt.setString(1,  memberVO.getMember_id());
				pstmt.setString(2,  memberVO.getMember_pw());
				
				return pstmt;
			}
		});
		return result;
	}
}