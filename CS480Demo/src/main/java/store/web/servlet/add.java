package store.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.Region;

import region.service.RegionService;
import store.domain.Store;
import store.service.StoreException;
import store.service.StoreService;

/**
 * Servlet implementation class add
 */
@WebServlet(name = "addStore", urlPatterns = { "/addStore" })
public class add extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public add() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		StoreService storeservice = new StoreService();
		RegionService regionservice = new RegionService();
		Store store = new Store();

		Map<String, String[]> paramMap = request.getParameterMap();
		List<String> info = new ArrayList<String>();

		for (String name : paramMap.keySet()) {
			String[] values = paramMap.get(name);
			info.add(values[0]);
			System.out.println(name + ": " + Arrays.toString(values));
		}

		int id = 0;

		if (info.size() > 0) {
			id = Integer.parseInt(info.get(0));
		}

		try {
			if (id != 0) {
				store = storeservice.findById(id);
			}

			List<region.domain.Region> listregion = regionservice.findall();

			request.setAttribute("listregion", listregion);
			request.setAttribute("store", store);
			request.getRequestDispatcher("/jsps/store/add.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", e.getMessage());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", e.getMessage());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", e.getMessage());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		StoreService storeservice = new StoreService();
		RegionService regionservice = new RegionService();
		Map<String, String[]> paramMap = request.getParameterMap();
		Store store = new Store();
		List<String> info = new ArrayList<String>();
		List<region.domain.Region> listregion = null;

		for (String name : paramMap.keySet()) {

			String[] values = paramMap.get(name);
			info.add(values[0]);
			System.out.println(name + ": " + Arrays.toString(values));
		}

		if (info.get(0) != "") {
			store.setId(Integer.parseInt(info.get(0)));
		} else {
			store.setId(0);
		}

		store.setName(info.get(1));
		store.setAddress(info.get(2));
		store.setZipcode(Integer.parseInt(info.get(3)));
		store.setRegion_id(Integer.parseInt(info.get(4)));

		try {
			listregion = regionservice.findall();

			if (store.getId() == 0) {
				storeservice.add(store);
			} else {
				storeservice.update(store);
			}

			request.getRequestDispatcher("findAllStore").forward(request, response);
		} catch (ClassNotFoundException | StoreException e) {
			// TODO Auto-generated catch block

			request.setAttribute("listregion", listregion);
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("store", store);
			request.getRequestDispatcher("/jsps/store/add.jsp").forward(request, response);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", e.getMessage());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", e.getMessage());
		}
	}

}
