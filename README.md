# LGKV

Android 数据操作类库 欢迎各位指正

最近在研究组件式开发，写几个类库做做准备

```java
//FIXME 注释还需要完善

//TODO 还未添加database的操作
```

####使用：

初始化：
```java
LG.init(this)
.setmParser(null) //json解析方式，默认GSON
.setmConverter(null) // 类型解析方式，默认（Parser在这当中调用）
.setmSerializer(null) // 序列化方式，默认
.setmEncryption(null) // 加密方式，默认Base64
.setmStorage(null) // 数据读取方式，默认SharedPreferences
.setmLogInterceptor(new MyLogInterceptor()) // Log打印方式，默认不打印
.build();
```
传null的地方，不写也行

使用：
```java
 LG.put("ListData", list);
```
算了自己研究咋用吧大家