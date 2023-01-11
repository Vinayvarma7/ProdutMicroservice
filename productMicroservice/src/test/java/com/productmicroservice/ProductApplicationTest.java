package com.productmicroservice;

import com.productmicroservice.controller.ProductController;
import com.productmicroservice.service.ProductService;
import com.productmicroservice.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ProductApplicationTest {

    private MockMvc mockMvc;


    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    Product PRODUCT1 = new Product(1,"electronics","Samsung Phone","Mobile",80000,"Samsung 20plus");
   Product PRODUCT2 = new Product(2,"Shoes","Nike Air","Footware",7000,"Flexible");
   Product PRODUCT3= new Product(3,"skincare","sunscreen","cosmetics",900,"Useful in sun");

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }
    @Test
    public void getAllProducts_success() throws Exception {
        List<Product> records = new ArrayList<>(Arrays.asList(PRODUCT1 , PRODUCT2, PRODUCT3));

        Mockito.when(productService.getAllProducts()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/product/view")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)));
    }
}



