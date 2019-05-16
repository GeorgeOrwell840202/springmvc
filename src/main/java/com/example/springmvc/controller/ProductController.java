package com.example.springmvc.controller;

import com.example.springmvc.model.Product;
import com.example.springmvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/")
    public String index(){
        return  "index";
    }

    @RequestMapping("/product/add")
    public String createProduct(Model model){
        model.addAttribute("product", new Product());
        return "edit";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveProduct(Product product){
        productRepository.save(product);
        return "redirect:/products";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getAllProducts(Model model){

        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @RequestMapping("/product/edit/{id}")
    public String editProduct(Model model, @PathVariable String id){
        Product product = productRepository.getOne(id);
        model.addAttribute("product", product);
        return "edit";
    }

    @RequestMapping("/product/delete/{id}")
    public String deleteProduct(Model model, @PathVariable String id){
        productRepository.deleteById(id);
        return "redirect:/products";
    }

}
