package com.cybersoft.uniclub.controller;

import com.cybersoft.uniclub.Service.Imp.FileServiceImp;
import com.cybersoft.uniclub.Service.Imp.ProductServiceImp;
import com.cybersoft.uniclub.payload.request.InsertProductRequest;
import com.cybersoft.uniclub.payload.respond.BaseRespond;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;

    @Autowired
    private RedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(ProductController.class);
    private Gson gson = new Gson();

    @PostMapping("")
    public ResponseEntity<?> insertProduct(InsertProductRequest productRequest){
        String jsonRequest = gson.toJson(productRequest);
        logger.info(jsonRequest); //những nội dung log luôn chọn info đừng chọn error

        productServiceImp.insertProduct(productRequest);

        BaseRespond baseRespond = new BaseRespond();
        baseRespond.setMessage("OK");

        logger.info(gson.toJson(baseRespond));
        return new ResponseEntity<>(baseRespond, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllProduct(){

        redisTemplate.opsForValue().set("test","Hello redis");

        BaseRespond baseRespond = new BaseRespond();
        baseRespond.setData(productServiceImp.getAllProduct());
        return new ResponseEntity<>(baseRespond,HttpStatus.OK);
    }
}
