package hw9;

import hw8.BuildingParser;
import hw8.Node;
import hw8.PathsPaser;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

public class CampusPathsMain {
	/**
	 * This class is used to represent the UW Campus Map. it marks out the
	 * selected start building and selected end building, and then prints out
	 * the shortest path between two selected buildings.
	 */
	private static JFrame frame;
	static Map<String, String> shortToFull;
	static Map<String, Node> coordinate;
	private static Map<Node, Map<Node, Double>> campusMap;
	public static final String campusBuilding = "src/hw8/data/campus_buildings.dat";
	public static final String campusPath = "src/hw8/data/campus_paths.dat";

	public static void main(String[] args) {
		shortToFull = new HashMap<String, String>();
		coordinate = new HashMap<String, Node>();
		campusMap = new HashMap<Node, Map<Node, Double>>();

		// load UW campus map data
		try {
			BuildingParser.parseData(campusBuilding, shortToFull, coordinate);
			PathsPaser.parseData(campusPath, campusMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// create the window frame
		frame = new JFrame();
		MapPanel panel = new MapPanel();
		frame.setPreferredSize(new Dimension(1024, 768));
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

}
