package com.assessment.pepipost.data;

import java.util.ArrayList;
import java.util.List;

public class Personalizations {
	private List<To> to = new ArrayList<To>();

    public void setTo(List<To> to){
        this.to = to;
    }
    public List<To> getTo(){
        return this.to;
    }
}
