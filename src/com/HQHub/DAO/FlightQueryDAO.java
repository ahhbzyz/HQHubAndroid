package com.HQHub.DAO;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.HQHub.Util.Connection;
import com.HQHub.pojo.F1DynFlight;
import com.HQHub.pojo.F3Airport;
import com.HQHub.pojo.RRdt;

public class FlightQueryDAO {
	
	
	public static List<F1DynFlight> getFlight(String origin_airport_iata, String dest_airport_iata, String operation_date ){
		
		Session session = Connection.getSession("oracle");
		String hql = "select f"
				+ " from F1DynFlight f"
				+ " where f.origin_airport_iata =:origin_airport_iata"
				+ " and f.dest_airport_iata =:dest_airport_iata"
				+ " and to_char(f.operation_date,'yyyy-MM-dd') = to_char(:operation_date)"
				+ " order by f.std"
				+ " asc";
		Query query = session.createQuery(hql);
		query.setString("origin_airport_iata",toAirportAbb(session,origin_airport_iata));
		query.setString("dest_airport_iata", toAirportAbb(session,dest_airport_iata));
		query.setString("operation_date", operation_date);
		List <F1DynFlight> list = query.list();

				
		return list;		
	}
	
	public static List<F1DynFlight> getFlight(String flight_no, String operation_date){		
		Session session = Connection.getSession("oracle");
	
		String hql = "select f"
				+ " from F1DynFlight f "
				+ " where f.flight_no =:flight_no"
				+ " and to_char(f.operation_date,'yyyy-MM-dd') = to_char(:operation_date)"
				+ " order by f.std"
				+ " asc";
		Query query = session.createQuery(hql);
		query.setString("flight_no", flight_no);
		query.setString("operation_date", operation_date);
		List <F1DynFlight> list = query.list();
		System.out.println();
					
		return list;		
	}
	

	private static String toAirportAbb(Session session, String airport){
		String hql = "select f from F3Airport as f where instr(f.name_xml,:airport)<>0";
		Query query = session.createQuery(hql);
		query.setString("airport", airport);
		List <F3Airport> list = query.list();
		String airportAbb = null;
		for(int i = 0 ;i< list.size();i++){
			if(airport.equals(parseXML(list.get(i).getName_xml()))){
				airportAbb = list.get(i).getAirport_iata();
			}
			
		}
		return airportAbb;		
	}
	
	public static List<F1DynFlight> getFlight() {
		Session session = Connection.getSession("oracle");

		String hql = "select f from F1DynFlight f"
				+ " where f.origin_airport_iata = 'SHA' and to_char(f.std,'yyyy-MM-dd HH24:MI:SS') >= to_char(sysdate, 'yyyy-MM-dd HH24:MI:SS')"
				+ " order by f.std asc ";
		Query query = session.createQuery(hql);
		List<F1DynFlight> list = query.list();
		return list.subList(0, 20);

	}
	
	private static String parseXML(String xml ){
        String result = xml.substring(xml.indexOf("<zh_cn>")+7,xml.indexOf("</zh_cn>"));
        return  result;
    }

}
