package clc65.tuongng59.nguyenhuynhtuong.project_android;

public class Question {
    public int id;
    public String question;
    public String optionA;
    public String optionB;
    public String optionC;
    public String optionD;
    public String correct;

    // Constructor đầy đủ cho PlayActivity
    public Question(int id, String question,
                    String optionA, String optionB,
                    String optionC, String optionD,
                    String correct) {

        this.id = id;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correct = correct;
    }


    // Constructor rút gọn cho QuestionListActivity
    public Question(int id, String question) {
        this.id = id;
        this.question = question;
    }

    @Override
    public String toString() {
        return question;
    }
}
