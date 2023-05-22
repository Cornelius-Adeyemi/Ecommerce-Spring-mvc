package com.adebisi.task_seven.service_interface;

import com.adebisi.task_seven.DTO.ProductDTO;
import com.adebisi.task_seven.model.Product;
import com.adebisi.task_seven.repository.ProductRepo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {







     boolean findByProductName(ProductDTO productDTO);

     List<Product> findall();


     Product findProductById(Long id);

     Product saveProduct(ProductDTO productDTO);

     Product saveProduct(Product product);

     void deleteProduct(Long id);

     Page<Product> findAllPaginatedProduct(int pageNo, int pageSize);

}
