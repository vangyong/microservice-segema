package cn.segema.cloud.system.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.cloud.common.constants.ApiConstant;
import cn.segema.cloud.common.constants.CommonConstant;
import cn.segema.cloud.common.utils.DateTimeUtil;
import cn.segema.cloud.system.domain.Region;
import cn.segema.cloud.system.repository.RegionRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 行政地区Controller
 */
@Api(tags="行政地区")
@RestController
@RequestMapping(value = ApiConstant.API_VERSION + "/region")
public class RegionController {

	@Autowired
	private RegionRepository regionRepository;

	@ApiOperation(value = "新增地区", notes = "新增地区")
	@PostMapping
	public ResponseEntity add(@RequestBody Region region) {
		if (region.getRegionId() == null) {
			Region oldRegion = regionRepository.findRegionByCode(region.getRegionCode());
			if(oldRegion!=null) {
				return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
			}else {
				region.setRegionId(BigInteger.valueOf(new Long(region.getRegionCode())));
				region.setCreateTime(DateTimeUtil.getCurrentUnixTime());
				regionRepository.save(region);
			}
		}
		return new ResponseEntity(region,HttpStatus.OK);
	}
//
//	@ApiOperation(value = "修改地区", notes = "修改地区")
//	@PutMapping
//	public Region edit(Region region) {
//		Region oldRegion = regionRepository.findOne(region.getRegionId());
//		BeanUtils.copyProperties(region, oldRegion);
//		regionRepository.save(region);
//		return region;
//	}
//
//	@ApiOperation(value = "删除地区", notes = "删除地区")
//	@DeleteMapping("/{optionId}")
//	public Region delete(@PathVariable BigInteger optionId) {
//		Optional<Region> region = regionRepository.findOne(optionId);
//		if(region!=null) {
//			regionRepository.delete(region.get());
//		}
//		return region.get();
//	}

	@ApiOperation(value = "根据id获取地区", notes = "根据id获取地区")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "regionId", value = "地区id", required = true, paramType = "path") })
	@GetMapping("/{regionId}")
	public ResponseEntity findById(@PathVariable BigInteger regionId) throws Exception {
		Region region = regionRepository.findOne(regionId);
		return new ResponseEntity(region,HttpStatus.OK);
	}

	@ApiOperation(value = "根据国家编码获取省份", notes = "根据国家编码获取省份")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countryCode", value = "国家编码", required = true, paramType = "path") })
	@GetMapping("/province/{countryCode}")
	public ResponseEntity findProvinceList(@PathVariable String countryCode) {
		if(StringUtils.isEmpty(countryCode)) {
			countryCode = CommonConstant.COUNTRY_CODE_CHINA;
		}
		List<Region> regionList = regionRepository.findProvinceList(countryCode);
		return new ResponseEntity(regionList,HttpStatus.OK);
	}
	
	@ApiOperation(value = "根据地区编码获取地市", notes = "根据地区编码获取地市")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "regionCode", value = "地区编码", required = true, paramType = "path") })
	@GetMapping("/city/{regionCode}")
	public List<Region> findCityList(@PathVariable String regionCode) {
		List<Region> regionList = regionRepository.findCityList(regionCode);
		return regionList;
	}

	
	@ApiOperation(value = "根据地区编码获取区县", notes = "根据地区编码获取区县")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "regionCode", value = "地区编码", required = true, paramType = "path") })
	@GetMapping("/county/{regionCode}")
	public List<Region> findCountyList(@PathVariable String regionCode) {
		List<Region> regionList = regionRepository.findCountyList(regionCode);
		return regionList;
	}
	
	@ApiOperation(value = "根据地区编码获取乡镇", notes = "根据地区编码获取乡镇")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "regionCode", value = "地区编码", required = true, paramType = "path") })
	@GetMapping("/towns/{regionCode}")
	public List<Region> findTownsList(@PathVariable String regionCode) {
		List<Region> regionList = regionRepository.findTownsList(regionCode);
		return regionList;
	}
	
	@ApiOperation(value = "根据地区编码获取下级", notes = "根据地区编码获取下级")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "parentCode", value = "地区编码", required = true, paramType = "path") })
	@GetMapping("/child/{parentCode}")
	public List<Region> findChildList(@PathVariable String parentCode) {
		List<Region> regionList = regionRepository.findRegionByParentCode(parentCode);
		return regionList;
	}
	

}
