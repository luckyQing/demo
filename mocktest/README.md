如果有静态方法mock时，需要设置VM arguments：
jdk8：**-noverify**
jdk7：**-XX:-UseSplitVerifier**

> 可在IDE中全局设置
> eclipse：Windows->Preferences->Java->Installed JRES->选中已安装的JRE，点击“Edit”->在Default VM arguments中添加“-noverify”