package com.otc.api.web;

import com.otc.api.aop.Token;
import com.otc.api.aop.TokenAll;
import com.otc.api.aop.TokenFinance;
import com.otc.api.domain.YioFile;
import com.otc.api.domain.YioSysUser;
import com.otc.api.domain.YioUser;
import com.otc.api.mapper.YioFileMapper;
import com.otc.api.util.FileUpload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Api("文件")
@RestController
@RequestMapping(value = "/fileUpload")
public class FileUploadController {

    @Value("${FILE_UPLOAD_URL}")
    private String FILE_UPLOAD_URL;

    @Autowired
    private YioFileMapper fileMapper;

    @TokenAll
    @ApiOperation(value = "文件上传", notes = "" ,response=Integer.class)
    @RequestMapping(method = RequestMethod.POST,produces = "application/json")
    public Object list(@RequestParam(value = "file", required = false) MultipartFile file,
                       HttpServletRequest request) throws Exception {
        YioSysUser user = (YioSysUser) request.getAttribute("user");
        String name = FileUpload.upload(FILE_UPLOAD_URL,file,user);
        YioFile yioFile = new YioFile();
        yioFile.setFileUrl(name);
        fileMapper.insert(yioFile);
        return name;
    }
}
