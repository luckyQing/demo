package com.liyulin.shading.jdbc.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class BaseEntity extends BaseDto {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "f_id")
	private Long id;

	/** 创建时间 */
	@Column(name = "f_sys_add_time")
	private Date addTime;

	/** 更新时间 */
	@Column(name = "f_sys_upd_time")
	private Date updTime;

	/** 删除时间 */
	@Column(name = "f_sys_del_time")
	private Date delTime;

	/** 新增者 */
	@Column(name = "f_sys_add_user")
	private Long addUser;

	/** 更新者 */
	@Column(name = "f_sys_upd_user")
	private Long updUser;

	/** 删除者 */
	@Column(name = "f_sys_del_user")
	private Long delUser;

	/** 删除状态=={'1':'正常','2':'已删除'} */
	@Column(name = "f_sys_del_state")
	private Byte delState;

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum Columns {
		/** 主键 */
		ID("id", "f_id"),
		/** 创建时间 */
		ADD_TIME("addTime", "f_sys_add_time"),
		/** 更新时间 */
		UPD_TIME("updTime", "f_sys_upd_time"),
		/** 删除时间 */
		DEL_TIME("delTime", "f_sys_del_time"),
		/** 新增者 */
		ADD_USER("addUser", "f_sys_add_user"),
		/** 更新者 */
		UPD_USER("updUser", "f_sys_upd_user"),
		/** 删除者 */
		DEL_USER("delUser", "f_sys_del_user"),
		/** 删除状态 */
		DEL_STATE("delState", "f_sys_del_state");

		private String property;
		private String column;
	}

}