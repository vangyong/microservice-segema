package cn.segema.cloud.system.controller;

import java.math.BigInteger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.cloud.common.constants.ApiConstant;
import cn.segema.cloud.common.utils.DateTimeUtil;
import cn.segema.cloud.common.utils.IdGeneratorUtil;
import cn.segema.cloud.system.domain.Certificate;
import cn.segema.cloud.system.repository.CertificateRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="证件信息")
@RestController
@RequestMapping(value = ApiConstant.API_VERSION + "/certificate")
public class CertificateController {

	@Autowired
	private CertificateRepository certificateRepository;

	@ApiOperation(value = "新增证件", notes = "新增证件")
	@PostMapping
	public ResponseEntity add(@RequestBody Certificate certificate) {
		if(StringUtils.isEmpty(certificate.getCertificateType())) {
			return new ResponseEntity("Type can't be null",HttpStatus.BAD_REQUEST);
		}
		if(StringUtils.isEmpty(certificate.getCertificateCode())) {
			return new ResponseEntity("Code can't be null",HttpStatus.BAD_REQUEST);
		}
		if (certificate.getCertificateId() == null) {
		    certificate.setCertificateId(BigInteger.valueOf(IdGeneratorUtil.generateSnowFlakeId()));
		}
		certificate.setCreateTime(DateTimeUtil.getCurrentUnixTime());
		certificateRepository.save(certificate);
		return new ResponseEntity(certificate,HttpStatus.OK);
	}

	@ApiOperation(value = "修改证件", notes = "修改证件")
	@PutMapping
	public ResponseEntity edit(@RequestBody Certificate certificate) {
	    Certificate oldCertificate = certificateRepository.findOne(certificate.getCertificateId());
		if(oldCertificate!=null) {
		    BeanUtils.copyProperties(certificate, oldCertificate,"createTime");
	        certificateRepository.save(oldCertificate);
	        return new ResponseEntity(oldCertificate,HttpStatus.OK);
		}else {
		    return new ResponseEntity("can't find Certificate",HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "根据id获取证件", notes = "根据id获取证件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "certificateId", value = "租户id", required = true, paramType = "path") })
	@GetMapping("/{certificateId}")
	public ResponseEntity findById(@PathVariable BigInteger certificateId) throws Exception {
		Certificate tenant = certificateRepository.findOne(certificateId);
		return new ResponseEntity(tenant,HttpStatus.OK);
	}
	
	@ApiOperation(value = "根据业务数据id、类型获取证件", notes = "根据业务数据id、类型获取证件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "businessId", value = "业务数据id", required = true, paramType = "path"),
            @ApiImplicitParam(name = "type", value = "类型", required = true, paramType = "path") })
    @GetMapping("/business/{businessId}/{type}")
    public ResponseEntity findByBusinessAndType(@PathVariable BigInteger businessId,@PathVariable String type) throws Exception {
        Certificate certificate = certificateRepository.findByBusinessAndType(businessId,type);
        return new ResponseEntity(certificate,HttpStatus.OK);
    }
	

	@ApiOperation(value = "分页获取证件", notes = "分页获取证件")
	@ApiImplicitParams({@ApiImplicitParam(name = "page", value = "当前页", required = true, paramType = "query"),
		@ApiImplicitParam(name = "limit", value = "每页数", required = true, paramType = "query"),
		@ApiImplicitParam(name = "sort", value = "排序列", required = false, paramType = "query")})
	@GetMapping("/page")
	public ResponseEntity findByPage(@RequestParam(defaultValue ="1") int page, 
			@RequestParam(defaultValue = "20") int limit, 
			@RequestParam(defaultValue = "create_time") String sort,Certificate tenant) {
		Sort sortOrder = new Sort(Sort.Direction.DESC, sort);
		Pageable pageable = new  PageRequest(page - 1, limit, sortOrder);
		Page<Certificate> optionPage = certificateRepository.findByPage(tenant,pageable);
		return new ResponseEntity(optionPage,HttpStatus.OK);
	}

}
