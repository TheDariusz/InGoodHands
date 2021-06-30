package pl.coderslab.charity.donation;

import java.util.List;

public interface InstitutionService {
    List<Institution> fetchInstitutions();

    Institution fetchInstitutionByName(String name);

    void save(Institution institution);

}
