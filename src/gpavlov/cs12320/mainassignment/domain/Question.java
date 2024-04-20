package gpavlov.cs12320.mainassignment.domain;

public interface Question {

    enum Type {SINGLE_CHOICE, FILL_BLANKS}

    String getQuestionID();
    String getQuestionText();
    Type getType();

    boolean validate(String ... stuAnswer);

}
