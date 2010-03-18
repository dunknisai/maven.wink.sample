package com.ibm.swattools.wink.sample;


import java.util.ArrayList;
import java.util.Iterator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ibm.websphere.objectgrid.NoActiveTransactionException;
import com.ibm.websphere.objectgrid.ObjectGrid;
import com.ibm.websphere.objectgrid.ObjectGridException;
import com.ibm.websphere.objectgrid.ObjectGridManager;
import com.ibm.websphere.objectgrid.ObjectGridManagerFactory;
import com.ibm.websphere.objectgrid.ObjectMap;
import com.ibm.websphere.objectgrid.Session;
import com.ibm.websphere.objectgrid.TransactionAlreadyActiveException;
import com.ibm.websphere.objectgrid.TransactionException;
import com.ibm.websphere.objectgrid.plugins.TransactionCallbackException;
import com.ibm.websphere.objectgrid.query.ObjectQuery;



@Path("/tickets")
public class Tickets {
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JSONArray getTickets(){
		JSONArray array = new JSONArray();
		ObjectGrid grid =  ObjectGridManagerFactory.getObjectGridManager().getObjectGrid("SAMPLE");
		try {
			Session s  = grid.getSession();
			s.begin();
			ObjectQuery oq = s.createObjectQuery("select o from Ticket o");
			Iterator<Ticket> i = oq.getResultIterator();
			while (i.hasNext()){
				JSONObject json = new JSONObject(i.next());
				array.put(json);
			}
			s.commit();
		} catch (TransactionAlreadyActiveException e) {
			e.printStackTrace();
		} catch (TransactionCallbackException e) {
			e.printStackTrace();
		} catch (TransactionException e) {
			e.printStackTrace();
		} catch (NoActiveTransactionException e) {
			e.printStackTrace();
		} catch (ObjectGridException e) {
			e.printStackTrace();
		}
		
		return array;
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public JSONObject getTicket(@PathParam("id") int id){
		ObjectGrid grid =  ObjectGridManagerFactory.getObjectGridManager().getObjectGrid("SAMPLE");
		JSONObject answer = null;
		try {
			Session s  = grid.getSession();
			s.begin();
			ObjectQuery oq = s.createObjectQuery("select o from Ticket o where o._id = ?1");
			oq.setParameter(1, id);
			Ticket t = (Ticket)oq.getSingleResult();
			answer = new JSONObject(t);
			s.commit();
		} catch (TransactionAlreadyActiveException e) {
			e.printStackTrace();
		} catch (TransactionCallbackException e) {
			e.printStackTrace();
		} catch (TransactionException e) {
			e.printStackTrace();
		} catch (NoActiveTransactionException e) {
			e.printStackTrace();
		} catch (ObjectGridException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
}
