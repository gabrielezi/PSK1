package lt.vu.rest;

import lt.vu.entities.Restaurant;
import lt.vu.persistence.RestaurantDAO;
import lombok.Getter;
import lombok.Setter;
import lt.vu.rest.contracts.RestaurantDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/restaurants")
public class RestaurantsController {
    @Inject
    @Setter
    @Getter
    private RestaurantDAO restaurantDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Restaurant restaurant = restaurantDAO.findOne(id);
        if (restaurant == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setId(restaurant.getId());

        return Response.ok(restaurantDto).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create (RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDto.getName());
        restaurantDAO.persist(restaurant);
        return Response.ok().build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update (@PathParam("id") final Integer id, RestaurantDto restaurantDto) {
        try {
            Restaurant localRestaurant = restaurantDAO.findOne(id);
            if (localRestaurant == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            localRestaurant.setName(restaurantDto.getName());
            restaurantDAO.update(localRestaurant);
            return Response.ok().build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
