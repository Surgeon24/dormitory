package krakow.university.dormitory.dto;

public class AvailabilityDTO {
    private Integer dayOfWeek;
    private Integer startTime;
    private Integer stopTime;

    public void setDayOfWeek(Integer dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }

    public void setStartTime(Integer start){
        this.startTime = start;
    }

    public void setStopTime(Integer stop){
        this.stopTime = stop;
    }

    public Integer getDayOfWeek(){
        return dayOfWeek;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public Integer getStopTime() {
        return stopTime;
    }
}
