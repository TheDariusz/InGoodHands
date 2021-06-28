package pl.coderslab.charity.donation;

import pl.coderslab.charity.donation.repository.entity.CategoryEntity;

public class CategoryMapper {

    public Category toModel(CategoryEntity entity) {
        return new Category(
                entity.getName()
        );
    }

    public CategoryEntity toEntity(Category category) {
        return new CategoryEntity(
                category.getName()
        );
    }
}
