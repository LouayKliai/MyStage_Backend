package src.DTO.Offer;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.utils.OfferType;
import src.utils.Specialite;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OfferUpdateDTO {
    private String title;
    private String description;
    private int duree;
    private OfferType type;
    private List<String> qualification;
}

