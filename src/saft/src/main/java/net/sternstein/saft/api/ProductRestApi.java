package net.sternstein.saft.api;

import net.sternstein.saft.model.dto.product.CreateProductRequest;
import net.sternstein.saft.model.dto.product.UpdateProductRequest;
import net.sternstein.saft.service.ProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("product")
@Consumes("application/json")
@Produces("application/json")
public class ProductRestApi implements ProductApi {

    @Inject
    ProductService productService;

    @POST
    @Override
    public Response createProduct(CreateProductRequest request) {
        var product = productService.createProduct(request.name(), request.price());
        return Response.ok().entity(product).build();
    }

    @GET
    @Override
    // TODO: GAJ ID!
    public Response getProduct(Long id) {
        var product = productService.getProduct(id);
        return Response.ok().entity(product).build();
    }

    @GET
    // TODO: check path ok?
    @Path("all")
    @Override
    public Response getAllProducts() {
        var products = productService.getAllProducts();
        return Response.ok().entity(products).build();
    }

    @PUT
    @Override
    public Response updateProduct(UpdateProductRequest request) {
        var product = productService.updateProduct(request.product());
        return Response.ok().entity(product).build();
    }

    @DELETE
    @Override
    // TODO: GAJ ID!
    public Response deleteProduct(Long id) {
        boolean isRemoved = productService.deleteProduct(id);
        if(!isRemoved) {
            // TODO: do this the right way
            return Response.serverError().build();
        }
        return Response.ok().build();
    }
}
