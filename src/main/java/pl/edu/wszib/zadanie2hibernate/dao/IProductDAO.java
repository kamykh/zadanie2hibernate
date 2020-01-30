package pl.edu.wszib.zadanie2hibernate.dao;

import pl.edu.wszib.zadanie2hibernate.models.Product;

import java.util.List;

public interface IProductDAO {
    void addProduct(Product product);
    void buy(int id, int amount);
    List<Object[]> showContent();
    List<Object[]> showByAmountLowerThan(int amount);
    List<Object[]> showWhatsSold();
    List<Object[]> showAmountsInCategories();
    void showProducers();
}
