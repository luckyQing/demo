package com.liyulin.protostuff;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.liyulin.protostuff.SerializingUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class SerializingUtilTest {

	@Test
	public void test() throws InstantiationException, IllegalAccessException {
        String expect = "hello, world.";
        byte[] serialized = SerializingUtil.serialize(expect);
        Assertions.assertThat(expect).isEqualTo(SerializingUtil.deserialize(serialized, String.class));
	}
	
	@Test
	public void testGeneric() {
		User user = new User();
		user.setId(1L);
		user.setName("张三");
		user.setAge(10);
		Req<User> req = new Req<>();
		req.setTransId("123");
		req.setT(user);
        byte[] serialized = SerializingUtil.serialize(req);
        Req<User> deserializeReq = SerializingUtil.deserialize(serialized, Req.class);
        Assertions.assertThat(req.getTransId()).isEqualTo(deserializeReq.getTransId());
        Assertions.assertThat(req.getT().getId()).isEqualTo(deserializeReq.getT().getId());;
        Assertions.assertThat(req.getT().getName()).isEqualTo(deserializeReq.getT().getName());;
        Assertions.assertThat(req.getT().getAge()).isEqualTo(deserializeReq.getT().getAge());
	}
	
	@Getter
	@Setter
	@ToString
	static class User{
		private long id;
		private String name;
		private int age;
	}
	
	@Getter
	@Setter
	@ToString
	static class Req<T>{
		private String transId;
		private T t;
	}
	
}