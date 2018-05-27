import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
This class handles the resulting xml file, creates the train objects based on the received data and fills the graph,
finding the path from one given station to the second
*/

public class GraphHandler {

    public static final Integer START = 1909;
    public static final Integer END = 1921;
    public List<Train> trains = new ArrayList<Train>();
    public ArrayList allPathes = new ArrayList();
    public ArrayList<ArrayList> rightPathes = new ArrayList();


    public void getTrains() {
        try {

            File fXmlFile = new File("src\\main\\resources\\trainlegs.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            try {
                dBuilder = dbFactory.newDocumentBuilder();
            } catch (ParserConfigurationException e2) {
                e2.printStackTrace();
            }
            Document doc = null;
            try {
                doc = dBuilder.parse(fXmlFile);
            } catch (SAXException e2) {
                e2.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }

            doc.getDocumentElement().normalize();


            NodeList nList = doc.getElementsByTagName("TrainLeg");

            System.out.println("----------------------------");



            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    Train newTrain = new Train();
                    trains.add(newTrain);
                    newTrain.setTrainid(Integer.parseInt(eElement.getAttribute("TrainId")));
                    newTrain.setDepartureStationId(Integer.parseInt(eElement.getAttribute("DepartureStationId")));
                    newTrain.setArrivalStationId(Integer.parseInt(eElement.getAttribute("ArrivalStationId")));
                    newTrain.setPrice(Float.parseFloat(eElement.getAttribute("Price")));
                    newTrain.setArrivalTimeString(LocalTime.parse(eElement.getAttribute("ArrivalTimeString")));
                    newTrain.setDepartureTimeString(LocalTime.parse(eElement.getAttribute("DepartureTimeString")));




                }
            }
            Graph graph = new Graph();

            for (Train train : trains) {
                graph.addEdge(train.getDepartureStationId(), train.getArrivalStationId());
            }

            LinkedList<Integer> visited = new LinkedList();
            visited.add(START);
            rightPathes = new GraphHandler().depthFirst(graph, visited);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

   /**
   A method that finds paths in a graph using the depth-search algorithm
   */
    private ArrayList depthFirst(Graph graph, LinkedList<Integer> visited) {

        ArrayList Path = new ArrayList();
        LinkedList<Integer> nodes = graph.adjacentNodes(visited.getLast());
        // examine adjacent nodes
        for (Integer node : nodes) {
            if (visited.contains(node)) {
                continue;
            }
            if (node.equals(END)) {

                visited.add(node);
                addPath(visited, Path);
                visited.removeLast();
                break;
            }

        }

        for (Integer node : nodes) {
            if (visited.contains(node) || node.equals(END)) {
                continue;
            }
            visited.addLast(node);
            depthFirst(graph, visited);
            visited.removeLast();
        }

        allPathes.add(Path);
        return allPathes;
    }

    private void addPath(LinkedList<Integer> visited, ArrayList Path) {

        for (Integer node : visited) {
            Path.add(node);
        }

    }

}


