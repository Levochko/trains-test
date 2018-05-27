import java.time.LocalTime;

public class Train {

    private int trainid;
    private Integer departureStationId;
    private Integer arrivalStationId;
    private float price;
    private LocalTime ArrivalTimeString;
    private LocalTime DepartureTimeString;

    public int getTrainid() {
        return trainid;
    }

    public void setTrainid(int trainid) {
        this.trainid = trainid;
    }

    public Integer getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(Integer arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public Integer getDepartureStationId() {

        return departureStationId;
    }

    public void setDepartureStationId(Integer departureStationId) {
        this.departureStationId = departureStationId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalTime getArrivalTimeString() {
        return ArrivalTimeString;
    }

    public void setArrivalTimeString(LocalTime arrivalTimeString) {
        ArrivalTimeString = arrivalTimeString;
    }

    public LocalTime getDepartureTimeString() {
        return DepartureTimeString;
    }

    public void setDepartureTimeString(LocalTime departureTimeString) {
        DepartureTimeString = departureTimeString;
    }


}
