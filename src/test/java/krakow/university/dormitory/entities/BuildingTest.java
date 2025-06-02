package krakow.university.dormitory.entities;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BuildingTest {

    @Test
    void testGettersAndSetters() {
        Building building = new Building();

        building.setBuildingId(10);
        building.setBuildingName("Main Office");
        building.setBuildingAddress("1234 Elm Street");
        building.setBuildingIsActive(1);

        assertThat(building.getBuildingId()).isEqualTo(10);
        assertThat(building.getBuildingName()).isEqualTo("Main Office");
        assertThat(building.getBuildingAddress()).isEqualTo("1234 Elm Street");
        assertThat(building.getBuildingIsActive()).isEqualTo(1);
    }

    @Test
    void testToString() {
        Building building = new Building();

        building.setBuildingId(10);
        building.setBuildingName("Main Office");
        building.setBuildingAddress("1234 Elm Street");
        building.setBuildingIsActive(1);

        String expected = "Building{buildingId=10, buildingName='Main Office', buildingAddress='1234 Elm Street', buildingIsActive=1}";
        assertThat(building.toString()).isEqualTo(expected);
    }
}

