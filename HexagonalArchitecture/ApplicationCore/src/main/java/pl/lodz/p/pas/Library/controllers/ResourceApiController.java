package pl.lodz.p.pas.Library.controllers;


import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import pl.lodz.p.pas.Library.model.Book;
import pl.lodz.p.pas.Library.model.Newspaper;
import pl.lodz.p.pas.Library.model.Resource;
import pl.lodz.p.pas.Library.services.ResourceService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named
public class ResourceApiController implements Serializable {

    private Client client = ClientBuilder.newClient();
    private WebTarget base = client.target("http://localhost:8080/DGK/resources/api");
    private List<Book> books;
    private List<Newspaper> newspapers;

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    private List<Resource> resources;


    public String converter(String resourceName){
        String temp= "";
        if(resourceName.contains(" ")){
            temp =  resourceName.replace(" ","-");
        }else{
            temp = resourceName;
        }
        return temp;
    }
    public void sendDelete(String resourceName) throws Exception {
        base.path(resourceName).request(MediaType.APPLICATION_JSON_TYPE).delete();
        loadData();
    }


    @PostConstruct
    public void loadData(){
       books = base.path("books").request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Book>>() {});
       newspapers = base.path("newspapers").request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Newspaper>>() {});
       resources = new ArrayList<>();
       resources.addAll(books);
       resources.addAll(newspapers);
    }





}
