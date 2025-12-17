@Entity
public class InvestorProfile {
    @Id
    private Long id;
    private String investorId;
    private String fullName;
    @Column(name=unique)
    @Email(message="Invalid Email")
    private String email;
    private boolean active;
    private LocalDate createdAt;

    public InvestorProfile() {
    }

    public InvestorProfile(
        Long id,
        String investorId,
        String fullName,
        String email,
        boolean active,
        LocalDate createdAt
    ) {
        this.id = id;
        this.investorId = investorId;
        this.fullName = fullName;
        this.email = email;
        this.active = active;
        this.createdAt = createdAt
    }

    public void getId() {
        return this.id;
    }

    public Long setId(Long id) {
        this.id = id;
    }

    public void getInvestorId() {
        return this.investorId;
    }

    public String setInvestorId(String investorId) {
        this.investorId = investorId;
    }

    public void getFullName() {
        return this.fullName;
    }

    public String setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}