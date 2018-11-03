# 说明 #

----------
## 1. json ##
转化工具：fastjson
## 2. xml ##
转化工具：fasterxml
Jackson是一个强大工具，可用于Json、XML、实体之间的相互转换。

Jackson提供的四个注解：
@JacksonXmlElementWrapper：可用于指定List等集合类，外围标签名。
@JacksonXmlProperty：指定包装标签名，或者指定标签内部属性名。
@JacksonXmlRootElement：指定生成xml根标签的名字。
@JacksonXmlText：指定当前这个值，没有xml标签包裹。 
@JsonPropertyOrder：指定序列化为json串时的各字段顺序。