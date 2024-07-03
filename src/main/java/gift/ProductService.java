package gift;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductService {
  private final ProductDao productDao;
  public ProductService(ProductDao productDao){
    this.productDao=productDao;
  }

  public List<Product> getAllProducts() {
    List<Product> productDtos = productDao.selectAllProducts();
    return productDtos;
  }

  public Product getProductById(Long id) {
    return productDao.selectProduct(id);

  }

  public Product addProduct(@Valid Product product) throws MethodArgumentNotValidException{
    productDao.insertProduct(product);
    return product;
  }
  
  public Product updateProduct(Long id, @Valid Product updatedProduct) throws MethodArgumentNotValidException {
    Product existingProduct = productDao.selectProduct(id);
    if (existingProduct!=null){
      updatedProduct.setId(id);
      productDao.updateProduct(updatedProduct);
    }
    return existingProduct;
  }

  public Product deleteProduct(@PathVariable Long id) {
    Product existingProduct = productDao.selectProduct(id);
    if (existingProduct != null) {
      productDao.deleteProduct(id);
    }
    return existingProduct;
  }

}
