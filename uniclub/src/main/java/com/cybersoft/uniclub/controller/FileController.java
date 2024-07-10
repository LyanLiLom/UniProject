package com.cybersoft.uniclub.controller;

import com.cybersoft.uniclub.Service.FileService;
import com.cybersoft.uniclub.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;
    @GetMapping("/{name:.+}")
    public ResponseEntity<?> getFile(@PathVariable String name){
        Resource resource = fileService.load(name);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                        name + "\"").body(resource);
    }

    @GetMapping("/demojava8")
    public ResponseEntity<?> demojava8(){
        ProductDTO productDTO = new ProductDTO();
        Optional<ProductDTO> dto = Optional.ofNullable(productDTO);
        Optional<ProductDTO> dto2 = dto.map(data -> {
           data.setImage("abc.png");
           return data;
        });

        dto2.ifPresentOrElse(value -> System.out.println("Có giá trị " + dto2.get().getImage()),
                () -> System.out.println("Lỗi không có giá trị")
                );

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/demoLambdaExpression")
    public ResponseEntity<?> demolambda(){
        MyInterface sum = (x,y) -> x + y;
        MyInterface difference = ((x, y) -> x - y);

        double resurl = sum.value(5,3);
        double resurl2 = difference.value(6,2);

        System.out.println("Tổng " + resurl);
        System.out.println("Hiệu " + resurl2);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    interface MyInterface{
        double value(int x, int y);
    }

}
