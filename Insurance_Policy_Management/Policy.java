import java.util.*;

public class Policy {
    String policyNumber;
    String holderId;
    String type;
    double coverageAmount;
    double premium;
    Date startDate;
    Date endDate;
    String status;
    double remainingAmount;
    public void activate() {
        status = "ACTIVE";
    }

    public void lapse() {
        status = "LAPSED";
    }

    public void renew() {
        status = "RENEWED";
    }

    public void surrender() {
        status = "SURRENDERED";
    }
}