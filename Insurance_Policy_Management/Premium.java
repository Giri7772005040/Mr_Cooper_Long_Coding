
import java.util.*;

public class Premium {

    String premiumId;
    String policyNumber;
    Date dueDate;
    double amount;
    Date paidDate;
    String status;

    public void pay() {
        status = "PAID";
        paidDate = new Date();
    }

    public void markOverdue() {
        status = "OVERDUE";
    }

    public void applyGracePeriod() {
        status = "GRACE";
    }
}
