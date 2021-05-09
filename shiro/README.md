登录账号：root/root

https://www.w3cschool.cn/shiro/vxcq1ifl.html

# 笔记
```
@RequiresPermissions(value = {"add", "update"}, logical = Logical.AND)
等价于
boolean isPermittedAll = SecurityUtils.getSubject().isPermittedAll("add", "update");



@RequiresRoles(value = {"vip", "admin"}, logical = Logical.AND)
等价于
SecurityUtils.getSubject().hasAllRoles(Arrays.asList("vip","admin"));
```

# 注解
## 1.@RequiresAuthentication：
```
使用该注解标注的类，实例，方法在访问或调用时，当前Subject必须通过login 进行了身份验证；即 Subject.isAuthenticated() 返回 true
```

## 2.@RequiresUser：
```
表示当前 Subject 已经身份验证或者通过记住我登录的,才能访问或调用被该注解标注的类，实例，方法。

验证用户是否被记忆，user有两种含义：
一种是成功登录的（subject.isAuthenticated() 结果为true）；
另外一种是被记忆的（subject.isRemembered()结果为true）。
```

## 3.@RequiresGuest：
```
使用该注解标注的类，实例，方法在访问或调用时，当前Subject可以是“gust”身份，不需要经过身份验证或通过记住我登录过，即是游客身份。
```
## 4.@RequiresRoles：
```
当前Subject必须拥有所有指定的角色时，才能访问被该注解标注的方法。如果当前Subject不同时拥有所有指定角色，则方法不会执行还会抛出AuthorizationException异常。
如：@RequiresRoles(value={“admin”, “user”}, logical= Logical.AND)： 表示当前 Subject需要角色 admin 和user
```
## 5.@RequiresPermissions：
```
当前Subject需要拥有某些特定的权限时，才能执行被该注解标注的方法。如果当前Subject不具有这样的权限，则方法不会被执行。
```