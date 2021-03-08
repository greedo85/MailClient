package database.controller;

import database.DataBase;
import database.entities.Email;
import database.entities.EmailAddress;
import database.entities.MailAddress;
import database.entities.RepositoryInterface;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmailController extends DataBase implements RepositoryInterface<Email> {
    @Override
    public void add( Email email ) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(email);
        getEntityManager().getTransaction().commit();
    }
    public Email findOrCreate( String subject, String localDateTime, String type, String content, EmailAddress emailAddress ) {
        Email email;
        try {
            email = getByString(localDateTime);
        } catch (NoResultException e) {
            email = new Email(subject,localDateTime,type,content,emailAddress);
            add(email);
        }
        return email;
    }

    @Override
    public Email getById( int id ) {
        return getEntityManager().find(Email.class,id);
    }

    @Override
    public Email getByString( String date ) {
        TypedQuery <Email> query=  getEntityManager().createQuery("FROM Email WHERE localDateTime=:date", Email.class).setParameter("date",date);
        Email email =  query.getSingleResult();
        return email;
    }

    @Override
    public List getByText( String text ) {
        TypedQuery<Email> query=getEntityManager().createQuery("FROM Email WHERE subject LIKE ?1", Email.class).setParameter(1,"%"+text+"%");
        List<Email> emailList =query.getResultList();
        return emailList;
    }

    @Override
    public List getAll() {
        Query query=getEntityManager().createQuery("FROM Email", Email.class);
        List<Email> getAllEmails =query.getResultList();
        return getAllEmails;
    }
}
