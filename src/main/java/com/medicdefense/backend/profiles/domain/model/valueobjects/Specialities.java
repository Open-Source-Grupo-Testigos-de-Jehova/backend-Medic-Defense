package com.medicdefense.backend.profiles.domain.model.valueobjects;

import com.medicdefense.backend.profiles.domain.model.entities.SpecialityItems;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Specialities {

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<SpecialityItems> specialityItems;

    public Specialities(List<SpecialityItems> specialityItems) {
        this.specialityItems = specialityItems;
    }

    public Specialities() {
        this.specialityItems = new ArrayList<>();
    }

    public void addSpecialityItem(String specialityItem) {
        this.specialityItems.add(new SpecialityItems(specialityItem));
    }

    public List<SpecialityItems> getSpecialityItems() {
        return specialityItems;
    }

    public SpecialityItems getSpecialityItemById(Long id) {
        for (SpecialityItems specialityItem : specialityItems) {
            if (specialityItem.getId().equals(id)) {
                return specialityItem;
            }
        }
        return null;
    }

    public boolean hasSpecialities() {
        return !specialityItems.isEmpty();
    }

    public SpecialityItems getSpecialityByName(String name) {
        for (SpecialityItems specialityItem : specialityItems) {
            if (specialityItem.getName().equals(name)) {
                return specialityItem;
            }
        }
        return null;
    }


}
