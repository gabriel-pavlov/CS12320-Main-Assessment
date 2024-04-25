package gpavlov.cs12320.mainassignment.domain;

import java.util.ArrayList;
import java.util.List;

public class QuizPOJO implements Quiz {

    private final List<SelectedQuestion> quizQuestions = new ArrayList<>();

    public QuizPOJO(final List<AnswerableQuestion> selected) {
        selected.stream().forEach(aq -> {
            switch (aq.getType()) {
                case SINGLE_CHOICE:
                    quizQuestions.add(new SelectedSingleQuestionPOJO(aq));
                    break;
                case FILL_BLANKS:
                    quizQuestions.add(new SelectedBlankQuestionPOJO(aq));
                    break;
            }
        });

    }

    @Override
    public List<AnswerableQuestion> getQuestions() {
        final List<AnswerableQuestion> answerableQuestions = new ArrayList<>();
        quizQuestions.stream().forEach(qq -> {
            answerableQuestions.add(qq.getQuestion());
        });
        return answerableQuestions;
    }

    @Override
    public int getUnansweredQuestionCount() {
        return (int) quizQuestions.stream().filter(qq -> !qq.isAnswered()).count();
    }

    @Override
    public float validate() {
        final int score = (int) quizQuestions.stream().filter(qq -> qq.validate()).count();
        return ((float) score) / ((float) quizQuestions.size());
    }

    @Override
    public void captureAnswer(final int index, final AnswerCapturer answer) {
        quizQuestions.get(index).captureAnswer(answer);
    }
}
