
import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Customer customer = new Customer();
    static RiskEngine engine = new RiskEngine();
    static Underwriter uw = new Underwriter();
    static List<Policy> policies = new ArrayList<>();
    static List<Claim> claims = new ArrayList<>();

    public static void main(String[] args) {
        customer.customerId = "C101";
        customer.name = "User";
        customer.kycStatus = true;

        while (true) {
            System.out.println("\n1.Apply Policy\n2.Pay Premium\n3.Raise Claim\n4.View Policies\n5.Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    applyPolicy();
                    break;
                case 2:
                    payPremium();
                    break;
                case 3:
                    raiseClaim();
                    break;
                case 4:
                    viewPolicies();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    static void applyPolicy() {
        System.out.println("Enter Age:");
        customer.age = sc.nextInt();

        System.out.println("Enter Coverage Amount:");
        double coverage = sc.nextDouble();

        Policy policy = customer.applyPolicy("Health", coverage, engine);

        double riskScore = uw.assessRisk(customer, engine, coverage);

        if (uw.approve(riskScore)) {
            policy.status = "PENDING";
            policies.add(policy);
            System.out.println("Policy Approved: " + policy.policyNumber);
            System.out.println("Premium: " + policy.premium);
        } else {
            policy.lapse();
            System.out.println("Policy Rejected");
        }
    }

    static void payPremium() {
        if (policies.isEmpty()) {
            System.out.println("No policies found");
            return;
        }

        System.out.println("Enter Policy Number:");
        String pno = sc.next();

        for (Policy p : policies) {
            if (p.policyNumber.equals(pno)) {

                System.out.println("Remaining Premium: " + p.remainingAmount);
                System.out.println("Enter Amount to Pay:");
                double amount = sc.nextDouble();

                if (amount <= 0) {
                    System.out.println("Invalid amount");
                    return;
                }

                if (amount > p.remainingAmount) {
                    System.out.println("Amount exceeds remaining balance");
                    return;
                }

                p.remainingAmount -= amount;

                Premium premium = new Premium();
                premium.policyNumber = pno;
                premium.amount = amount;
                premium.pay();

                if (p.remainingAmount == 0) {
                    p.activate();
                    System.out.println("Full payment done. Policy Activated");
                } else {
                    p.status = "PENDING";
                    System.out.println("Payment accepted. Remaining: " + p.remainingAmount);
                }

                return;
            }
        }

        System.out.println("Policy not found");
    }

    static void raiseClaim() {
        System.out.println("Enter Policy Number:");
        String pno = sc.next();

        Policy foundPolicy = null;

        for (Policy p : policies) {
            if (p.policyNumber.equals(pno)) {
                foundPolicy = p;
                break;
            }
        }

        if (foundPolicy == null) {
            System.out.println("Policy not found");
            return;
        }

        if (!foundPolicy.status.equals("ACTIVE")) {
            System.out.println("Policy is not active. Claim not allowed");
            return;
        }

        System.out.println("Enter Claim Amount:");
        double amt = sc.nextDouble();

        if (amt < 50000) {
            Claim claim = customer.raiseClaim(pno, amt);
            claims.add(claim);
            claim.approve();
            System.out.println("Claim Approved. Settlement: " + claim.calculateSettlement());
        } else {
            Claim claim = customer.raiseClaim(pno, amt);
            claims.add(claim);
            claim.reject();
            System.out.println("Claim Rejected");
        }
    }

    static void viewPolicies() {
        for (Policy p : policies) {
            System.out.println("Policy: " + p.policyNumber
                    + " Status: " + p.status
                    + " Premium: " + p.premium
                    + " Remaining: " + p.remainingAmount);
        }
    }
}
