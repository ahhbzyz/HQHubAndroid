package com.HQHub.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.HQHub.Util.Connection;
import com.HQHub.pojo.F1DynFlight;
import com.HQHub.pojo.RRdt;
import com.HQHub.pojo.RSta;

public class TrainQueryDAO {

	public static List<RRdt> getTrain(String train_no, String run_date) {
		Session session = Connection.getSession("oracle");

		String hql = "select r"
				+ " from RRdt r "
				+ " where r.arrive_train_no =:train_no"
				+ " and to_char(r.run_date,'yyyy-MM-dd') = to_char(:run_date)"
				+ " order by r.estimated_depart_time"
				+ " asc";
		Query query = session.createQuery(hql);
		query.setString("train_no", train_no);
		query.setString("run_date", run_date);
		List<RRdt> list = query.list();

		return list;

	}

	public static List<RRdt> getTrain(String startstation,String terminalstation, String run_date){
		Session session = Connection.getSession("oracle");
			
		String hql = "select r"
				+ " from RRdt r "
				+ " where r.startstation =:startstation"
				+ " and r.terminalstation=:terminalstation"
				+ " and to_char(r.run_date,'yyyy-MM-dd') = to_char(:run_date)"
				+ " order by r.estimated_depart_time"
				+ " asc";
		Query query = session.createQuery(hql);

		query.setString("startstation", "AOH");
		query.setString("terminalstation", "VNP");
		query.setString("run_date", "2015-05-28");
		
		List <RRdt> list = query.list();

		return list;
		
	}
	
	private static String toStationAbb(Session session, String station){

		String hql = "select r from RSta as r where instr(r.sta_name,:station)<>0";
		Query query = session.createQuery(hql);
		query.setString("station", station);
		List <RSta> list = query.list();
		String stationAbb = null;
		for(int i = 0 ;i< list.size();i++){
			if(station.equals(parseXML(list.get(i).getSta_name()))){
				stationAbb = list.get(i).getSta();
			}
			
		}
		return stationAbb;		
	}
	
	public static List<RRdt> getTrain() {
		Session session = Connection.getSession("oracle");

		String hql = "select r"
				+ " from RRdt r"
				+ " where to_char(r.check_time,'yyyy-MM-dd HH24:MI:SS') >= to_char(sysdate, 'yyyy-MM-dd HH24:MI:SS')"
				+ " order by r.check_time asc";
		Query query = session.createQuery(hql);
		List<RRdt> list = query.list();
		return list.subList(0, 20);

	}
	
	
	private static String parseXML(String xml ){
        String result = xml.substring(xml.indexOf("<zh_cn>")+7,xml.indexOf("</zh_cn>"));
        return  result;
    }
}
