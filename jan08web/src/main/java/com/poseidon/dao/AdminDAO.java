package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.dto.MemberDTO;

public class AdminDAO extends AbstractDAO {

	public List<MemberDTO> memberList() {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT mno, mid, mname, mdate, mgrade FROM member";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDTO e = new MemberDTO();
				e.setMno(rs.getInt("mno"));
				e.setMid(rs.getString("mid"));
				e.setMname(rs.getString("mname"));
				e.setMdate(rs.getString("mdate"));
				e.setMgrade(rs.getInt("mgrade"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return list;
	}

	public List<MemberDTO> memberList(int grade) {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT mno, mid, mname, mdate, mgrade FROM member WHERE mgrade=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, grade);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDTO e = new MemberDTO();
				e.setMno(rs.getInt("mno"));
				e.setMid(rs.getString("mid"));
				e.setMname(rs.getString("mname"));
				e.setMdate(rs.getString("mdate"));
				e.setMgrade(rs.getInt("mgrade"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return list;
	}

	public int memberUpdate(int grade, int mno) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE member SET mgrade=? WHERE mno=?";
		int result = 0;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, grade);
			pstmt.setInt(2, mno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		return result;
	}

	public List<BoardDTO> boardList() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT board_no, board_title, board_date, board_ip, board_del, "
				+ " (SELECT count(*) FROM visitcount v WHERE v.board_no=b.board_no) as count,"
				+ " (SELECT count(*) FROM comment c WHERE c.board_no=b.board_no) as comment, " + " m.mname "
				+ " FROM board b" + " JOIN member m ON b.mno=m.mno" + " ORDER BY board_no DESC";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO e = new BoardDTO();
				e.setNo(rs.getInt("board_no"));
				e.setTitle(rs.getString("board_title"));
				e.setWrite(rs.getString("mname"));// ? member에서 옵니다.
				e.setDate(rs.getString("board_date"));
				e.setCount(rs.getInt("count")); // ? visitcount에서 옵니다.
				e.setComment(rs.getInt("comment"));// ? 댓글에서 옵니다.
				e.setIp(rs.getString("board_ip"));
				e.setDel(rs.getInt("board_del"));// 만들어주세요.
				list.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return list;
	}

	public List<BoardDTO> boardList(String parameter) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT board_no, board_title, board_date, board_ip, board_del, "
				+ " (SELECT count(*) FROM visitcount v WHERE v.board_no=b.board_no) as count,"
				+ " (SELECT count(*) FROM comment c WHERE c.board_no=b.board_no) as comment, m.mname "
				+ " FROM board b JOIN member m ON b.mno=m.mno" + " WHERE board_title LIKE CONCAT('%', ?, '%')"
				+ "	OR board_content LIKE CONCAT('%', ?, '%')" + " OR mname LIKE CONCAT('%', ?, '%')"
				+ " ORDER BY board_no DESC";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, parameter);
			pstmt.setString(2, parameter);
			pstmt.setString(3, parameter);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO e = new BoardDTO();
				e.setNo(rs.getInt("board_no"));
				e.setTitle(rs.getString("board_title"));
				e.setWrite(rs.getString("mname"));// ? member에서 옵니다.
				e.setDate(rs.getString("board_date"));
				e.setCount(rs.getInt("count")); // ? visitcount에서 옵니다.
				e.setComment(rs.getInt("comment"));// ? 댓글에서 옵니다.
				e.setIp(rs.getString("board_ip"));
				e.setDel(rs.getInt("board_del"));// 만들어주세요.
				list.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return list;
	}

	public int boardDel(BoardDTO dto) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_del=? WHERE board_no=?";
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getDel()+"");
			pstmt.setInt(2, dto.getNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(null, pstmt, con);
		}
		return result;
	}

	public List<CommentDTO> commentList() {
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select c.cno, c.board_no, SUBSTRING(REPLACE(c.ccomment, '<br>', ' '),1, 15) as ccomment, "
				+ "if(date_format(c.cdate,'%Y-%m-%d') = date_format(current_timestamp(),'%Y-%m-%d'), "
				+ "date_format(c.cdate,'%h:%i'),date_format(c.cdate,'%Y-%m-%d')) AS cdate, "
				+ "c.clike, m.mno, m.mid, m.mname, c.cip , c.cdel "
				+ "from (comment c join member m on(c.mno = m.mno)) "
				+ "order by c.cno desc";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CommentDTO e = new CommentDTO();
				e.setCno(rs.getInt("cno"));
				e.setBoard_no(rs.getInt("board_no"));
				e.setComment(rs.getString("ccomment"));
				e.setCdate(rs.getString("cdate"));
				e.setClike(rs.getInt("clike"));
				e.setMno(rs.getInt("mno"));
				e.setMname(rs.getString("mname"));
				e.setMid(rs.getString("mid"));
				e.setIp(rs.getString("cip"));
				e.setDel(rs.getInt("cdel"));
				list.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return list;
	}

	public List<Map<String, Object>> ipList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT ino, iip, idate, iurl, idata FROM iplog ORDER BY ino DESC ";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Map<String, Object> e = new HashMap<String, Object>();
				e.put("ino", rs.getInt("ino"));
				e.put("iip", rs.getString("iip"));
				e.put("idate", rs.getString("idate"));
				e.put("iurl", rs.getString("iurl"));
				e.put("idata", rs.getString("idata"));
				list.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return list;
	}
	public List<Map<String, Object>> ipList(String ip) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT ino, iip, idate, iurl, idata FROM iplog WHERE iip=? ORDER BY ino DESC ";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ip);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Map<String, Object> e = new HashMap<String, Object>();
				e.put("ino", rs.getInt("ino"));
				e.put("iip", rs.getString("iip"));
				e.put("idate", rs.getString("idate"));
				e.put("iurl", rs.getString("iurl"));
				e.put("idata", rs.getString("idata"));
				list.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return list;
	}
	
	//가장 많이 접속한 ip - 2024-01-31 - 프레임워크 프로그래밍
	public List<Map<String, Object>> mostConnectedIP5() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT iip, COUNT(*) AS count "
				+ "FROM iplog "
				+ "GROUP BY iip "
				+ "ORDER BY count DESC LIMIT 0, 5";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Map<String, Object> e = new HashMap<String, Object>();
				e.put("iip", rs.getString("iip"));
				e.put("count", rs.getString("count"));
				list.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return list;
	}
	//가장 최근에 접속한 IP 2024-01-31
	public List<Map<String, Object>> latestAccessIP10() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT iip, idate "
				+ "FROM (SELECT DISTINCT iip, idate FROM iplog ORDER BY idate DESC) AS t "
				+ "GROUP BY t.iip ORDER BY idate DESC LIMIT 0, 10";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Map<String, Object> e = new HashMap<String, Object>();
				e.put("iip", rs.getString("iip"));
				e.put("idate", rs.getString("idate"));
				list.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		return list;
	}

}
