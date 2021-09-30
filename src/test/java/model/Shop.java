package model;

public class Shop {

  private String city;
  private String country;
  private int id;
  private String name;

    public Shop(String city, String country, int id, String name) {
        this.city = city;
        this.country = country;
        this.id = id;
        this.name = name;
    }

    public Shop() {
      super();

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
