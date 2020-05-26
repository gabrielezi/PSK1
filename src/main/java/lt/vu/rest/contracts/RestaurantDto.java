package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.vu.entities.Restaurant;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantDto {
    private Integer id;
    private String name;

    public RestaurantDto(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
    }
}
