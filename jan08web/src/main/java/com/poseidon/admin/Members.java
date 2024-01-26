package com.poseidon.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.AdminDAO;
import com.poseidon.dto.MemberDTO;
import com.poseidon.util.Util;

@WebServlet("/admin/members")
public class Members extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Members() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getParameter("grade"));
		
		AdminDAO dao = new AdminDAO();
		List<MemberDTO> list = null;
		if(request.getParameter("grade") != null) {
			list = dao.memberList(Util.str2Int(request.getParameter("grade")));
		} else {
			list = dao.memberList();			
		}
		
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/members.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getParameter("mno"));
		//System.out.println(request.getParameter("grade"));
		
		//db에 변경
		AdminDAO dao = new AdminDAO();
		int result = dao.memberUpdate(Util.str2Int(request.getParameter("grade")), Util.str2Int(request.getParameter("mno")));
		System.out.println(result);
		//페이지 이동
		response.sendRedirect("./members");
		
	}

}
