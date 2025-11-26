package org.registration.dto;

public class RegistrationEvent {
    private String action;
    private String email;
    private String policyCode;

    public RegistrationEvent() {}

    public RegistrationEvent(String action, String email, String policyCode) {
        this.action = action;
        this.email = email;
        this.policyCode = policyCode;
    }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPolicyCode() { return policyCode; }
    public void setPolicyCode(String policyCode) { this.policyCode = policyCode; }
}