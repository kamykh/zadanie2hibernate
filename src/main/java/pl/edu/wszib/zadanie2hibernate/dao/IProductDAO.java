package pl.edu.wszib.zadanie2hibernate.dao;

import pl.edu.wszib.zadanie2hibernate.models.Product;

import java.util.List;

public interface IProductDAO {
    void addProduct(Product product);
    void buy(int id, int amount);
    List<Product> showContent();
    List<Product> showByAmountLowerThan(int amount);
    List<Product> showWhatsSold();
    List<Product> showAmountsInCategories();
    List<Product> showProducers();
}
