package product.data;

import java.util.List;

import product.model.Product;

public interface ProductServiceImpl {

	Product createProduct (Product product);
	
	Product getProductById (Long id);
	
	List<Product> getAllProduct ();
	
	Product editProduct (Long id, Product product);
	
	void deleteProduct (Product product);
	
}
