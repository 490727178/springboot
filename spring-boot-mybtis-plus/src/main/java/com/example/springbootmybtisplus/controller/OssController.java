package com.example.springbootmybtisplus.controller;

import com.example.springbootmybtisplus.config.FileTypeEnum;
import com.example.springbootmybtisplus.util.AliyunOssUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件服务接口
 * @author：zengrenshang
 * @date：2020年5月17日
 */
@RestController
@RequestMapping("web/Oss")
public class OssController {

    @Autowired
    private AliyunOssUtils ossUtils;

    /**
     * 上传文件
     * @author zengrenshang
     * @param files
     * @return List<String>
     */
    @PostMapping(value = "/uploadFiles")
//    @SneakyThrows(CommonRuntimeException.class)
    public List<String> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        return ossUtils.uploadFile(files, "imgs", FileTypeEnum.IMG);
    }


    /**
     * 下载文件
     * @author zengrenshang
     * @param fileName
     */
    @SneakyThrows
    @GetMapping(value = "/downloadFile")
    public void downloadFiles(String fileName, HttpServletResponse response) {
        InputStream is = ossUtils.downloadFile(fileName);
        if (is ==null){
            response.reset();
            response.setStatus(200);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            Map<String, String> map = new HashMap<>();
            map.put("sta", "09");
            map.put("message", "文件不存在");
            writer.write(map.toString());
        }else {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            OutputStream os = response.getOutputStream();
            int ch = 0;
            while (-1 != (ch = is.read())){
                baos.write(ch);
            }
            os.write(baos.toByteArray());
            is.close();
            baos.close();
            os.close();
        }
    }
}
