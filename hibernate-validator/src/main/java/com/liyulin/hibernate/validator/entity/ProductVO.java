package com.liyulin.hibernate.validator.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.liyulin.hibernate.validator.entity.ValidatorGroup.Insert;

import lombok.Data;

/**
 * group校验测试
 *
 * @author liyulin
 * @date 上午11:38:10
 */
@Data
public class ProductVO {
	
	@NotNull(message = "id不允许为空", groups = { Insert.class })
	@Min(value = 1, message = "最小值为1")
	private BigInteger id;

	@NotBlank(message = "名称不能为空")
	@Length(max = 10, message = "名称最大长度为10")
	private String name;

	@NotNull(message = "价格不能为空")
	@DecimalMin(value = "1", message = "价格最小值为1")
	@DecimalMax(value = "100", message = "价格最大值为100")
	private BigDecimal price;

}
