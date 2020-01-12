package onetry;

import java.util.Calendar;

public class FailAnswer {
    private String failMessage;
    private Calendar operDay = Calendar.getInstance();
    private Integer systemCode;
    private Integer currentTry;

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public Calendar getOperDay() {
        return operDay;
    }

    public void setOperDay(Calendar operDay) {
        this.operDay = operDay;
    }

    public Integer getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(Integer systemCode) {
        this.systemCode = systemCode;
    }

    public Integer getCurrentTry() {
        return currentTry;
    }

    public void setCurrentTry(Integer currentTry) {
        this.currentTry = currentTry;
    }
}
