package com.bigsun.www.mylibrary.lgkv.interf;


import com.bigsun.www.mylibrary.lgkv.DataInfo;

@SuppressWarnings("WeakerAccess")
/**
 * 序列化 的接口
 */
public interface Serializer {

  /**
   * Serialize the cipher text along with the given data type
   *
   * @return serialized string
   */
  <T> String serialize(String cipherText, T value);

  /**
   * Deserialize the given text according to given DataInfo
   *
   * @return original object
   */
  DataInfo deserialize(String plainText);
}