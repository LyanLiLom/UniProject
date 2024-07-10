package com.cybersoft.uniclub.Service;

import com.cybersoft.uniclub.Service.Imp.LoginServiceImp;
import com.cybersoft.uniclub.entity.UserEntity;
import com.cybersoft.uniclub.payload.respond.RoleRespond;
import com.cybersoft.uniclub.repository.UserRepository;
import com.cybersoft.uniclub.utils.JWTUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceImp {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    private Gson gson = new Gson();

    @Override
    public String checkLogin(String username,String password) {
        String token = "";
        UserEntity userEntity = userRepository.findByEmail(username);
        if(passwordEncoder.matches(password,userEntity.getPassword())){

            RoleRespond roleRespond = new RoleRespond();
            roleRespond.setName(userEntity.getRole().getName());

            String roles = gson.toJson(roleRespond);
            token = jwtUtils.createToken(roles);
        }

        return token;
    }
}
