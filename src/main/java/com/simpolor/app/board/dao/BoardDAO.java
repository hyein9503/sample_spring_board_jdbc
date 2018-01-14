package com.simpolor.app.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import com.simpolor.app.board.vo.BoardVO;

public class BoardDAO {
	
	private JdbcTemplate jdbcTemplate;

	public BoardDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	public List selectBoardList(BoardVO boardVO){
		
		List list = null;
		
		String query = ""; 
		query += " SELECT  seq, title, contents, reg_id, reg_name, DATE(reg_date) reg_date, mod_id, mod_name, DATE(mod_date) mod_date ";
		query += " FROM board ";
		query += " ORDER BY seq DESC";
		
		list = jdbcTemplate.query(
							query
							, new RowMapper<BoardVO>(){
								public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
									BoardVO boardVO = new BoardVO();
									boardVO.setSeq(rs.getInt("seq"));
									boardVO.setTitle(rs.getString("title"));
									boardVO.setContents(rs.getString("contents"));
									boardVO.setReg_id(rs.getString("reg_id"));
									boardVO.setReg_name(rs.getString("reg_name"));
									boardVO.setReg_date(rs.getString("reg_date"));
									boardVO.setMod_id(rs.getString("mod_id"));
									boardVO.setMod_name(rs.getString("mod_name"));
									boardVO.setMod_date(rs.getString("mod_date"));
									
									return boardVO;
								}
							});
		return list;
	}
	
	public BoardVO selectBoard( int seq ){
		
		BoardVO boardVO = null;
		
		String query = ""; 
		query += " SELECT seq, title, contents, reg_id, reg_name, DATE(reg_date) reg_date, mod_id, mod_name, DATE(mod_date) mod_date ";
		query += " FROM board ";
		query += " WHERE seq=? ";
		
		boardVO = jdbcTemplate.queryForObject(
				query
				, new Object[]{seq}
				, new RowMapper<BoardVO>(){
					public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
						BoardVO boardVO = new BoardVO();
						boardVO.setSeq(rs.getInt("seq"));
						boardVO.setTitle(rs.getString("title"));
						boardVO.setContents(rs.getString("contents"));
						boardVO.setReg_id(rs.getString("reg_id"));
						boardVO.setReg_name(rs.getString("reg_name"));
						boardVO.setReg_date(rs.getString("reg_date"));
						boardVO.setMod_id(rs.getString("mod_id"));
						boardVO.setMod_name(rs.getString("mod_name"));
						boardVO.setMod_date(rs.getString("mod_date"));
						
						return boardVO;
					}
				});
		return boardVO;
	}
	
	public int insertBoard( final BoardVO boardVO ){
		
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						" INSERT INTO BOARD(seq, title, contents, reg_id, reg_name, reg_date ) "+
						" VALUES( (SELECT IFNULL(MAX(seq)+1, 1) FROM board t1), ?, ?, ?, ?, now() ) ");
				pstmt.setString(1,  boardVO.getTitle());
				pstmt.setString(2,  boardVO.getContents());
				pstmt.setString(3,  boardVO.getReg_id());
				pstmt.setString(4,  boardVO.getReg_name());
				
				return pstmt;
			}
		});
		return result;
	}
	
	public int updateBoard( final BoardVO boardVO ){
		
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						" UPDATE board "+
						" SET title=?, contents=?, mod_id=?, mod_name=?, mod_date=now() "+
						" WHERE seq=? ");
				pstmt.setString(1,  boardVO.getTitle());
				pstmt.setString(2,  boardVO.getContents());
				pstmt.setString(3,  boardVO.getMod_id());
				pstmt.setString(4,  boardVO.getMod_name());
				pstmt.setInt(5,  boardVO.getSeq());
				
				return pstmt;
			}
		});
		return result;
	}
	
	public int deleteBoard( final int seq ){
		
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// 완전 삭제가 필요할 경우 
				PreparedStatement pstmt = con.prepareStatement(
						" DELETE FROM board  "+
						" WHERE seq=? ");
				pstmt.setInt(1, seq);
				
				return pstmt;
			}
		});
		return result;
	}
}
