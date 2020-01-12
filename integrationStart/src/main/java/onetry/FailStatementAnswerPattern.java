package onetry;

public class FailStatementAnswerPattern {
    private Long id;
    private String regexp;
    private Integer SystemCode;
    private Action action;
    private Integer newSystemCode;
    private String error;
    private Integer numberOfTry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegexp() {
        return regexp;
    }

    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }

    public Integer getSystemCode() {
        return SystemCode;
    }

    public void setSystemCode(Integer systemCode) {
        SystemCode = systemCode;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Integer getNewSystemCode() {
        return newSystemCode;
    }

    public void setNewSystemCode(Integer newSystemCode) {
        this.newSystemCode = newSystemCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getNumberOfTry() {
        return numberOfTry;
    }

    public void setNumberOfTry(Integer numberOfTry) {
        this.numberOfTry = numberOfTry;
    }

    public enum Action {
        RESEND(),
        INC_OPER_DAY(),
        EXCLUDE_WITH_ERROR();

    }
}
