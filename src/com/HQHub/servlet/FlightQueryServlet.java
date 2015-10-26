package com.HQHub.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HQHub.DAO.FlightQueryDAO;
import com.HQHub.DAO.TrainQueryDAO;
import com.HQHub.pojo.F1DynFlight;
import com.HQHub.pojo.RRdt;
import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class FlightSerlvet
 */
@WebServlet("/FlightSerlvet")
public class FlightQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightQueryServlet() {
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
		String date = request.getParameter("date");
		switch (type) {		
		
		case "flight_no":
			String flight_no = request.getParameter("flight_no");
	
			List <F1DynFlight> fByFlightNo = FlightQueryDAO.getFlight(flight_no,date);
			 out.println(JSON.toJSON(fByFlightNo));
			break;
		case "airport":			
			String origin = request.getParameter("origin");
			String dest = request.getParameter("dest");		
			List <F1DynFlight> fByAirport = FlightQueryDAO.getFlight(origin,dest,date);
			out.println(JSON.toJSON(fByAirport));
			
			break;
		case "dynamic":			

			List <F1DynFlight> fByDynamic = FlightQueryDAO.getFlight();
			out.println(JSON.toJSON(fByDynamic));
			break;
		
		}
	}

}
