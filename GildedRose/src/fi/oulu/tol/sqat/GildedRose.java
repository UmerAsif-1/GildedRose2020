package fi.oulu.tol.sqat;

import java.util.ArrayList;
import java.util.List;


public class GildedRose {

	private static List<Item> items = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
        System.out.println("OMGHAI!");
		
        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));

        updateQuality();
}


	
    public static void updateQuality()
    {
    	for (int i = 0; i < items.size(); i++) {
    	    Item item = items.get(i);

    	    // Skip logic for Sulfuras
    	    if ("Sulfuras, Hand of Ragnaros".equals(item.getName())) {
    	        continue;  // Skip the current iteration for Sulfuras
    	    }

    	    // Update SellIn for all items except Sulfuras
    	    item.setSellIn(item.getSellIn() - 1);

    	    // Handle the item based on its name
    	    if (item.getSellIn() < 0) {
    	        // Item has passed its sell-by date
    	        if ("Aged Brie".equals(item.getName())) {
    	            if (item.getQuality() < 50) {
    	                item.setQuality(item.getQuality() + 1);  // Aged Brie increases in quality
    	            }
    	        } else if ("Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
    	            item.setQuality(0);  // Backstage passes quality drops to 0 after the concert
    	        } else {
    	            if (item.getQuality() > 0) {
    	                item.setQuality(item.getQuality() - 1);  // Decrease quality for regular items
    	            }
    	        }
    	    } else {
    	        // Item has not passed the sell-by date
    	        if ("Aged Brie".equals(item.getName())) {
    	            if (item.getQuality() < 50) {
    	                item.setQuality(item.getQuality() + 1);  // Aged Brie increases in quality
    	            }
    	        } else if ("Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
    	            if (item.getSellIn() <= 10 && item.getQuality() < 50) {
    	                item.setQuality(item.getQuality() + 1);  // Backstage passes increase by 2 when sell-in <= 10
    	            }
    	            if (item.getSellIn() <= 5 && item.getQuality() < 50) {
    	                item.setQuality(item.getQuality() + 1);  // Backstage passes increase by 3 when sell-in <= 5
    	            }
    	        } else {
    	            if (item.getQuality() > 0) {
    	                item.setQuality(item.getQuality() - 1);  // Decrease quality for regular items
    	            }
    	        }
    	    }
    	}
    }

    //constructor
    public GildedRose() {
    	items = new ArrayList<Item>();
    }
    
    //getter
    public List<Item> getItems() {
    	return items;
    }
    //setter
    public void setItem(Item item) {
    	items.add(item);
    }
    
    //update one day
    public void oneDay() {
    	updateQuality();
    }
}
