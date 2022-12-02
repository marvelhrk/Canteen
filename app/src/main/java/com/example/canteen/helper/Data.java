package com.example.canteen.helper;

import com.example.canteen.models.Category;
import com.example.canteen.models.Offer;
import com.example.canteen.models.Product;

import java.util.ArrayList;
import java.util.List;

public class Data {
    List<Category> categoryList = new ArrayList<>();
    List<Product> specialList = new ArrayList<>();
    List<Product> beveragesList = new ArrayList<>();
    List<Product> chipsList = new ArrayList<>();
    List<Product> newList = new ArrayList<>();
    List<Product> popularList = new ArrayList<>();
    List<Offer> offerList = new ArrayList<>();

    public List<Category> getCategoryList() {
        Category category = new Category("1", "Fast Food", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2FCategories%2Ffast%20food.png?alt=media&token=f37355ac-04a6-4a9c-8340-76bc8cb81172");
        categoryList.add(category);
        category = new Category("2", "Chips", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2FCategories%2Fchips.png?alt=media&token=9b3f94bf-e441-4ac9-acfc-1bcbdd964e60");
        categoryList.add(category);
        category = new Category("3", "Juices", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2FCategories%2Fjuice.png?alt=media&token=76ee6226-3494-4fcc-bba3-c394fb5d746e");
        categoryList.add(category);
        category = new Category("4", "Soft Drinks", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2FCategories%2Fsoft%20drink.png?alt=media&token=e61a21c1-e01b-4418-894c-ac55713437d6");
        categoryList.add(category);
        category = new Category("5", "Sweets", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2FCategories%2Fchocloates.png?alt=media&token=282a0fb9-1c5f-4317-bbe5-ebad6f0b6114");
        categoryList.add(category);
        category = new Category("6", "Dairy", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2FCategories%2Fdairy.png?alt=media&token=6e376784-1b97-4a29-88e4-16478a202408");
        categoryList.add(category);
        return categoryList;
    }

//    public List<Product> getProductList() {
//        Product product = new Product("1", "1", "Apple", "", "1 Kg", "Rs.", "20", "10% OFF", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("2", "1", "Banana", "", "1 Bounch", "Rs.", "10", "20% OFF", "https://images-na.ssl-images-amazon.com/images/I/21DejQuoT2L.jpg");
//        productList.add(product);
//        product = new Product("3", "2", "House Clean Liquid", "", "1 Lit.", "Rs.", "25", "", "http://sunsetcleaningcia.com/wp-content/uploads/2016/05/houseclean.png");
//        productList.add(product);
//        product = new Product("4", "2", "House Clean Brush", "", "1 Piece", "Rs.", "10", "", "https://www.clean-hoouse.com/wp-content/uploads/2017/09/13.png");
//        productList.add(product);
//        product = new Product("5", "3", "Pampers", "", "1 Piece", "Rs.", "20", "10% OFF", "https://cdn.bmstores.co.uk/images/hpcProductImage/imgFull/311448-Pampers-Baby-Dry-Size-4-Maxi-251.jpg");
//        productList.add(product);
//        product = new Product("6", "3", "Baby Oil", "", "500 Ml", "Rs.", "31", "", "https://www.fortunaonline.net/media/catalog/product/cache/1/small_image/295x/040ec09b1e35df139433887a97daa66f/n/k/nkbcp12_-_xia-shib-ao-baby-oil-200ml.png");
//        productList.add(product);
//        product = new Product("7", "4", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("8", "4", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("9", "5", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("10", "5", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("11", "6", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("12", "6", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("13", "1", "Litche", "", "1 Kg", "Rs.", "20", "30%OFF", "https://cdn.shopify.com/s/files/1/0665/4989/products/lichee-485x400_grande.jpg");
//        productList.add(product);
//        return productList;
//    }

    public List<Product> getSpecial() {
        Product product = new Product("1", "1", "Fries", "", "1 Plate", "Rs.", "40", "10% OFF", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2Ffast%20food%2Ffries.png?alt=media&token=e9cdbb46-c4fa-4647-b932-7455a1d4bc03");
        specialList.add(product);
        product = new Product("2", "1", "Samose", "", "1 Piece", "Rs.", "7", "20% OFF", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2Ffast%20food%2Fsamose.png?alt=media&token=c4fd5002-14ce-4f5a-934f-59f4b924d804");
        specialList.add(product);
        product = new Product("3", "1", "Momos", "", "5 Piece.", "Rs.", "30", "", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2Ffast%20food%2Fmomos.png?alt=media&token=71f3efa1-ccd0-4d15-85ee-1cd58fbf034e");
        specialList.add(product);
        product = new Product("4", "1", "Noodles", "", "1 Plate", "Rs.", "30", "", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2Ffast%20food%2Fnoodles.png?alt=media&token=52b92177-e464-45b4-b133-b61df6fe26bb");
        specialList.add(product);
        product = new Product("5", "1", "Fried Rice", "", "1 Plate", "Rs.", "30", "10% OFF", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2Ffast%20food%2Ffried%20rice.png?alt=media&token=877e3984-68ea-4a9a-bc9d-8e6fcadb5e8d");
        specialList.add(product);
        return specialList;
    }

    public List<Product> getPopularList() {
        Product product = new Product("6", "3", "Baby Oil", "", "500 Ml", "Rs.", "31", "", "https://www.fortunaonline.net/media/catalog/product/cache/1/small_image/295x/040ec09b1e35df139433887a97daa66f/n/k/nkbcp12_-_xia-shib-ao-baby-oil-200ml.png");
        popularList.add(product);
        product = new Product("7", "4", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
        popularList.add(product);
        product = new Product("8", "4", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
        popularList.add(product);
        product = new Product("9", "5", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
        popularList.add(product);
        product = new Product("10", "5", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
        popularList.add(product);
        product = new Product("11", "6", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
        popularList.add(product);
        product = new Product("12", "6", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
        popularList.add(product);
        product = new Product("13", "1", "Litche", "", "1 Kg", "Rs.", "20", "30%OFF", "https://cdn.shopify.com/s/files/1/0665/4989/products/lichee-485x400_grande.jpg");
        popularList.add(product);
        return popularList;
    }
    public List<Product> getBeverages() {
        Product product = new Product("6", "3", "Pepsi", "", "300 ML", "Rs.", "31", "", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2Fbeverages%2Fpepsi.png?alt=media&token=6aed65be-34eb-40e1-87c6-e5f951c44067");
        beveragesList.add(product);
        product = new Product("7", "3", "Sprite", "", "1 Ltr", "Rs.", "40", "", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2Fbeverages%2Fsprite.png?alt=media&token=c4f25c75-0795-49c3-9f8a-ffb1340e8eaa");
        beveragesList.add(product);
        product = new Product("8", "4", "Slice", "", "1 Ltr", "Rs.", "30", "", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2Fbeverages%2Fslice.png?alt=media&token=da76e264-5a25-4ca8-a615-221680ddc6fb");
        beveragesList.add(product);
        product = new Product("9", "5", "Amul Kool", "", "200 ML", "Rs.", "20", "", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2Fbeverages%2Famul%20kool.png?alt=media&token=5df1d050-8200-4f84-abb2-525bcfd33e08");
        beveragesList.add(product);
        product = new Product("10", "5", "Cocunut Water", "", "200 Ml", "Rs.", "20", "", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2Fbeverages%2Fcocout%20water.png?alt=media&token=79e6bb31-d096-4dd6-a370-fc3dabf2a324");
        beveragesList.add(product);
        return beveragesList;
    }

    public List<Product> getChips() {
        Product product = new Product("6", "3", "Lays", "", "300 g", "Rs.", "20", "", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2Fchips%2Flays-potato-chips-pack-115283315-removebg-preview%20(1).png?alt=media&token=9c05b02a-e628-4f26-8dc6-d79acc3e4013");
        chipsList.add(product);
        product = new Product("7", "4", "Bhujia", "", "300 g", "Rs.", "20", "", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2Fchips%2Fbhujia.png?alt=media&token=6f6225bf-2644-47c3-8de5-da182f95a429");
        chipsList.add(product);
        product = new Product("8", "4", "Kurkure", "", "300 g", "Rs.", "20", "", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2Fchips%2Fkurkure.png?alt=media&token=406b96f4-1088-4c27-8809-8f8e716f912c");
        chipsList.add(product);
        product = new Product("9", "5", "Khatta Meetha", "", "300 g", "Rs.", "20", "", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2Fchips%2Fkhattameetha.png?alt=media&token=d07fbf0c-c50b-4513-aa65-c74ecdb04147");
        chipsList.add(product);
        product = new Product("10", "3", "Lays", "", "300 g", "Rs.", "20", "", "https://firebasestorage.googleapis.com/v0/b/canteen-e07d4.appspot.com/o/Resorces%2Fchips%2Flays.png?alt=media&token=35da72ee-33c3-4eb2-8156-6945b5658302");
        chipsList.add(product);
        return chipsList;
    }

    public List<Offer> getOfferList() {
        Offer offer = new Offer("http://1.bp.blogspot.com/-MMt-60IWEdw/VqZsh45Dv8I/AAAAAAAAMHg/70D9tVowlyc/s1600/askmegrocery-republic-day-offer.jpg");
        offerList.add(offer);
        offer = new Offer("http://www.lootkaro.com/wp-content/uploads/2016/05/as1.jpg");
        offerList.add(offer);
        offer = new Offer("https://453xbcknr3t3ahr522mms518-wpengine.netdna-ssl.com/wp-content/themes/iga-west/images/banner-save-more.jpg");
        offerList.add(offer);
        offer = new Offer("https://images-eu.ssl-images-amazon.com/images/G/31/img16/Grocery/SVD/July18/750x375.png");
        offerList.add(offer);
        offer = new Offer("https://images-eu.ssl-images-amazon.com/images/G/31/img16/Grocery/BreakfastStore/kmande_2018-06-15T12-00_f010a5_1121973_grocery_750x375.jpg");
        offerList.add(offer);
        offer = new Offer("http://www.enjoygrocery.com/images/enjoygrocery-offer.jpg");
        offerList.add(offer);


        return offerList;
    }
}