package com.bigsun.www.mylibrary.lgkv;


import com.bigsun.www.mylibrary.lgkv.interf.Converter;
import com.bigsun.www.mylibrary.lgkv.interf.Encryption;
import com.bigsun.www.mylibrary.lgkv.interf.LGFacase;
import com.bigsun.www.mylibrary.lgkv.interf.LogInterceptor;
import com.bigsun.www.mylibrary.lgkv.interf.Serializer;
import com.bigsun.www.mylibrary.lgkv.interf.Storage;
import com.bigsun.www.mylibrary.lgkv.utils.LGUtils;

/**
 * Created by wudi on 17-4-6.
 *
 * <p>默认配置</p>
 */
public class DefaultLGFacase implements LGFacase {

  private final Storage mStorage;
  private final Converter mConverter;
  private final Encryption mEncryption;
  private final Serializer mSerializer;
  private final LogInterceptor mLogInterceptor;

  public DefaultLGFacase(LGBuilder builder) {
    mEncryption = builder.getmEncryption();
    mStorage = builder.getmStorage();
    mConverter = builder.getmConverter();
    mSerializer = builder.getmSerializer();
    mLogInterceptor = builder.getmLogInterceptor();
    mLogInterceptor.onLog("LG.init -> Encryption : " + mEncryption.getClass().getSimpleName());
  }

  @Override public <T> boolean put(String key, T value) {
    //[1] 判断key是否为空
    LGUtils.checkNull("Key", key);
    log("LG.put -> key: " + key + ", value: " + value);

    //[2] 判断value是否为空，为空的话把原有的数据删除
    if (value == null) {
      log("LG.put -> 大兄弟，既然你要保存一个空值，我就把原来的删除了！");
      return delete(key);
    }

    //[3] 转换类型 T -> String
    String plainText = mConverter.toString(value);
    log("LG.put -> 大兄弟， T -> String 的结果是： " + plainText);

    //[3.1] 判断转成String 之后是否为null
    if (plainText == null) {
      log("LG.put -> 大兄弟，我们 T -> String 失败了");
      return false;
    }

    //[4] 加密
    String cipherText = null;
    try {
      cipherText = mEncryption.encrypt(key, plainText);
      log("LG.put -> 大兄弟，加密完的字符串是：  " + cipherText);
    } catch (Exception e) {
      e.printStackTrace();
    }

    //[4.1] 加密后的字符串是否为空
    if (cipherText == null) {
      log("LG.put -> 大兄弟，我们加密失败了");
      return false;
    }
    
    //[5] 序列化
    String serializedText = mSerializer.serialize(cipherText, value);
    log("LG.put -> 大兄弟，序列化之后的结果是：" + serializedText);
    if (serializedText == null) {
      log("LG.put -> 大兄弟，我们序列化失败了");
      return false;
    }

    //[6] 保存
    if (mStorage.put(key, serializedText)) {
      log("LG.put -> 大兄弟，我们保存成功了！");
      return true;
    } else {
      log("LG.put -> 大兄弟，我们保存失败了！");
      return false;
    }
  }

  @Override public <T> T get(String key) {
    log("LG.get -> key: " + key);
    // [1]判断key是否为空
    if (key == null) {
      log("LG.get -> 大兄弟，你给我传一个null的key弄啥");
      return null;
    }

    // [2] 取值
    String serializedText = mStorage.get(key);
    log("LG.get -> 取出的序列化值 : " + serializedText);
    // [2.1] 判断取得值是否为空
    if (serializedText == null) {
      log("LG.get -> 大兄弟，我们取数据失败了！");
      return null;
    }

    // [3] 反序列化
    DataInfo dataInfo = mSerializer.deserialize(serializedText);
    if (dataInfo == null) {
      log("LG.get -> 大兄弟，我们反序列化失败了！");
      return null;
    }

    // [4] 解密
    String plainText = null;
    try {
      plainText = mEncryption.decrypt(key, dataInfo.cipherText);
      log("LG.get -> 大兄弟，解密成功了，结果是 : " + plainText);
    } catch (Exception e) {
      log("LG.get -> 大兄弟，解密失败了，错误是 " + e.getMessage());
    }
    if (plainText == null) {
      log("LG.get -> 大兄弟，解密失败了，为空的");
      return null;
    }

    // [5] String - > T
    T result = null;
    try {
      result = mConverter.fromString(plainText, dataInfo);
      log("LG.get -> String -> T 成功，结果是： " + result);
    } catch (Exception e) {
      log("LG.get -> String -> T 失败");
    }

    return result;
  }

  @Override public <T> T get(String key, T defaultValue) {
    T t = get(key);
    return t == null ? defaultValue : t;
  }

  @Override public long count() {
    return mStorage.count();
  }

  @Override public boolean deleteAll() {
    return mStorage.deleteAll();
  }

  @Override public boolean delete(String key) {
    return mStorage.delete(key);
  }

  @Override public boolean contains(String key) {
    return mStorage.contains(key);
  }

  @Override public boolean isBuilt() {
    return true;
  }

  @Override public void destroy() {
  }

  private void log(String message) {
    mLogInterceptor.onLog(message);
  }
}
