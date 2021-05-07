package com.api.book.bootrestbook.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
    
//public final String UPLOAD_DIR="E:\\spring boot\\bootrestbook\\src\\main\\resources\\static\\image";
public final String UPLOAD_DIR= new ClassPathResource("static/image/").getFile().getAbsolutePath();

public FileUploadHelper()throws IOException
{

}

public boolean uploadFile(MultipartFile multipartfile)
{
boolean f=false;
try {
    
    //first type
    // InputStream is = multipartfile.getInputStream();
    // byte data[] = new byte[is.available()];
    // is.read(data);

    //write
    // FileOutputStream fos =new FileOutputStream(UPLOAD_DIR+"\\"+multipartfile.getOriginalFilename());
    // fos.write(data);
    // fos.flush();
    // fos.close();
    // f=true;

Files.copy(multipartfile.getInputStream(),
Paths.get(UPLOAD_DIR+File.separator+multipartfile.getOriginalFilename()) , 
StandardCopyOption.REPLACE_EXISTING);
 f=true;


} catch (Exception e) {
    
    
e.printStackTrace();
}

return f;
}

}
