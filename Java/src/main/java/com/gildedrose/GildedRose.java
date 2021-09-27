package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured Mana Cake";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);
        }
    }

    private void updateItemQuality(Item item) {
        boolean isExpired = item.sellIn < 1; // default = 0;
        int degradeRate = determineDegradeRate(item, isExpired);
        boolean doesDegrade = !item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES) && !item.name.equals(SULFURAS);



        if (doesDegrade) {
            adjustmentQuality(item, degradeRate);
        }

        if (item.name.equals(AGED_BRIE)) {
            adjustmentQuality(item, 1);

        }
        if (item.name.equals(BACKSTAGE_PASSES)) {
            adjustmentQuality(item, 1);

            if (item.sellIn < 11) {
                adjustmentQuality(item, 1);
            }

            if (item.sellIn < 6) {
                adjustmentQuality(item, 1);
            }
        }

        if (!item.name.equals(SULFURAS)) {
            item.sellIn = item.sellIn - 1;
        }

        if (isExpired) {
            if (item.name.equals(AGED_BRIE)) {
                adjustmentQuality(item, 1);
            } else if (item.name.equals(BACKSTAGE_PASSES)) {
                    item.quality = item.quality - item.quality;
            }
        }
    }

    private int determineDegradeRate(Item item, boolean isExpired) {
        final int baseDegradeRate = item.name.equals(CONJURED) ? -2 : -1;
        return isExpired ? baseDegradeRate * 2 : baseDegradeRate;
    }

    private void adjustmentQuality(Item item, int adjustment) {
        int newQuality = item.quality + adjustment;
        boolean isValidRange = newQuality <= 50 && newQuality >= 0;
        if (isValidRange) {
            item.quality = newQuality;
        }
    }
}
