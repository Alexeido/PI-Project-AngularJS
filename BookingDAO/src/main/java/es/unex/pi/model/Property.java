package es.unex.pi.model;
//alojamiento
public class Property {

	private long id;
	private String name;
	private String address;
	private String telephone;
	private double gradesAverage;
	private String city;
	private double centerDistance;
	private String description;
	private int petFriendly;
	private int available;
	private int idu;
    private int restaurante;
    private int desayuno;
    private int wifi;
    private int gym;
    private int piscina;
    private int spa;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getGradesAverage() {
		return gradesAverage;
	}
	public void setGradesAverage(double gradesAverage) {
		this.gradesAverage = gradesAverage;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public int getIdu() {
		return idu;
	}
	public void setIdu(int idu) {
		this.idu = idu;
	}
	public double getCenterDistance() {
		return centerDistance;
	}
	public void setCenterDistance(double centerDistance) {
		this.centerDistance = centerDistance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPetFriendly() {
		return petFriendly;
	}
	public void setPetFriendly(int petFriendly) {
		this.petFriendly = petFriendly;
	}
	public int getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(int restaurante) {
        this.restaurante = restaurante;
    }

    public int getDesayuno() {
        return desayuno;
    }

    public void setDesayuno(int desayuno) {
        this.desayuno = desayuno;
    }

    public int getWifi() {
        return wifi;
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    public int getGym() {
        return gym;
    }

    public void setGym(int gym) {
        this.gym = gym;
    }

    public int getPiscina() {
        return piscina;
    }

    public void setPiscina(int piscina) {
        this.piscina = piscina;
    }

    public int getSpa() {
        return spa;
    }

    public void setSpa(int spa) {
        this.spa = spa;
    }
	
	
}
