package com.assessment.quizapi.response;

import java.util.List;

public class Question {
	private int id;

    private String question;

    private String description;

    private Answers answers;

    private String multiple_correct_answers;

    private Correct_answers correct_answers;

    private String correct_answer;

    private String explanation;

    private String tip;

    private List<Tags> tags;

    private String category;

    private String difficulty;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setQuestion(String question){
        this.question = question;
    }
    public String getQuestion(){
        return this.question;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setAnswers(Answers answers){
        this.answers = answers;
    }
    public Answers getAnswers(){
        return this.answers;
    }
    public void setMultiple_correct_answers(String multiple_correct_answers){
        this.multiple_correct_answers = multiple_correct_answers;
    }
    public String getMultiple_correct_answers(){
        return this.multiple_correct_answers;
    }
    public void setCorrect_answers(Correct_answers correct_answers){
        this.correct_answers = correct_answers;
    }
    public Correct_answers getCorrect_answers(){
        return this.correct_answers;
    }
    public void setCorrect_answer(String correct_answer){
        this.correct_answer = correct_answer;
    }
    public String getCorrect_answer(){
        return this.correct_answer;
    }
    public void setExplanation(String explanation){
        this.explanation = explanation;
    }
    public String getExplanation(){
        return this.explanation;
    }
    public void setTip(String tip){
        this.tip = tip;
    }
    public String getTip(){
        return this.tip;
    }
    public void setTags(List<Tags> tags){
        this.tags = tags;
    }
    public List<Tags> getTags(){
        return this.tags;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public String getCategory(){
        return this.category;
    }
    public void setDifficulty(String difficulty){
        this.difficulty = difficulty;
    }
    public String getDifficulty(){
        return this.difficulty;
    }
}
