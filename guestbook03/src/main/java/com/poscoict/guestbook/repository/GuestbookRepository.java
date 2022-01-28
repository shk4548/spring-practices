package com.poscoict.guestbook.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.poscoict.guestbook.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	
	public List<GuestbookVo> findAll(){
		List<GuestbookVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();


			String sql = "select no , name , date_format(reg_date, '%Y/%m/%d %H:%i:%s') as\r\n"
		      		+ "reg_date, message from guestbook order by reg_date desc;";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			

			while(rs.next()) {
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String date = rs.getString(3);
				String message = rs.getString(4);
				
				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setDate(date);
				vo.setMessage(message);
				
				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.print("error : " + e);
		} finally {
			// 자원정리
			try {
				if(rs !=null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	public boolean insert(GuestbookVo vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

				
			String sql = "insert into guestbook  values (null,?,?,?,now())";
			pstmt = conn.prepareStatement(sql);
	

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());
			
			result = pstmt.executeUpdate() == 1;

		} catch (SQLException e) {
			System.out.print("error : " + e);
		} finally {
			// 자원정리
			try {
				if(rs !=null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		}
	
	
	
	public boolean delete (GuestbookVo vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
				
			String sql = "delete from guestbook where no= ? and password= ? ";
			pstmt = conn.prepareStatement(sql);
	

			pstmt.setInt(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			

			result = pstmt.executeUpdate() == 1;

		} catch (SQLException e) {
			System.out.print("error : " + e);
		} finally {

			try {
				if(rs !=null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			//1. JDBC 드라이버 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url ="jdbc:mysql://192.168.0.57:3307/webdb?characterEncoding=UTF-8&serverTimezone=UTC"; 
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		}catch(ClassNotFoundException e) {
			System.out.print("드라이버 로딩 실패 : " + e);
		}
		
		return conn;
	}
}