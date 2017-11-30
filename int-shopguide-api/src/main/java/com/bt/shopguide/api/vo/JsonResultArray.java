package com.bt.shopguide.api.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by caiting on 2017/9/29.
 */
public class JsonResultArray implements Serializable {

    public Collection<? extends Serializable> getList() {
        return list;
    }

    public void setList(Collection<? extends Serializable> list) {
        this.list = list;
    }

    private Collection<? extends Serializable> list;
}
