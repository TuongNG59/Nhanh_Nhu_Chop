package clc65.tuongng59.nguyenhuynhtuong.project_android;

public class Question {
    public int id;
    public String question;

    public Question(int id, String question) {
        this.id = id;
        this.question = question;
    }

    @Override
    public String toString() {
        return question;
    }
}
