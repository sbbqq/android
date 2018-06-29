package com.example.moduleview.Modal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alone-nine-sword on 18-6-28.
 */

public class Family {
    private String whom;
    private int sex;//0 w 1 m
    private List<Attribute> attributes=new ArrayList<>();

    public Family(String whom, int sex) {
        this.whom = whom;
        this.sex = sex;
    }


    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getWhom() {
        return whom;
    }

    public void setWhom(String whom) {
        this.whom = whom;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

   public Attribute getAttrByFlag(int Flag){
        for(int i=0;i<attributes.size();i++){
             if(getAttributes().get(i).getFlag()==Flag)
             {
                 return  getAttributes().get(i);

             }
        }
        return  null;
   }

}
