package com.ibm.swattools.wink.sample;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.ibm.websphere.objectgrid.NoActiveTransactionException;
import com.ibm.websphere.objectgrid.ObjectGrid;
import com.ibm.websphere.objectgrid.ObjectGridException;
import com.ibm.websphere.objectgrid.ObjectGridManager;
import com.ibm.websphere.objectgrid.ObjectGridManagerFactory;
import com.ibm.websphere.objectgrid.ObjectMap;
import com.ibm.websphere.objectgrid.Session;
import com.ibm.websphere.objectgrid.TransactionAlreadyActiveException;
import com.ibm.websphere.objectgrid.TransactionException;
import com.ibm.websphere.objectgrid.UndefinedMapException;
import com.ibm.websphere.objectgrid.plugins.TransactionCallbackException;

/**
 * Servlet implementation class GridServlet
 */
public class GridServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectGrid _og;
	
    /**
     * Default constructor. 
     */
    public GridServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init grid");
		try {
			_og = ObjectGridManagerFactory.getObjectGridManager().createObjectGrid("SAMPLE", Thread.currentThread().getContextClassLoader().getResource("META-INF/objectgrid.xml"));
		} catch (ObjectGridException e) {
			e.printStackTrace();
		}
		System.out.println("grid is up: " + _og);
		
		//populate the grid with stuff
		Ticket t1 = new Ticket(0, "Morpheus", "Zion", "male", 40);
		Ticket t2 = new Ticket(1, "Trinity", "Zion", "female", 32);
		Ticket t3 = new Ticket(2, "The Oracle", "Matrix", "female", 99);
		Ticket t4 = new Ticket(3, "Agent Smith", "Matrix", "male", 01);
		try {
			Session s = _og.getSession();
			s.begin();
			ObjectMap map = s.getMap("Ticket");
			map.put("0", t1);
			map.put("1", t2);
			map.put("2", t3);
			map.put("3", t4);
			s.commit();
		} catch (TransactionAlreadyActiveException e) {
			e.printStackTrace();
		} catch (TransactionCallbackException e) {
			e.printStackTrace();
		} catch (TransactionException e) {
			e.printStackTrace();
		} catch (UndefinedMapException e) {
			e.printStackTrace();
		} catch (NoActiveTransactionException e) {
			e.printStackTrace();
		} catch (ObjectGridException e) {
			e.printStackTrace();
		}
		System.out.println("grid packed");
		
	}

}
