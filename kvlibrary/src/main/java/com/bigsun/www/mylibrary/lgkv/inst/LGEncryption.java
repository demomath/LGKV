package com.bigsun.www.mylibrary.lgkv.inst;

import android.content.Context;

import com.bigsun.www.mylibrary.lgkv.interf.Encryption;


public class LGEncryption implements Encryption {


  public LGEncryption(Context context) {

  }

  @Override public boolean init() {
    return false;
  }

  /**
   * 加密逻
   * @param key   is the given key
   * @param plainText
   * @return
   * @throws Exception
   */
  @Override public String encrypt(String key, String plainText) throws Exception {
    //// FIXME: 17-4-6  这里添加自己的加密
    return plainText;
  }

  /**
   * 解密逻辑
   * @param key   is the given key
   * @param cipherText
   * @return
   * @throws Exception
   */
  @Override public String decrypt(String key, String cipherText) throws Exception {
    //// FIXME: 17-4-6  自己的解密
    return cipherText;
  }

}
