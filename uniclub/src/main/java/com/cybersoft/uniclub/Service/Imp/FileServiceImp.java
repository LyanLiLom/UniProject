package com.cybersoft.uniclub.Service.Imp;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileServiceImp {
    void saveFile(MultipartFile file);
    Resource load(String file);
}
