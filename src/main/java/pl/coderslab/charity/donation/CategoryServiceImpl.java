package pl.coderslab.charity.donation;

import lombok.RequiredArgsConstructor;
import pl.coderslab.charity.donation.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public List<Category> fetchCategories() {
        return repository.findAll().stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }
}
