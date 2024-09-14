package PoJo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true)
public class PetsPojo {
    private int id;
    private PetsCategoryPojo category;
    private String name;
    private List <String> photoUrls;
    private List<PetsTagsPojo> tags;
    private String status;

}
