package com.bigsun.www.mylibrary.lgkv;


import android.content.Context;

import com.bigsun.www.mylibrary.lgkv.inst.LGConverter;
import com.bigsun.www.mylibrary.lgkv.inst.LGEncryption;
import com.bigsun.www.mylibrary.lgkv.inst.LGGsonParser;
import com.bigsun.www.mylibrary.lgkv.inst.LGNoEncryption;
import com.bigsun.www.mylibrary.lgkv.inst.LGSerializer;
import com.bigsun.www.mylibrary.lgkv.inst.LGSharedPreferencesStorage;
import com.bigsun.www.mylibrary.lgkv.interf.Converter;
import com.bigsun.www.mylibrary.lgkv.interf.Encryption;
import com.bigsun.www.mylibrary.lgkv.interf.LogInterceptor;
import com.bigsun.www.mylibrary.lgkv.interf.Parser;
import com.bigsun.www.mylibrary.lgkv.interf.Serializer;
import com.bigsun.www.mylibrary.lgkv.interf.Storage;
import com.bigsun.www.mylibrary.lgkv.utils.LGUtils;
import com.google.gson.Gson;

/**
 * Created by wudi on 17-4-6.
 */

public class LGBuilder {

  //这个值是文件名，别动
  private static final String STORAGE_TAG_DO_NOT_CHANGE = "LG";

  private Context mContext;
  private Storage mStorage;//仓库 sp，sqlite 等
  private Converter mConverter; //转换，tostring ，fromstring
  private Parser mParser; //json 解析
  private Encryption mEncryption; // 加密/解密
  private Serializer mSerializer;//序列化
  private LogInterceptor mLogInterceptor; //打印log


  /**
   * 构造方法
   * @param context 上下文对象
   */
  public LGBuilder(Context context) {
    //[1]判空 ，抛出异常
    LGUtils.checkNull("Context",context);

    //[2]得到Application的上下文
    this.mContext = context.getApplicationContext();
  }

  /**
   * 调用LG中的build方法，设置参数
   */
  public void build() {
    LG.build(this);
  }

  public LGBuilder setmContext(Context mContext) {
    LGUtils.checkNull("Context",mContext);
    this.mContext = mContext.getApplicationContext();
    return this;
  }

  public LGBuilder setmStorage(Storage mStorage) {
    this.mStorage = mStorage;
    return this;
  }

  public LGBuilder setmConverter(Converter mConverter) {
    this.mConverter = mConverter;
    return this;
  }

  public LGBuilder setmParser(Parser mParser) {
    this.mParser = mParser;
    return this;
  }

  public LGBuilder setmEncryption(Encryption mEncryption) {
    this.mEncryption = mEncryption;
    return this;
  }

  public LGBuilder setmSerializer(Serializer mSerializer) {
    this.mSerializer = mSerializer;
    return this;
  }

  public LGBuilder setmLogInterceptor(LogInterceptor mLogInterceptor) {
    this.mLogInterceptor = mLogInterceptor;
    return this;
  }

  public Storage getmStorage() {
    if (this.mStorage == null) {
      mStorage = new LGSharedPreferencesStorage(mContext,STORAGE_TAG_DO_NOT_CHANGE);
    }
    return mStorage;
  }

  public Converter getmConverter() {
    if (this.mConverter == null) {
      mConverter = new LGConverter(getmParser());
    }
    return mConverter;
  }

  public Parser getmParser() {
    if (mParser == null) {
      mParser = new LGGsonParser(new Gson());
    }
    return mParser;
  }

  public Encryption getmEncryption() {
    if (mEncryption == null) {
      mEncryption = new LGEncryption(mContext);
      if (!mEncryption.init()) {
        mEncryption = new LGNoEncryption();
      }
    }
    return mEncryption;
  }

  public Serializer getmSerializer() {
    if (mSerializer == null) {
      mSerializer = new LGSerializer(getmLogInterceptor());
    }
    return mSerializer;
  }

  public LogInterceptor getmLogInterceptor() {
    if (mLogInterceptor == null) {
      mLogInterceptor = new LogInterceptor() {
        @Override public void onLog(String message) {
          //empty implementation
        }
      };
    }
    return mLogInterceptor;
  }

}
