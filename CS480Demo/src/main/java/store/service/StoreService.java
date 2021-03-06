package store.service;

import java.util.List;

import store.dao.StoreDao;
import store.domain.Store;
import store.domain.StoreProduct;

public class StoreService {

	private StoreDao storeDao = new StoreDao();

	public List<Store> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return storeDao.findall();
	}

	public void add(Store store)
			throws StoreException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		// check the user name
		Store store1 = storeDao.findIfDuplicate(store);

		if (store1.getName() != null && store1.getName().equals(store.getName()))
			throw new StoreException("This store exists!!!");

		storeDao.add(store);
	}

	public void update(Store store)
			throws StoreException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		// check the user name
		Store store1 = storeDao.findIfDuplicateWithId(store);

		if (store1.getName() != null && store1.getName().equals(store.getName()))
			throw new StoreException("This store exists!!!");

		storeDao.update(store);
	}

	public void delete(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		storeDao.delete(id);
	}

	public Store findById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return storeDao.findById(id);
	}

	public List<Store> searchByRegion_Id(String reg)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		System.out.println("In StoreService:" + reg);
		return storeDao.searchByRegion_Id(reg);
	}

	public List<Store> searchByStore_Name(String reg)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		System.out.println("In StoreService:" + reg);
		return storeDao.searchByStore_Name(reg);
	}

	public List<Store> searchByZip(String reg)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		System.out.println("In StoreService:" + reg);
		return storeDao.searchByZip(reg);
	}

	public List<Store> findByHalalProducts()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return storeDao.findByHalalProducts();
	}

	public List<StoreProduct> getStoreByProduct(String product_name, Integer zipcode)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return storeDao.getStoreByProduct(product_name, zipcode);
	}
}
