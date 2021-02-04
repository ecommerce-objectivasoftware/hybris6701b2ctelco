package de.hybris.electronics.dto.pdp;

import java.io.Serializable;

public class Issue implements Serializable {

    private int id;

    private String question;

    private String answer;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return this.answer;
    }
}
