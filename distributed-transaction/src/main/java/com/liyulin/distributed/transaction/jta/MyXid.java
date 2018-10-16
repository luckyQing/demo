package com.liyulin.distributed.transaction.jta;

import javax.transaction.xa.Xid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实现一个Xid类用来标识事务
 * 
 * @author liyulin
 * @version 1.0 2016年9月18日 上午10:38:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyXid implements Xid {

	private int formatId;
	/** 全局事务标识符 */
	private byte globalTransactionId[];
	/** 分支修饰词标识符 */
	private byte branchQualifier[];

}
