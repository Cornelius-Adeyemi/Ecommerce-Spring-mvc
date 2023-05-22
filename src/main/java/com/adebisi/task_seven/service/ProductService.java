package com.adebisi.task_seven.service;

import com.adebisi.task_seven.DTO.ProductDTO;
import com.adebisi.task_seven.model.Product;
import com.adebisi.task_seven.repository.ProductRepo;
import com.adebisi.task_seven.service_interface.ProductServiceInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceInterface {

    ProductRepo productRepo;

    public ProductService(ProductRepo productRepo){

        this.productRepo=productRepo;
    }



    public boolean findByProductName(ProductDTO productDTO){
        Product product = new Product(productDTO);

        Optional<Product> checker = productRepo.findProductByName(productDTO.getName());

        if(checker.isPresent()){
            return false;
        }

        return true;
    }

    public List<Product> findall(){
        return productRepo.findAll();
    }


    public Product findProductById(Long id){
        Optional<Product> product = productRepo.findById(id);

        return product.get();
    }

    public Product saveProduct(ProductDTO productDTO){
        Product product = new Product(productDTO);
         return productRepo.save(product);

    }

    public Product saveProduct(Product product){

        return productRepo.save(product);

    }


    public void deleteProduct(Long id){
        productRepo.deleteById(id);
    }

   public Page<Product> findAllPaginatedProduct(int pageNo, int pageSize){

       Pageable pageable = PageRequest.of(pageNo-1,pageSize);
       return productRepo.findAll(pageable);

   }

}
