package Menu.WebTest;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Item> dataOrdered = new ArrayList<Item>();
    private int totalPrice;

    public List<Item> getDataOrdered() {
        return dataOrdered;
    }

    public void setDataOrdered(List<Item> dataOrdered) {
        this.dataOrdered = dataOrdered;
    }

    public void addItemOrder (Item product){
        dataOrdered.add(product);
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void calculateTotalPrice(){
        totalPrice = 0;
        for(int idx=0; idx<dataOrdered.size(); idx++){
            totalPrice = totalPrice + dataOrdered.get(idx).getPrice();
        }
    }
}
