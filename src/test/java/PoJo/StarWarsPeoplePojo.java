package PoJo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true)

public class StarWarsPeoplePojo {
private String name;
private String height;
private String mass;
private String gender;
private List<String> films;
private List<String> vehicles;


}