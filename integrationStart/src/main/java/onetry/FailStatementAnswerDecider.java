package onetry;

import java.util.ArrayList;
import java.util.List;

public class FailStatementAnswerDecider {
    private List<FailStatementAnswerPattern> patterns = new ArrayList<FailStatementAnswerPattern>();

    public boolean decide(FailAnswer failAnswer) {
        for (FailStatementAnswerPattern pattern : patterns){
            if (failAnswer.getFailMessage().matches(pattern.getRegexp())) {
                doAction(failAnswer, pattern);
                return true;
            }
        }

        return false;
    }

    private void doAction(FailAnswer failAnswer, FailStatementAnswerPattern pattern) {
        if (failAnswer.getCurrentTry() >= pattern.getNumberOfTry() || isLastDatToAnswer(failAnswer)) {
            exclude(failAnswer);
        }

        switch (pattern.getAction()) {
            case RESEND:
                resend(failAnswer, pattern);
                break;
            case INC_OPER_DAY:
                resendAndIncOperDay(failAnswer, pattern);
                break;
            case EXCLUDE_WITH_ERROR:
                excludeWithError(failAnswer, pattern);
                break;
        }
    }

    private boolean isLastDatToAnswer(FailAnswer failAnswer) {
        return false;
    }

    private void resend(FailAnswer failAnswer, FailStatementAnswerPattern pattern) {

    }

    private void resendAndIncOperDay(FailAnswer failAnswer, FailStatementAnswerPattern pattern) {

    }

    private void excludeWithError(FailAnswer failAnswer, FailStatementAnswerPattern pattern) {

    }

    private void exclude(FailAnswer failAnswer) {

    }

    public List<FailStatementAnswerPattern> getPatterns() {
        return patterns;
    }

    public void setPatterns(List<FailStatementAnswerPattern> patterns) {
        this.patterns = patterns;
    }
}
