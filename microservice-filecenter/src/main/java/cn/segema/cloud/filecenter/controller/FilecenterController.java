package cn.segema.cloud.filecenter.controller;

import java.math.BigDecimal;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import cn.segema.cloud.filecenter.domain.Filecenter;
import cn.segema.cloud.filecenter.repository.FilecenterRepository;
import cn.segema.cloud.filecenter.util.FileUtil;

/**
 * 文件中心接口
 */
@Controller
@RequestMapping(value = "/filecenter")
public class FilecenterController {
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	private FilecenterRepository filecenterRepository;
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${filecenter.local.directory}")
	private String filecenterLocalDirectory;
	
	@RequestMapping(value="/uploadfile", method = RequestMethod.GET)
    public String uploadFile() {
        return "uploadfile";
    }

    //处理文件上传
    @RequestMapping(value="/uploadfile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file,
            HttpServletRequest request) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        String localDirectory = filecenterLocalDirectory;
        try {
        		String fileId = UUID.randomUUID().toString();
            FileUtil.uploadFile(file.getBytes(), localDirectory+fileId+"/", fileName);
            Filecenter filecenter = new Filecenter();
            filecenter.setFileId(fileId);
            filecenter.setFileName(fileName);
            filecenter.setFileType(contentType);
            filecenter.setRelativePath(fileId+"/"+fileName);
            filecenter.setSuffix(fileName.substring(fileName.lastIndexOf(".")));
            filecenter.setAbsolutePath(localDirectory+fileId+"/"+fileName);
            filecenter.setTotalSize(new BigDecimal(file.getSize()));
            filecenter.setFileContent(file.getBytes());
            filecenterRepository.save(filecenter);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "uploadimg success";
    }
    
    /**
	 * 下载
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "download/{id}")
	public void download(@PathVariable("id") String id,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Filecenter filecenter  = filecenterRepository.findOne(id);
		String storeName = filecenter.getAbsolutePath();
		String fileName = filecenter.getFileName();
		
		String contentType = "application/x-download";
		FileUtil.download(request, response, storeName, contentType,fileName);
	}

}
