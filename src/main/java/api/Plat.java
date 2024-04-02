package api;

public class Plat {
    private String id;
    private String name;
    private String description;
    private Double price;
    public Plat(String id, String name, String description, Double price){
        this.id= id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }
}