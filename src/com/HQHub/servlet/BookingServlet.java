package com.HQHub.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HQHub.DAO.BookingDAO;
import com.HQHub.pojo.Booking;
import com.HQHub.pojo.ParkingSpace;
import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class BookingServlet
 */
@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String phone;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();
		
		String type = request.getParameter("type");
		switch (type) {	

		case"booking":
			

			String duration = request.getParameter("duration");
			String start_time = request.getParameter("start_time");
			
			List<ParkingSpace> pList = BookingDAO.getAvailableSpace(start_time, Integer.parseInt(duration));
			if(pList.size()>0){
				
				
				
				
				int num = (int)((Math.random())*pList.size());
				Booking b = new Booking(pList.get(num).getId(),"18862300786",BookingDAO.toTimestamp(start_time),
						BookingDAO.getEndTime(BookingDAO.toTimestamp(start_time), Integer.parseInt(duration)),Integer.parseInt(duration));

				BookingDAO.Add(b);
				List<ParkingSpace> newPList =new ArrayList<>();
				newPList.add(pList.get(num));
				out.print(JSON.toJSON(newPList));
			}else{

				out.print(JSON.toJSON(pList));
			}
				
			break;
		}
	}

}
