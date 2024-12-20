package es.unex.pi.model;

//habitaciones de cada alojamiento
public class Accommodation {
	
	private long id;
	private String name;
	private int price;
	private String description;
	private long idp;
	private int numAccommodations;
	private Property prop;
	
	public long getIdp() {
		return idp;
	}
	public void setIdp(long idp) {
		this.idp = idp;
	}
	public int getNumAccommodations() {
		return numAccommodations;
	}
	public void setNumAccommodations(int numAccom) {
		this.numAccommodations = numAccom;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Property getProp() {
		return prop;
	}
	public void setProp(Property prop) {
		this.prop = prop;
	}

}
