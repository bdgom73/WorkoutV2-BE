package app.workout.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.Objects;


@Component
@PropertySource("classpath:variable/files.properties")
public class FileUpload {

    @Value("${FILE_CLASSPATH}")
    private String CLASSPATH;

    // 파일 저장
    public String fileSave(MultipartFile file, String path){
        // 현재시간 가져오기
        long date = System.currentTimeMillis();
        String rs = date + file.getName();
        StringBuilder sb = new StringBuilder();
        // 확장자 가져오기
        String ext = takeExtension(file.getOriginalFilename());
        sb.append(CLASSPATH).append(path).append("/").append(date).append(rs).append(".").append(ext);
        String safe_pathname = sb.toString(); // 실제 저장될 위치
        sb = new StringBuilder();
        sb.append(path).append("/").append(date).append(rs).append(".").append(ext);

        try {
            String save_pathname = sb.toString(); // 가져올 위치
            file.transferTo(new File(safe_pathname)); // 파일저장
            return save_pathname;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String takeExtension(String fileName){
        if(fileName == null) return null;
        return fileName.split("[.]")[1];
    }

    public String getClasspath(){
        return CLASSPATH;
    }


}
