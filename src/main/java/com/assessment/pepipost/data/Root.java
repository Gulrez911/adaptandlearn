package com.assessment.pepipost.data;

import java.util.ArrayList;
import java.util.List;

public class Root {
	private From from;

    private String subject;

    private List<Content> content = new ArrayList<Content>();

    private List<Personalizations> personalizations =  new ArrayList<>();

    public void setFrom(From from){
        this.from = from;
    }
    public From getFrom(){
        return this.from;
    }
    public void setSubject(String subject){
        this.subject = subject;
    }
    public String getSubject(){
        return this.subject;
    }
    public void setContent(List<Content> content){
        this.content = content;
    }
    public List<Content> getContent(){
        return this.content;
    }
    public void setPersonalizations(List<Personalizations> personalizations){
        this.personalizations = personalizations;
    }
    public List<Personalizations> getPersonalizations(){
        return this.personalizations;
    }
}
