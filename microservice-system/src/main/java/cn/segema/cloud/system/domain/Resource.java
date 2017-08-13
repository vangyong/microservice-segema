package cn.segema.cloud.system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 资源
 * @author wangyong
 *
 */
@Table(name = "SYS_RESOURCE")
@Entity
public class Resource  {
	@Id
	@Column(name = "RESOURCEID")
    private String resourceId;
	@Column(name = "RESOURCENAME")
    private String resourceName;
	@Column(name = "PARENTID")
    private String parentId;
	@Column(name = "RESOURCECODE")
    private String resourceCode;
	@Column(name = "TYPE")
    private String type;
	@Column(name = "RESOURCEURL")
    private String resourceUrl;
	@Column(name = "LEVEL")
    private String level;
	@Column(name = "ICON")
    private String icon;
	@Column(name = "ISHIDE")
    private Integer isHide;
	@Column(name = "DESCRIPTION")
    private String description;
	@Column(name = "DELETESTATUS")
    private Integer deleteStatus;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
