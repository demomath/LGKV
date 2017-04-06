package com.bigsun.www.mylibrary.lgkv.utils;

/**
 * Created by wudi on 17-4-6.
 */

public class LGUtils {

  public static void checkNull (String message, Object object) {
    if (object == null) {
      throw new NullPointerException("大兄弟，"+message+ "不能为null");
    }
  }

  public static void checkNullOrEmpty(String message, String value) {
    if (isEmpty(value)) {
      throw new NullPointerException("大兄弟，"+message+ "不能为null或者空");
    }
  }

  public static boolean isEmpty(String text) {
    return text == null || text.trim().length() == 0;
  }
}
