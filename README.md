# Insurance Policy Management System

## Problem Statement

Develop an Insurance Policy Management System that handles policy creation, premium calculation, payment processing, and claim management. The system should ensure that policies are activated only after full premium payment and allow claims only for active policies.

---

## Approach / Logic Used

* Customer applies for a policy by entering age and coverage amount
* System calculates premium using risk factors
* Underwriter approves or rejects the policy
* Approved policies are created with **PENDING status**
* Premium payment supports **multiple installments**
* Remaining premium amount is tracked dynamically
* Policy becomes **ACTIVE only after full payment**
* Claims can be raised only when the policy is ACTIVE
* Claims below threshold are approved and settlement is calculated

---

## Steps to Execute the Code

1. Open terminal in project directory
2. Compile all Java files:

```bash
javac *.java
```

3. Run the program:

```bash
java Main
```

4. Use menu options in terminal to:

   * Apply policy
   * Pay premium
   * Raise claim
   * View policies
