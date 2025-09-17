package in.chill.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "budget")
public class Budget {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id")
    private int budgetId;
    
    @Column(name = "allocated_amount")
    private float allocatedAmount;
    
    @Column(name = "utilized_amount")
    private float utilizedAmount;
    
    @OneToOne
    @JoinColumn(name = "sponsor_id")
    private Sponsor sponsor;
    
    @OneToOne
    @JoinColumn(name = "club_id")
    private Club club;
    
    @OneToOne
    @JoinColumn(name = "event_id")
    private Events event;
    
    // Default constructor
    public Budget() {}
    
    // Constructor with parameters
    public Budget(float allocatedAmount, float utilizedAmount, Sponsor sponsor, Club club, Events event) {
        this.allocatedAmount = allocatedAmount;
        this.utilizedAmount = utilizedAmount;
        this.sponsor = sponsor;
        this.club = club;
        this.event = event;
    }
    
    // Getters and setters
    public int getBudgetId() {
        return budgetId;
    }
    
    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }
    
    public float getAllocatedAmount() {
        return allocatedAmount;
    }
    
    public void setAllocatedAmount(float allocatedAmount) {
        this.allocatedAmount = allocatedAmount;
    }
    
    public float getUtilizedAmount() {
        return utilizedAmount;
    }
    
    public void setUtilizedAmount(float utilizedAmount) {
        this.utilizedAmount = utilizedAmount;
    }
    
    public Sponsor getSponsor() {
        return sponsor;
    }
    
    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }
    
    public Club getClub() {
        return club;
    }
    
    public void setClub(Club club) {
        this.club = club;
    }
    
    public Events getEvent() {
        return event;
    }
    
    public void setEvent(Events event) {
        this.event = event;
    }
    
    @Override
    public String toString() {
        return "Budget{" +
                "budgetId=" + budgetId +
                ", allocatedAmount=" + allocatedAmount +
                ", utilizedAmount=" + utilizedAmount +
                ", sponsor=" + (sponsor != null ? sponsor.getSponsorId() : null) +
                ", club=" + (club != null ? club.getClub_id() : null) +
                ", event=" + (event != null ? event.getEvent_id() : null) +
                '}';
    }
}
