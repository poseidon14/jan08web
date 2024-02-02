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
		//System.out.println(request.getParameter("ip"));
		
		AdminDAO dao = new AdminDAO();
		
		request.setAttribute("list1", dao.mostConnectedIP5());
		request.setAttribute("list2", dao.latestAccessIP10());
		List<Map<String, Object>> list = null;	

		if(request.getParameter("ip") != null && !request.getParameter("ip").equals("")) {
			list = dao.ipList(request.getParameter("ip"));
		} else {
			list = dao.ipList();
		}
		
		request.setAttribute("list", list);	
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/index");//파일 있는 경로
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}