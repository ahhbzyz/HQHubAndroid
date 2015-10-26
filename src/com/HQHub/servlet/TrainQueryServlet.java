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
 * Servlet implementation class TrainQueryServlet
 */
@WebServlet("/TrainQueryServlet")
public class TrainQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrainQueryServlet() {
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
		
		case "train_no":
			String train_no = request.getParameter("train_no");
	
			List <RRdt> rByTrainNo = TrainQueryDAO.getTrain(train_no,date);
			 out.println(JSON.toJSON(rByTrainNo));
			break;
		case "station":			
			String startstation = request.getParameter("start");
			String terminalstation = request.getParameter("terminal");		
			List <RRdt> rByTrainStation = TrainQueryDAO.getTrain(startstation,terminalstation,date);
			out.println(JSON.toJSON(rByTrainStation));
			break;
		case "dynamic":			

			List <RRdt> rByDynamic = TrainQueryDAO.getTrain();
			out.println(JSON.toJSON(rByDynamic));
			break;
		
		}
	}

}
