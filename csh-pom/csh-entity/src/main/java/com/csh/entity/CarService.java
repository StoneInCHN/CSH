package com.csh.entity;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.ServiceStatus;
import com.csh.entity.commonenum.CommonEnum.ServiceType;


/**
 * 汽车服务
 *
 */

@Entity
@Table(name = "csh_car_service")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_car_service_sequence")
public class CarService extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 4496524488163375888L;

    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 服务简介
     */
    private String serviceDesc;

    /**
     * 服务介绍展示图片
     */
    private String imgPath;


    /**
     * 商家
     */
    private TenantInfo tenantInfo;

    /**
     * 服务分类
     */
    private ServiceType serviceType;

    /**
     * 服务单价
     */
    private BigDecimal price;

    /**
     * 评分
     */
    private Integer rate;
    
    /**
     * 服务状态
     */
    private ServiceStatus serviceStatus;
    
    @Column(length=50)
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Lob
	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@ManyToOne
	public TenantInfo getTenantInfo() {
		return tenantInfo;
	}

	public void setTenantInfo(TenantInfo tenantInfo) {
		this.tenantInfo = tenantInfo;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	@Column(scale = 2, precision = 10, nullable = false)
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public ServiceStatus getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(ServiceStatus serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	

}
