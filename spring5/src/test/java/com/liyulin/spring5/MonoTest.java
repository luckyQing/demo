package com.liyulin.spring5;

import java.time.Duration;
import java.util.Optional;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoTest extends TestCase {

	public void test() {
		Mono.just("test hjf").subscribe(log::info);
		Mono.empty().subscribe((item) -> {
			log.info(String.valueOf(item));
		});
		
		Mono.error(new Throwable()).subscribe((info) -> {
			log.info(String.valueOf(info));
		}, (error)->{
			log.info(String.valueOf(error));
		});
		
		Mono.never().subscribe((item) -> {
			log.info(String.valueOf(item));
		});

		// fromCallable()、fromCompletionStage()、fromFuture()、fromRunnable()和
		// fromSupplier()：分别从 Callable、CompletionStage、CompletableFuture、Runnable 和
		// Supplier 中创建 Mono。
		Mono.fromSupplier(() -> "Hello").subscribe(log::info);

		// justOrEmpty(Optional<? extends T> data)和 justOrEmpty(T data)：从一个 Optional
		// 对象或可能为 null 的对象中创建 Mono。只有 Optional 对象中包含值或对象不为 null 时，Mono 序列才产生对应的元素。
		Mono.justOrEmpty(Optional.of("Hello")).subscribe(log::info);

		// delay(Duration duration)和 delayMillis(long duration)：创建一个 Mono
		// 序列，在指定的延迟时间之后，产生数字 0 作为唯一值。
		Mono.delay(Duration.ofSeconds(10)).subscribe((item) -> {
			log.info(String.valueOf(item));
		});

		// 还可以通过 create()方法来使用 MonoSink 来创建 Mono。
		Mono.create(sink -> sink.success("Hello")).subscribe((item) -> {
			log.info(String.valueOf(item));
		});
	}

}