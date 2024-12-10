// Abdul Reeves - Matthew (added overloaded constructor)
public class Product {
	private String pName;
	private String pDesc;
	private double price;
	private String pChanging;


	Product(String pName, String pDesc, double price){
		this.pName = pName;
		this.pDesc = pDesc;
		this.price = price;
	}

	Product(String pName, String pDesc, double price, String pChanging){
		this.pName = pName;
		this.pDesc = pDesc;
		this.price = price;
		this.pChanging = pChanging;
	}


	public String getPname() {
		return pName;
	}

	public double getPrice() {
		return this.price;
	}
	
	public String getDesc() {
		return this.pDesc;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setPname(String pName) {
		this.pName = pName;
	}
	
	public void setPdesc(String pDesc) {
		this.pDesc = pDesc;
	}

	public String getPchanging(){
		return this.pChanging;
	}
}
