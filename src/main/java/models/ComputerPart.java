package models;

import javax.persistence.*;

@Entity
@Table(name = "computer_parts" )
public class ComputerPart {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
@Column (name = "id")
private int id;

@Column(name = "name")
private String name;

@Column(name = "isNecessary")
private boolean isNecessary;

@Column(name = "quantity")
private int quantity;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsNecessary() {
        return isNecessary;
    }

    public void setNecessary(boolean necessary) {
        isNecessary = necessary;
    }

    public void setIsNecessaty(boolean necessary) {
        isNecessary = necessary;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setId(int id){
        this.id = id;
    }

    public ComputerPart(){
    }

    public ComputerPart(String name, boolean isNecessary, int quantity) {
        this.name = name;
        this.isNecessary = isNecessary;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ComputerPart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isNecessary=" + isNecessary +
                ", quantity=" + quantity +
                '}';
    }

    public ComputerPart(ComputerPartWrapper part){
        this.setQuantity(part.getQuantity());
        this.setName(part.getName());
        this.setId(part.getId());
        this.isNecessary=Boolean.parseBoolean(part.getIsNecessary());
    }
}
