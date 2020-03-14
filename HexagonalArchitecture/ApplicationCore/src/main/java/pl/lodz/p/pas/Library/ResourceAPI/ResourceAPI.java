package pl.lodz.p.pas.Library.ResourceAPI;


import pl.lodz.p.pas.Library.model.Book;
import pl.lodz.p.pas.Library.model.Newspaper;
import pl.lodz.p.pas.Library.model.Resource;
import pl.lodz.p.pas.Library.services.ResourceService;

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
    public void Create(Book book) {
        resourceService.addResource(book);
    }

    @POST
    @Path("add_newspaper")
    @Consumes(MediaType.APPLICATION_JSON)
    public void Create(Newspaper newspaper) {
        resourceService.addResource(newspaper);
    }


    @GET
    @Path("{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Resource Get(@PathParam("name") String name) {

        return resourceService.getResourceByTitle(convertString(name));
    }

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Resource> GetAll() {
        return resourceService.getAllResources();
    }

    @GET
    @Path("books")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Resource> GetAllBooks() {
        List<Resource> temp = new ArrayList<>();
        for (Resource res: resourceService.getAllResources()) {
                if(res instanceof Book){
                    temp.add(res);
                }
        }
        return temp;
    }

    @GET
    @Path("newspapers")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Resource> GetAllNewspapers() {
        List<Resource> temp = new ArrayList<>();
        for (Resource res: resourceService.getAllResources()) {
            if(res instanceof Newspaper){
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
    public void Update(Book resource){
        resourceService.updateResource(resource);
    }

    @POST
    @Path("modify_newspaper")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void Update(Newspaper resource){
        resourceService.updateResource(resource);
    }


}
