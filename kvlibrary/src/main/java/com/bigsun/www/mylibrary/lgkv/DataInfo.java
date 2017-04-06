package com.bigsun.www.mylibrary.lgkv;

public final class DataInfo {

  public static final char TYPE_OBJECT = '0';
  public static final char TYPE_LIST = '1';
  public static final char TYPE_MAP = '2';
  public static final char TYPE_SET = '3';

  public final char dataType;
  public final String cipherText;
  public final Class keyClazz;
  public final Class valueClazz;

  public DataInfo(char dataType, String cipherText, Class keyClazz, Class valueClazz) {
    this.cipherText = cipherText;
    this.keyClazz = keyClazz;
    this.valueClazz = valueClazz;
    this.dataType = dataType;
  }
}
