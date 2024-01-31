package com.poseidon.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.AdminDAO;


@WebServlet("/admin/ip") //url의 경로 = 실제 파일과 다르게 호출할 url을 지정합니다.
public class Ip extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ip() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO dao = new AdminDAO();
		
		List<Map<String, Object>> list1 = dao.mostConnectedIP5();
		request.setAttribute("list1", list1);
		
		List<Map<String, Object>> list2 = dao.latestAccessIP10();
		request.setAttribute("list2", list2);
		
		List<Map<String, Object>> list = dao.ipList();
		request.setAttribute("list", list);
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/ip.jsp");//파일 있는 경로
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

}
