> 运行junit5，当前JDK环境要在 Java 8 以上

# junit5注解
```
@DisplayName：设置名称
@Disabled：禁用执行测试
@Nested：内嵌测试类
```

**注意点：**
1、新建suite时，不用通过“右键——>Junit Test Suite”的方式创建；通过普通的方式创建即可，否则eclipse可能识别不了。
2、suite case类名必须一**Test开头**，或者以**Test、Tests结尾**。

**参考**
https://junit.org/junit5/docs/current/user-guide/#running-tests-junit-platform-runner