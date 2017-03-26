package dao;

import model.Article;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by MSI on 2017-03-26.
 */
public class ArticleDao {

    private SessionFactory sf;

    public ArticleDao(SessionFactory sf) {
        this.sf = sf;
        if (sf == null)
            throw new RuntimeException("Session factory is null!");
    }

    public List<Article> get() {
        return query("from " + Article.class.getName(), null);
    }

    public Article get(int id) {
        Session session = sf.getCurrentSession();
        session.beginTransaction();

        Article article = (Article) session.get(Article.class, id);

        session.getTransaction().commit();
        return article;
    }

    public int post(Article article) {
        Session session = sf.getCurrentSession();
        session.beginTransaction();

        session.saveOrUpdate(article);

        session.getTransaction().commit();
        return article.getId();
    }

    public int delete(int id) {
        Article article = get(id);

        Session session = sf.getCurrentSession();
        session.beginTransaction();

        session.delete(article);

        session.getTransaction().commit();
        return article.getId();
    }

    private List<Article> query(String hsql, Map<String, Object> params) {
        Session session = sf.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery(hsql);
        if (params != null)
            for (String i : params.keySet())
                query.setParameter(i, params.get(i));

        List<Article> result = null;
        if ((hsql.toUpperCase().indexOf("DELETE") == -1)
                && (hsql.toUpperCase().indexOf("UPDATE") == -1)
                && (hsql.toUpperCase().indexOf("INSERT") == -1)) {
            result = query.list();
        }

        session.getTransaction().commit();
        return result;
    }

}
