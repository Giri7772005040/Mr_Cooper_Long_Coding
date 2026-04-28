public class Underwriter {
    String underwriterId;
    String name;

    public double assessRisk(Customer c, RiskEngine r, double coverage) {
        return r.scoreRisk(c) + r.calcPremium(c, coverage);
    }

    public boolean approve(double riskScore) {
        return riskScore < 10000;
    }

    public boolean reject(double riskScore) {
        return riskScore >= 10000;
    }
}