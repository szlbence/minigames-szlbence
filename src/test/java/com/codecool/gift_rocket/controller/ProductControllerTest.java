package com.codecool.gift_rocket.controller;

import com.codecool.gift_rocket.model.Category;
import com.codecool.gift_rocket.model.Product;
import com.codecool.gift_rocket.service.ShopService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired private MockMvc mvc;

    @MockBean private ShopService shopService;

    private Product product = new Product(new BigDecimal(40), "name", "description", Category.KID);

    private ObjectMapper mapper = new ObjectMapper();


    private UUID productId = product.getId();




    @Test
    public void getProducts_returnJsonArray() throws Exception {
        String productString = mapper.writeValueAsString(product);

        when(shopService.getAllProducts())
                .thenReturn(List.of(product));

        RequestBuilder request = MockMvcRequestBuilders.get("/products")
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request).andExpect(status().isOk())
                .andExpect(content().json("[" + productString + "]")).andReturn();
    }

    @Test
    public void givenProducts_addProducts_returnJson() throws Exception {
        String productString = mapper.writeValueAsString(product);

        RequestBuilder request = MockMvcRequestBuilders.post("/products")
                .accept(MediaType.APPLICATION_JSON)
                .content(productString)
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void useFindMethod_usingUUID_returnProductWithMatchingUUID() throws Exception {
        String productString = mapper.writeValueAsString(product);

        when(shopService.findProduct(productId)).thenReturn(product);

        RequestBuilder request = MockMvcRequestBuilders.get("/products/" + productId)
                .accept(MediaType.APPLICATION_JSON);



        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(productString))
                .andReturn();

    }

    @Test
    public void removeProduct_usingUUID_returnEmptyList() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.delete("/products/" + productId)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(productId))
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void getProduct_usingCategory_returnProductWithMatchingCategory() throws  Exception {
        String productString = mapper.writeValueAsString(product);
        Category category = Category.KID;

        when(shopService.getProductsByCategory(category)).thenReturn(List.of(product));

        RequestBuilder request = MockMvcRequestBuilders.get("/products/category/" + category)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[" + productString + "]"))
                .andReturn();

    }
}
