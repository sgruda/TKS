package pl.lodz.p.pas.Library.ResourceAPI;


import pl.lodz.p.pas.Library.model.Book;
import pl.lodz.p.pas.Library.model.Newspaper;
import pl.lodz.p.pas.Library.model.Resource;
import pl.lodz.p.pas.Library.services.ResourceService;
import pl.lodz.p.tks.model.BookEnt;
import pl.lodz.p.tks.model.NewspaperEnt;
import pl.lodz.p.tks.model.ResourceEnt;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("api")
public class ResourceAPI {
    private ResourceService resourceService;

    @Inject
    public ResourceAPI(ResourceService resourceService){
        this.resourceService = resourceService;
    }

    public String convertString(String name){
        String temp = "";
        if(name.contains("-")){
            temp =  name.replace("-"," ");
        }else{
            temp=name;
        }
        return temp;
    }

    @POST
    @Path("add_book")
    @Consumes(MediaType.APPLICATION_JSON)
    public void Create(BookEnt book) {
        resourceService.addResource(book);
    }

    @POST
    @Path("add_newspaper")
    @Consumes(MediaType.APPLICATION_JSON)
    public void Create(NewspaperEnt newspaper) {
        resourceService.addResource(newspaper);
    }


    @GET
    @Path("{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ResourceEnt Get(@PathParam("name") String name) {

        return resourceService.getResourceByTitle(convertString(name));
    }

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<ResourceEnt> GetAll() {
        return resourceService.getAllResources();
    }

    @GET
    @Path("books")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<ResourceEnt> GetAllBooks() {
        List<ResourceEnt> temp = new ArrayList<>();
        for (ResourceEnt res: resourceService.getAllResources()) {
                if(res instanceof BookEnt){
                    temp.add(res);
                }
        }
        return temp;
    }

    @GET
    @Path("newspapers")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<ResourceEnt> GetAllNewspapers() {
        List<ResourceEnt> temp = new ArrayList<>();
        for (ResourceEnt res: resourceService.getAllResources()) {
            if(res instanceof NewspaperEnt){
                temp.add(res);
            }
        }
        return temp;
    }

    @DELETE
    @Path("{name}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void Remove(@PathParam("name") String name){
        resourceService.removeResourceBasedOnTitle(convertString(name));
    }



    @POST
    @Path("modify_book")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void Update(BookEnt resource){
        resourceService.updateResource(resource);
    }

    @POST
    @Path("modify_newspaper")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void Update(NewspaperEnt resource){
        resourceService.updateResource(resource);
    }


}
