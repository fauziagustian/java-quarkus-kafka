package org.registration.dto;

public class CreatePolishResponse {
    private String customerName;
    private String email;
    private String productName;
    private Integer amount;
    private String policyCode;

    public CreatePolishResponse() {}

    public CreatePolishResponse(String customerName, String email,
                                String productName, Integer amount,
                                String policyCode) {
        this.customerName = customerName;
        this.email = email;
        this.productName = productName;
        this.amount = amount;
        this.policyCode = policyCode;
    }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }

    public String getPolicyCode() { return policyCode; }
    public void setPolicyCode(String policyCode) { this.policyCode = policyCode; }
}