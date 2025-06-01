package krakow.university.dormitory.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "buildings")
public class Building {
    @Id
    @Column(name = "building_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer buildingId;

    @Column(name = "building_name")
    private String buildingName;

    @Column(name = "building_address")
    private String buildingAddress;

    @Column(name = "building_is_active")
    private Integer buildingIsActive;

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingAddress() {
        return buildingAddress;
    }

    public void setBuildingAddress(String buildingAddress) {
        this.buildingAddress = buildingAddress;
    }

    public Integer getBuildingIsActive() {
        return buildingIsActive;
    }

    public void setBuildingIsActive(Integer buildingIsActive) {
        this.buildingIsActive = buildingIsActive;
    }

    @Override
    public String toString() {
        return "Building{" +
                "buildingId=" + buildingId +
                ", buildingName='" + buildingName + '\'' +
                ", buildingAddress='" + buildingAddress + '\'' +
                ", buildingIsActive=" + buildingIsActive +
                '}';
    }
}
