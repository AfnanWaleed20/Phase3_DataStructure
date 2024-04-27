package application;
//this class about Location imformation
public class Location {
	private String Location;
	private AvlTree avl1=new AvlTree();
	private AvlTreeTwo tree=new AvlTreeTwo();
	
	public AvlTree getAvl1() {
		return avl1;
	}

	public void setAvl1(AvlTree avl1) {
		this.avl1 = avl1;
	}

	public Location() {
		super();
		
		
	}
	
	public Location(String location) {
		Location = location;
	}

	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}

	@Override
	public String toString() {
		return "Location=" + Location ;
	}

	public AvlTreeTwo getTree() {
		return tree;
	}

	public void setTree(AvlTreeTwo tree) {
		this.tree = tree;
	}
	
	

}
