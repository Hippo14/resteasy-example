import com.google.gson.Gson;
import dao.ArticleDao;
import model.Article;
import util.HibernateUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by MSI on 2017-03-26.
 */
@Path("/article")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArticleService {

    Gson gson = new Gson();
    ArticleDao articleDao = new ArticleDao(HibernateUtil.getSessionFactory());

    @GET
    @Path("/")
    public Response getArticles() {
        return Response.status(200).entity(articleDao.get()).build();
    }

    @GET
    @Path("/{id}")
    public Response getArticlesById(@PathParam("id") int id) {
        Article article = articleDao.get(id);

        return Response.status(200).entity(article).build();
    }

    @POST
    @Path("/")
    public Response setArticle(String json) throws IOException {
        Article article = gson.fromJson(json, Article.class);
        int result = articleDao.post(article);

        return Response.status(200).entity(result).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteArticle(@PathParam("id") int id) {
        int result = articleDao.delete(id);

        return Response.status(200).entity(result).build();
    }

}
