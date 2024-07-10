package com.cybersoft.uniclub.controller;

import com.cybersoft.uniclub.Service.Imp.LoginServiceImp;
import com.cybersoft.uniclub.payload.request.LoginRequest;
import com.cybersoft.uniclub.payload.respond.BaseRespond;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin //cho phép tất cả các đường dẫn khác ngoài localhost 8080 đi vào
public class LoginController {

    /**
     *Do password lưu trữ trong database là chuỗi mã hóa dạng BCrypt cho nên không thể dùng password
     * như điều kiện where
     * Bước 1: Viết câu truy vấn lấu dữ liệu dựa trên username
     * Bước 2: Lấy dữ liệu password trả ra từ bước 1 và kiểm tra xem password lưu trữ trong database
     * với password người dùng truyền lên.
     * Bước 3: Nêếu 2 password match với nhau thì sẽ tạo ra token, nếu không giống thì báo đăng nhập thất bại
     *
     */

    @Autowired
    LoginServiceImp loginServiceImp;
    @PostMapping("")
    public ResponseEntity<?> login( @Valid LoginRequest loginRequest){

        System.out.println("Kiem tra login");
        String token = loginServiceImp.checkLogin(loginRequest.getUsername(),loginRequest.getPassword());

        BaseRespond baseRespond = new BaseRespond();
        baseRespond.setStatusCode(token.trim().length() > 0 ? 200 : 401);
        baseRespond.setData(token.trim().length() > 0 ? token : "Dang nhap that bai");


        return new ResponseEntity<>(baseRespond, HttpStatus.OK);
    }
}
