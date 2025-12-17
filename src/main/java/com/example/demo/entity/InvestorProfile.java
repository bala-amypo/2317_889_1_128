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
}