package com.bigsun.www.mylibrary.lgkv.interf;

/**
 * Created by wudi on 17-4-6.
 * <p>定义LG操作</p>
 */
public interface LGFacase {

  <T> boolean put(String key, T value);

  <T> T get(String key);

  <T> T get(String key, T defaultValue);

  long count();

  boolean deleteAll();

  boolean delete(String key);

  boolean contains(String key);

  boolean isBuilt();

  void destroy();

  /**
   * 当LG未init时，定义一个空的规则，抛出异常
   */
  public class EmptyLGFacase implements LGFacase {
    @Override public <T> boolean put(String key, T value) {
      throwValidation();
      return false;
    }

    @Override public <T> T get(String key) {
      throwValidation();
      return null;
    }

    @Override public <T> T get(String key, T defaultValue) {
      throwValidation();
      return null;
    }

    @Override public long count() {
      throwValidation();
      return 0;
    }

    @Override public boolean deleteAll() {
      throwValidation();
      return false;
    }

    @Override public boolean delete(String key) {
      throwValidation();
      return false;
    }

    @Override public boolean contains(String key) {
      throwValidation();
      return false;
    }

    @Override public boolean isBuilt() {
      throwValidation();
      return false;
    }

    @Override public void destroy() {
      throwValidation();
    }

    private void throwValidation() {
      throw new IllegalStateException("LG 创建失败 " +
          "请在调用增删方法之前，先执行init。");
    }
  }
}
