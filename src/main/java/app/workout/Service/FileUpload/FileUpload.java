package app.workout.Service.FileUpload;

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

    /**
     * 이미지 파일 저장
     * @param file MultipartFile
     * @param path storage path
     * */
    public String fileSave(MultipartFile file, String path){

        // 파일 이름 설정
        long date = System.currentTimeMillis();  // 현재시간 가져오기
        String rs = date + file.getName(); // 파일 이름

        // 실제 저장위치 설정
        StringBuilder sb = new StringBuilder();
        String ext = takeExtension(file.getOriginalFilename());  // 확장자 가져오기
        sb.append(CLASSPATH).append(path).append("/").append(date).append(rs).append(".").append(ext);
        String safe_pathname = sb.toString(); // 실제 저장될 위치

        // 저장될 위치 설정
        sb = new StringBuilder();
        sb.append(path).append("/").append(date).append(rs).append(".").append(ext);
        try {
            // 파일저장
            String save_pathname = sb.toString();
            file.transferTo(new File(safe_pathname));
            return save_pathname;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 확장자 가져오기
     * @param fileName ex) file.png
     * @return if the filename is null, return 'png'
     * */
    public String takeExtension(String fileName){
        if(fileName == null) return "png";
        return fileName.split("[.]")[1];
    }

    /**
     * @return CLASSPATH
     * */
    public String getClasspath(){
        return CLASSPATH;
    }


}
