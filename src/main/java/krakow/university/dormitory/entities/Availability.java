package krakow.university.dormitory.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "availabilities")
public class Availability {
    @Id
    @Column(name = "availability_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer availabilityId;

    @Column(name = "availability_weekday")
    private Integer availabilityWeekday;

    @Column(name = "availability_start_hour")
    private Integer availabilityStartHour;

    @Column(name = "availability_stop_hour")
    private Integer availabilityStopHour;

    public Integer getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Integer getAvailabilityWeekday() {
        return availabilityWeekday;
    }

    public void setAvailabilityWeekday(Integer availabilityWeekday) {
        this.availabilityWeekday = availabilityWeekday;
    }

    public Integer getAvailabilityStartHour() {
        return availabilityStartHour;
    }

    public void setAvailabilityStartHour(Integer availabilityStartHour) {
        this.availabilityStartHour = availabilityStartHour;
    }

    public Integer getAvailabilityStopHour() {
        return availabilityStopHour;
    }

    public void setAvailabilityStopHour(Integer availabilityStopHour) {
        this.availabilityStopHour = availabilityStopHour;
    }

    @Override
    public String toString() {
        return "Availability{" +
                "availabilityId=" + availabilityId +
                ", availabilityWeekday=" + availabilityWeekday +
                ", availabilityStartHour=" + availabilityStartHour +
                ", availabilityStopHour=" + availabilityStopHour +
                '}';
    }
}
