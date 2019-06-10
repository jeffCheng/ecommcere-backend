package com.easybuy.shopping.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.easybuy.shopping.repository.ProductRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

	@Autowired
    private ProductRepository repository;

    @Test
    @Transactional
    public void testProduct() {
    	Product p = new Product("C-", 1.1, 1, "/c.jpg", "This is a book");
    	p= repository.save(p);
    	long id = p.getId();
    	Product product = repository.findById(id).orElseThrow(()->new RuntimeException("Product is not found")); 
        assertThat(product.getName().equals("C-"));
    }
}
