package pl.coderslab.charity.donation;

import lombok.RequiredArgsConstructor;
import pl.coderslab.charity.donation.repository.InstitutionRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final InstitutionMapper institutionMapper;

    @Override
    public List<Institution> fetchInstitutions() {
        return institutionRepository.findAll().stream()
                .map(institutionMapper::toModel)
                .collect(Collectors.toList());
    }
}
