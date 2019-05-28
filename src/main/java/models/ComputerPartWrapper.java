package models;

public class ComputerPartWrapper {
    private int id;

    private String name;

    private String isNecessary;

    private int quantity;

    public ComputerPartWrapper(int id, String name, String isNecessary, int quantity) {
        this.id = id;
        this.name = name;
        this.isNecessary = isNecessary;
        this.quantity = quantity;
    }
    public ComputerPartWrapper(){

    }

    public ComputerPartWrapper(ComputerPart part){
        this.id= part.getId();
        this.name = part.getName();
        this.quantity = part.getQuantity();
        if (part.getIsNecessary()){
            this.isNecessary="true";
        }
        else{
            this.isNecessary="false";
        }

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

    public String getIsNecessary() {
        return isNecessary;
    }

    public void setIsNecessary(String isNecessary) {
        this.isNecessary = isNecessary;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
