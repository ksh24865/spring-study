package com.fileUpDownloadTutorial;

        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.multipart.MultipartFile;
        import javax.swing.filechooser.FileSystemView;
        import java.io.File;

@Controller
public class FileController {
    @PostMapping("/single")
    public String uploadSingle(@RequestParam("files") MultipartFile file) throws Exception {
        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
        String basePath = rootPath + "/" ;
        String filePath = basePath + "/files/" + file.getOriginalFilename();
        File dest = new File(filePath);
        file.transferTo(dest); // 파일 업로드 작업 수행
        return "uploaded";
    }
    @GetMapping("/single")
    public String single(){
        return "single";
    }
//    @GetMapping("/")
//    public String single2(){
//        return "single";
//    }
}
