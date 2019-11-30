package cpm.liyulin.protostuff;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.liyulin.protostuff.SerializingUtil;

public class SerializingUtilTest {

	@Test
	public void test() throws InstantiationException, IllegalAccessException {
        String expect = "hello, world.";
        byte[] serialized = SerializingUtil.serialize(expect);
        Assertions.assertThat(expect).isEqualTo(SerializingUtil.deserialize(serialized, String.class));
	}
	
}