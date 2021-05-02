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