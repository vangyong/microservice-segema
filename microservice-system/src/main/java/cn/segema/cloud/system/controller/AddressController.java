package cn.segema.cloud.system.controller;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.cloud.common.constants.ApiConstant;
import cn.segema.cloud.common.utils.DateTimeUtil;
import cn.segema.cloud.common.utils.IdGeneratorUtil;
import cn.segema.cloud.system.domain.Address;
import cn.segema.cloud.system.domain.Region;
import cn.segema.cloud.system.repository.AddressRepository;
import cn.segema.cloud.system.repository.RegionRepository;
import cn.segema.cloud.system.vo.AddressVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "地址信息")
@RestController
@RequestMapping(value = ApiConstant.API_VERSION + "/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RegionRepository regionRepository;

    @ApiOperation(value = "新增地区", notes = "新增地区")
    @PostMapping
    public ResponseEntity add(@RequestBody Address address) {
        if (address.getAddressId() == null) {
            address.setAddressId(BigInteger.valueOf(IdGeneratorUtil.generateSnowFlakeId()));
            address.setCreateTime(DateTimeUtil.getCurrentUnixTime());
            addressRepository.save(address);
        }
        return new ResponseEntity(address, HttpStatus.OK);
    }

    @ApiOperation(value = "修改地址", notes = "修改地址")
    @PutMapping
    public Address edit(@RequestBody Address address) {
        Address oldAddress = addressRepository.findOne(address.getAddressId());
        try {
            BeanUtils.copyProperties(address, oldAddress);
            addressRepository.save(oldAddress);
        } catch (Exception e) {
             e.printStackTrace();
             throw new RuntimeException();
        }
        return oldAddress;
    }

    @ApiOperation(value = "删除地址", notes = "删除地址")
    @DeleteMapping("/{addressId}")
    public Address delete(@PathVariable BigInteger addressId) {
        Address address = addressRepository.findOne(addressId);
        if (address != null) {
            addressRepository.delete(address);
        }
        return address;
    }

    @ApiOperation(value = "根据id获取地址", notes = "根据id获取地址")
    @ApiImplicitParams({@ApiImplicitParam(name = "addressId", value = "地址id", required = true, paramType = "path")})
    @GetMapping("/{addressId}")
    public ResponseEntity findById(@PathVariable BigInteger addressId) throws Exception {
        Address address = addressRepository.findOne(addressId);
        return new ResponseEntity(address, HttpStatus.OK);
    }

    @ApiOperation(value = "根据id获取详细地址", notes = "根据id获取详细地址")
    @ApiImplicitParams({@ApiImplicitParam(name = "addressId", value = "地址id", required = true, paramType = "path")})
    @GetMapping("/detail/{addressId}")
    public ResponseEntity findDetailById(@PathVariable BigInteger addressId) throws Exception {
        Address address = addressRepository.findOne(addressId);
        if (address!=null) {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(address, addressVO);
            String regionCode = address.getRegionCode();
            String provinceCode = regionCode.substring(0, 2) + "0000000";
            String cityCode = regionCode.substring(0, 4) + "00000";
            String countyCode = regionCode.substring(0, 6) + "000";
            String townsCode = regionCode;
            Region province = regionRepository.findRegionByCode(provinceCode);
            if (province != null) {
                addressVO.setCountryCode(province.getCountryCode());
                addressVO.setProvinceCode(province.getRegionCode());
                addressVO.setProvinceName(province.getRegionName());
            }
            Region city = regionRepository.findRegionByCode(cityCode);
            if (city != null) {
                addressVO.setCityCode(city.getRegionCode());
                addressVO.setCityName(city.getRegionName());
            }
            Region county = regionRepository.findRegionByCode(countyCode);
            if (county != null) {
                addressVO.setCountyCode(county.getRegionCode());
                addressVO.setCountyName(county.getRegionName());
            }
            Region towns = regionRepository.findRegionByCode(townsCode);
            if (towns != null) {
                addressVO.setTownsCode(towns.getRegionCode());
                addressVO.setTownsName(towns.getRegionName());
            }
            return new ResponseEntity(addressVO, HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

}
