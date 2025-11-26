package org.emailservices.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "email_sender")
public class EmailSender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", length = 128, nullable = false)
    private String email;

    @Column(name = "policy_code", length = 10, nullable = false)
    private String policyCode;

    @Column(name = "status", length = 32, nullable = false)
    private String status;

    @Column(name = "action", length = 64, nullable = false)
    private String action;
    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPolicyCode() { return policyCode; }
    public void setPolicyCode(String policyCode) { this.policyCode = policyCode; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
}
