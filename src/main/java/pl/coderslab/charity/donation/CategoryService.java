package pl.coderslab.charity.donation;

import pl.coderslab.charity.donation.repository.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<Category> fetchCategories();

    CategoryEntity findCategoryByName(String name);
}
