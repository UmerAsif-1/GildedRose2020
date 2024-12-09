package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	@Test
	public void exampleTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}
	@Test
	public void testRegularItem_QualityDecreasesByOneBeforeSellIn() {
	    GildedRose inn = new GildedRose();
	    inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
	    inn.oneDay();
	    List<Item> items = inn.getItems();
	    assertEquals("Quality should decrease by 1", 19, items.get(0).getQuality());
	    assertEquals("SellIn should decrease by 1", 9, items.get(0).getSellIn());
	}
	@Test
	public void testRegularItem_QualityDoesNotGoBelowZero() {
	    GildedRose inn = new GildedRose();
	    inn.setItem(new Item("+5 Dexterity Vest", 1, 0));
	    inn.oneDay();
	    List<Item> items = inn.getItems();
	    assertEquals("Quality should not go below 0", 0, items.get(0).getQuality());
	}
	@Test
	public void testAgedBrie_QualityIncreasesByOne() {
	    GildedRose inn = new GildedRose();
	    inn.setItem(new Item("Aged Brie", 10, 30));
	    inn.oneDay();
	    List<Item> items = inn.getItems();
	    assertEquals("Quality should increase by 1", 31, items.get(0).getQuality());
	}
	@Test
	public void testAgedBrie_QualityDoesNotExceedFifty() {
	    GildedRose inn = new GildedRose();
	    inn.setItem(new Item("Aged Brie", 10, 50));
	    inn.oneDay();
	    List<Item> items = inn.getItems();
	    assertEquals("Quality should not exceed 50", 50, items.get(0).getQuality());
	}
	@Test
	public void testBackstagePasses_QualityIncreasesByTwoWhenSellInTenOrLess() {
	    GildedRose inn = new GildedRose();
	    inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20));
	    inn.oneDay();
	    List<Item> items = inn.getItems();
	    assertEquals("Quality should increase by 2", 22, items.get(0).getQuality());
	}

	@Test
	public void testBackstagePasses_QualityDropsToZeroAfterConcert() {
	    GildedRose inn = new GildedRose();
	    inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));
	    inn.oneDay();
	    List<Item> items = inn.getItems();
	    assertEquals("Quality should drop to 0", 0, items.get(0).getQuality());
	}
	@Test
	public void testSulfurasDoesNotChange() {
	    GildedRose inn = new GildedRose();
	    inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
	    inn.oneDay();
	    List<Item> items = inn.getItems();
	    assertEquals("Quality should remain 80 for Sulfuras", 80, items.get(0).getQuality());
	    assertEquals("SellIn should remain 0 for Sulfuras", 0, items.get(0).getSellIn());
	}
	
	@Test
	public void testAgedBrieDoesNotExceedFifty() {
	    GildedRose inn = new GildedRose();
	    inn.setItem(new Item("Aged Brie", 5, 50));
	    inn.oneDay();
	    List<Item> items = inn.getItems();
	    assertEquals("Quality should stay at 50 for Aged Brie", 50, items.get(0).getQuality());
	}
	@Test
	public void testBackstagePasses_QualityIncreasesByTwoAtTen() {
	    GildedRose inn = new GildedRose();
	    inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20));
	    inn.oneDay();
	    List<Item> items = inn.getItems();
	    assertEquals("Quality should increase by 2 when SellIn is 10 or less", 22, items.get(0).getQuality());
	}

	@Test
	public void testBackstagePasses_QualityIncreasesByThreeAtFive() {
	    GildedRose inn = new GildedRose();
	    inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20));
	    inn.oneDay();
	    List<Item> items = inn.getItems();
	    assertEquals("Quality should increase by 3 when SellIn is 5 or less", 23, items.get(0).getQuality());
	}
	@Test
	public void testRegularItem_QualityDecreasesByOneAfterSellIn() {
	    GildedRose inn = new GildedRose();
	    inn.setItem(new Item("+5 Dexterity Vest", 0, 10));  // SellIn is 0, quality is 10
	    inn.oneDay();
	    List<Item> items = inn.getItems();
	    assertEquals("Quality should decrease by 1 after SellIn passes", 9, items.get(0).getQuality());
	    assertEquals("SellIn should be negative", -1, items.get(0).getSellIn());
	}

}
