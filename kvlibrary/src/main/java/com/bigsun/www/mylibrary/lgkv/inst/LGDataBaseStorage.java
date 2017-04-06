package com.bigsun.www.mylibrary.lgkv.inst;

import com.bigsun.www.mylibrary.lgkv.interf.Storage;

/**
 * Created by wudi on 17-4-6.
 */

public class LGDataBaseStorage implements Storage {

    @Override
    public <T> boolean put(String key, T value) {
        return false;
    }

    @Override
    public <T> T get(String key) {
        return null;
    }

    @Override
    public boolean delete(String key) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean contains(String key) {
        return false;
    }
}
