package com.bigsun.www.mylibrary.lgkv.interf;

import java.lang.reflect.Type;

@SuppressWarnings("WeakerAccess")
/**
 * 解析json 的接口
 */
public interface Parser {

  <T> T fromJson(String content, Type type) throws Exception;

  String toJson(Object body);

}
