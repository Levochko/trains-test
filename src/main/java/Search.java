import java.util.*;

public class Search {


    public static void main(String[] args) {

        GraphHandler newHandler = new GraphHandler();
        newHandler.getTrains();
        System.out.println("--------Print all paths from station " + GraphHandler.START + " to station " + GraphHandler.END + "------------");
        System.out.println(" ");
        getAllTrains(newHandler.rightPathes, newHandler.trains);

    }

    /**
     Print all ways from START to END
     */
    public static void getAllTrains(ArrayList<ArrayList> rightPathes, List<Train> trains) {
        for (int i = 0; i < rightPathes.size(); i++) {

            Search.findTrains(rightPathes.get(i), trains);

        }

    }

    /**
     Finds and prints the way by picking up trains for transfer
     */
    public static void findTrains(ArrayList rightPath, List<Train> trains){
        ArrayList road = rightPath;
        LinkedList<Train> checkedTrains = new LinkedList<Train>();
        LinkedList<Train> tempTrains = new LinkedList<Train>();
        float roadPrice = 0;
        Integer numberOfTransfer = 0;

        for (int j = 0; j < road.size(); j++)
        {
            for (Train train : trains) {
                Integer trainDS = train.getDepartureStationId();
                Integer trainAS = train.getArrivalStationId();

                if (!road.get(j).equals(GraphHandler.END) && trainDS.equals(road.get(j)) &&  trainAS.equals(road.get(j+1)))
                {
                    tempTrains.add(train);
                }}
        }

        if(tempTrains.size() > 0) {
            checkedTrains.add(tempTrains.getFirst());
        }
        for(Train train : tempTrains ){

            if (checkedTrains.getLast().getArrivalStationId().equals(train.getDepartureStationId())
                    && checkedTrains.getLast().getArrivalTimeString().isBefore(train.getDepartureTimeString())){
                checkedTrains.add(train);
                numberOfTransfer++;
            }
        }

        if(checkedTrains.size()>0 && checkedTrains.getLast().getArrivalStationId().equals(GraphHandler.END) && numberOfTransfer!=0) {
            for (Train train : checkedTrains) {
                roadPrice+= train.getPrice();
                System.out.println("Id " + train.getTrainid() + " DS " + train.getDepartureStationId() + " AS " + train.getArrivalStationId() +
                        " DEPARTURE TIME " + train.getDepartureTimeString() + " ARRIVAL TIME " + train.getArrivalTimeString() + " PRICE: " + train.getPrice() );
            }
            System.out.println("___________Total Price: " + roadPrice + " Number of transfer: " + numberOfTransfer + "______________");
            System.out.println(" ");
        }

        if (checkedTrains.size()>0 && checkedTrains.getLast().getArrivalStationId().equals(GraphHandler.END) && numberOfTransfer==0){
            for (Train train : checkedTrains) {
                System.out.println("Id " + train.getTrainid() + " DS " + train.getDepartureStationId() + " AS " + train.getArrivalStationId() +
                        " DEPARTURE TIME " + train.getDepartureTimeString() + " ARRIVAL TIME " + train.getArrivalTimeString());
                System.out.println("___________Total Price: " + train.getPrice() + " Number of transfer: No transfer, straight way");
                System.out.println(" ");

            }

        }
    }

}

