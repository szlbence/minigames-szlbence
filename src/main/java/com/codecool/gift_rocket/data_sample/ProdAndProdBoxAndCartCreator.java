//package com.codecool.gift_rocket.data_sample;
//
//import com.codecool.gift_rocket.model.Cart;
//import com.codecool.gift_rocket.model.Category;
//import com.codecool.gift_rocket.model.Product;
//import com.codecool.gift_rocket.model.ProductBox;
//import com.codecool.gift_rocket.repository.CartMem;
//import com.codecool.gift_rocket.repository.ProductBoxMem;
//import com.codecool.gift_rocket.repository.ProductMem;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//
//@Component
//public class ProdAndProdBoxAndCartCreator {
//
//    private ProductBoxMem productBoxMem;
//    private ProductMem productMem;
//
//    private CartMem cartMem;
//
//    public void initialize() {
//        Cart cart = new Cart("cart");
//        ProductBox productBox1 = new ProductBox(BigDecimal.valueOf(10), "Big product box", "A red box");
//        ProductBox productBox2 = new ProductBox(BigDecimal.valueOf(10), "Small product box", "A blue box");
//        Product product1 = new Product(BigDecimal.valueOf(40), "Wine", "A bottle of top shelf wine. Tasty!", Category.ALCOHOL);
//        Product product2 = new Product(BigDecimal.valueOf(10), "Chocolate", "A bar of chocolate from our favorite purple brand!", Category.SWEET);
//        Product product3 = new Product(BigDecimal.valueOf(25), "Water Balloons", "Global Warming? Get these water balloons for your kids!", Category.KID);
//        productBox1.addProduct(product1);
//        productBox1.addProduct(product2);
//        productBox2.addProduct(product2);
//        productBox2.addProduct(product3);
//        cartMem.addNewCart(cart);
//        cartMem.addProductBox(productBox1 ,cart.getId());
//        cartMem.addProductBox(productBox2, cart.getId());
//        productMem.add(product1);
//        productMem.add(product2);
//        productMem.add(product3);
//        productBoxMem.addNewProductBox(productBox1);
//        productBoxMem.addNewProductBox(productBox2);
//    }
//
//    @Autowired
//    public ProdAndProdBoxAndCartCreator(ProductBoxMem productBoxMem, ProductMem productMem, CartMem cartMem) {
//        this.productBoxMem = productBoxMem;
//        this.productMem = productMem;
//        this.cartMem = cartMem;
//        initialize();
//    }
//}
