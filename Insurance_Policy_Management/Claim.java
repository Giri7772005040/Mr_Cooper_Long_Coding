import java.util.*;

public class Claim {
    String claimId;
    String policyNumber;
    Date incidentDate;
    double amount;
    String status;
    String adjusterNotes;

    public void submit() {
        status = "SUBMITTED";
    }

    public void approve() {
        status = "APPROVED";
    }

    public void reject() {
        status = "REJECTED";
    }

    public double calculateSettlement() {
        return amount * 0.9;
    }
}