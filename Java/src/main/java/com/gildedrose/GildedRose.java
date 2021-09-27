package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateQuality(item);
            updateSellIn(item);
        }
    }

    private void updateQuality(Item item ) {
        if (!item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            decreaseQualityIfItemHasQuality(item);
        } else {
            increaseQualityIncludingBackstagePasses(item);
        }
    }

    private void decreaseQualityIfItemHasQuality(Item item) {
        if (item.quality > 0) {
            decreaseQuality(item);
        }
    }

    private void decreaseQuality(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.quality = item.quality - 1;
        }
    }

    private void increaseQualityIncludingBackstagePasses(Item item) {
        if (item.quality < 50) {
            increaseQuality(item);
            increaseQualityOfBackstagePasses(item);
        }
    }

    private void increaseQualityOfBackstagePasses(Item item) {
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            increaseQualityIfFarFromExpiry(item);
            increaseQualityIfCloseToExpiry(item);
        }
    }

    private void increaseQualityIfCloseToExpiry(Item item) {
        if (item.sellIn < 6) {
            increaseQualityIfNotMax(item);
        }
    }

    private void increaseQualityIfFarFromExpiry(Item item) {
        if (item.sellIn < 11) {
            increaseQualityIfNotMax(item);
        }
    }

    private void increaseQualityIfNotMax(Item item) {
        if (item.quality < 50) {
            increaseQuality(item);
        }
    }

    private void increaseQuality(Item item) {
        item.quality = item.quality + 1;
    }

    private void updateSellIn(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn = item.sellIn - 1;
        }
        handleIfExpired(item);
    }

    private void handleIfExpired(Item item) {
        if (item.sellIn < 0) {
            handleExpired(item);
        }
    }

    private void handleExpired(Item item) {
        if (!item.name.equals("Aged Brie")) {
            handleExpiredBackstagePasses(item);
        } else {
            increaseQualityIfNotMax(item);
        }
    }

    private void handleExpiredBackstagePasses(Item item) {
        if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            decreaseQualityIfItemHasQuality(item);
        } else {
            item.quality = item.quality - item.quality;
        }
    }
}
