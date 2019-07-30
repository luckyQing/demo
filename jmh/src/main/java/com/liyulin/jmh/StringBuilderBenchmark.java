package com.liyulin.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

//基准测试类型
@BenchmarkMode(Mode.Throughput)
//预热的迭代次数
@Warmup(iterations = 3)
//度量:iterations正式进行测试的轮次，time每轮进行的时长，timeUnit时长单位,batchSize批次数量
@Measurement(iterations = 8, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(4)//测试线程数量
//3轮测试
@Fork(3)
@OutputTimeUnit(TimeUnit.MILLISECONDS)//基准测试结果的时间类型
public class StringBuilderBenchmark {

	public static void main(String[] args) throws RunnerException {
		Options options = new OptionsBuilder()
				.include(StringBuilderBenchmark.class.getSimpleName())
				.output("E:/Benchmark.log").build();
		new Runner(options).run();
	}

	@SuppressWarnings("unused")
	@Benchmark
	public void testStringAdd() {
		String a = "";
		for (int i = 0; i < 10; i++) {
			a += i;
		}
	}

	@Benchmark
	public void testStringBuilderAdd() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			sb.append(i);
		}
	}
}