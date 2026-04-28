public class RiskEngine {
    double ageFactor;
    double coverageFactor;
    double historyFactor;

    public double scoreRisk(Customer c) {
        return c.age * 10 + historyFactor;
    }

    public double calcPremium(Customer c, double coverage) {
        double base = coverage * 0.02;
        double ageLoad = 0;

        if (c.age >= 18 && c.age <= 35) ageLoad = 0;
        else if (c.age <= 50) ageLoad = base * 0.5;
        else if (c.age <= 60) ageLoad = base;
        else ageLoad = base * 2;

        double preExisting = 500;
        double noClaimBonus = -0.05 * base;

        double finalPremium = base + ageLoad + preExisting + noClaimBonus;
        return Math.round(finalPremium / 10.0) * 10;
    }
}