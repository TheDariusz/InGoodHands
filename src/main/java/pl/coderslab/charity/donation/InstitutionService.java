package pl.coderslab.charity.donation;

import pl.coderslab.charity.donation.repository.entity.InstitutionEntity;

import java.util.List;

public interface InstitutionService {
    List<Institution> fetchInstitutions();

    InstitutionEntity fetchInstitutionByName(String name);

    void save(Institution institution);

}
