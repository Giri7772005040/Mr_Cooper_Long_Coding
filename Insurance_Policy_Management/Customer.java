
import java.util.*;

public class Customer {

    String customerId;
    String name;
    int age;
    boolean kycStatus;
    List<Policy> policies = new ArrayList<>();

    public Policy applyPolicy(String type, double coverage, RiskEngine r) {
        double premium = r.calcPremium(this, coverage);
        Policy p = new Policy();
        p.policyNumber = UUID.randomUUID().toString();
        p.holderId = customerId;
        p.type = type;
        p.coverageAmount = coverage;
        p.premium = premium;
        p.status = "PENDING";
        p.remainingAmount = p.premium;
        policies.add(p);
        return p;
    }

    public Claim raiseClaim(String policyNumber, double amount) {
        Claim c = new Claim();
        c.claimId = UUID.randomUUID().toString();
        c.policyNumber = policyNumber;
        c.amount = amount;
        c.incidentDate = new Date();
        c.submit();
        return c;
    }

    public void renewPolicy(Policy p) {
        p.renew();
    }
}
