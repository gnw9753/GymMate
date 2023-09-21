package com.example.gymmate.ui.review;

import java.util.ArrayList;
import java.util.List;

public class DataBean {

    public String date;
    public float value;

    public DataBean(String s,float v)
    {
        this.date=s;
        this.value=v;
    }

    public static List<DataBean> getData()
    {
        List<DataBean> list=new ArrayList<>();
        list.add(new DataBean("0901",10));
        list.add(new DataBean("0902",9));
        list.add(new DataBean("0903",11));
        list.add(new DataBean("0904",15));
        return list;
    }
}
