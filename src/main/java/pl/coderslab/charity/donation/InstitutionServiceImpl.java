package pl.coderslab.charity.donation;

import lombok.RequiredArgsConstructor;
import pl.coderslab.charity.donation.repository.InstitutionRepository;
import pl.coderslab.charity.donation.repository.entity.InstitutionEntity;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final InstitutionMapper institutionMapper;

    @Override
    public List<Institution> fetchInstitutions() {
        final List<InstitutionEntity> institutionEntities = institutionRepository.findAll();
        return institutionEntities.stream()
                .map(institutionMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public InstitutionEntity fetchInstitutionByName(String name) {
        return institutionRepository.findByName(name);
    }

    @Override
    public void save(Institution institution) {
        final InstitutionEntity institutionEntity = institutionMapper.toEntity(institution);
        institutionRepository.save(institutionEntity);
    }

    @Override
    public InstitutionEntity fetchInstitution(Institution institution) {
        return institutionRepository.findByName(institution.getName());
    }
}
