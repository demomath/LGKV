package com.bigsun.www.mylibrary.lgkv;

import android.content.Context;

import com.bigsun.www.mylibrary.lgkv.interf.LGFacase;
import com.bigsun.www.mylibrary.lgkv.utils.LGUtils;


/**
 * Created by wudi on 17-4-6.
 * <p>由构造器创建对象</p>
 *
 */

public final class LG {


  static LGFacase mLGFacase = new LGFacase.EmptyLGFacase();

  /**
   * 私有构造方法
   */
  private LG () {}

  /**
   * 初始化
   * @param context 上下文对象
   * @return 返回构造器
   */
  public static LGBuilder init(Context context) {
    //[1]判空
    LGUtils.checkNull("Context",context);

    //[2]置空配置
    mLGFacase = null;

    //[3]返回构造器
    return new LGBuilder(context);
  }

  /**
   * 未public，为外类库外部调用不了
   */
  static void build(LGBuilder lgBuilder) {
    mLGFacase = new DefaultLGFacase(lgBuilder);
  }

  public static <T> boolean put(String key, T value) {
    return mLGFacase.put(key,value);
  }

  public static <T> T get(String key) {
    return mLGFacase.get(key);
  }

  public static <T> T get(String key, T defaultValue) {
    return mLGFacase.get(key,defaultValue);
  }

  public static long count() {
    return mLGFacase.count();
  }

  public static boolean deleteAll() {
    return mLGFacase.deleteAll();
  }

  public static boolean delete(String key) {
    return mLGFacase.delete(key);
  }

  public static boolean contains(String key) {
    return mLGFacase.contains(key);
  }

  public static boolean isBuilt() {
    return mLGFacase.isBuilt();
  }

  public static void destroy() {
    mLGFacase.destroy();
  }
}
