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
        int degradeRate = item.name.equals(CONJURED) ? -2 : -1;
        if (!item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES)) {
            if (!item.name.equals(SULFURAS)) {
                adjustmentQuality(item, degradeRate);
            }
        } else {
            adjustmentQuality(item, 1);

            if (item.name.equals(BACKSTAGE_PASSES)) {
                if (item.sellIn < 11) {
                    adjustmentQuality(item, 1);
                }

                if (item.sellIn < 6) {
                    adjustmentQuality(item, 1);
                }
            }
        }

        if (!item.name.equals(SULFURAS)) {
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            if (!item.name.equals(AGED_BRIE)) {
                if (!item.name.equals(BACKSTAGE_PASSES)) {
                    if (!item.name.equals(SULFURAS)) {
                        adjustmentQuality(item, degradeRate);
                    }
                } else {
                    item.quality = item.quality - item.quality;
                }
            } else {
                adjustmentQuality(item, 1);
            }
        }
    }

    private void adjustmentQuality(Item item, int adjustment) {
        int newQuality = item.quality + adjustment;
        boolean isValidRange = newQuality <= 50 && newQuality >= 0;
        if (isValidRange) {
            item.quality = newQuality;
        }
    }
}
