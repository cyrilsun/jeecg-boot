package org.jeecg.modules.biz.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 密码管理
 * @Author: jeecg-boot
 * @Date:   2023-02-01
 * @Version: V1.0
 */
@Data
@TableName("tb_password_management")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tb_password_management对象", description="密码管理")
public class PasswordManagement implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private java.lang.Long id;
	/**名称*/
	@Excel(name = "名称", width = 15)
    @ApiModelProperty(value = "名称")
    private java.lang.String name;
	/**站点*/
	@Excel(name = "站点", width = 15)
    @ApiModelProperty(value = "站点")
    private java.lang.String website;
	/**用户名*/
	@Excel(name = "用户名", width = 15)
    @ApiModelProperty(value = "用户名")
    private java.lang.String username;
	/**密码*/
	@Excel(name = "密码", width = 15)
    @ApiModelProperty(value = "密码")
    private java.lang.String code;
	/**提示*/
	@Excel(name = "提示", width = 15)
    @ApiModelProperty(value = "提示")
    private java.lang.String tip;
	/**本地存储*/
	@Excel(name = "本地存储", width = 15, dicCode = "local_storage")
	@Dict(dicCode = "local_storage")
    @ApiModelProperty(value = "本地存储")
    private java.lang.Integer localStorage;
	/**盐*/
	@Excel(name = "盐", width = 15)
    @ApiModelProperty(value = "盐")
    private java.lang.String salt;
	/**自定义秘钥 0否 1是*/
	@Excel(name = "自定义秘钥 0否 1是", width = 15)
    @ApiModelProperty(value = "自定义秘钥 0否 1是")
    private java.lang.Integer custom;
	/**分组*/
	@Excel(name = "分组", width = 15, dictTable = "tb_password_group", dicText = "name", dicCode = "id")
	@Dict(dictTable = "tb_password_group", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "分组")
    @JsonSerialize(using = ToStringSerializer.class)
    private java.lang.Long groupId;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
	/**填充*/
	@Excel(name = "填充", width = 15)
    @ApiModelProperty(value = "填充")
    private java.lang.String fill;
	/**createBy*/
    @ApiModelProperty(value = "createBy")
    private java.lang.String createBy;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
    private java.util.Date createTime;
	/**updateBy*/
    @ApiModelProperty(value = "updateBy")
    private java.lang.String updateBy;
	/**updateTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime")
    private java.util.Date updateTime;
	/**租户id*/
	@Excel(name = "租户id", width = 15)
    @ApiModelProperty(value = "租户id")
    private java.lang.Integer tenantId;
}
