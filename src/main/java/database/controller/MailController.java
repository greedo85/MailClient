package database.controller;

import database.DataBase;
import database.entities.Address;
import database.entities.Mail;
import database.entities.RepositoryInterface;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class MailController extends DataBase implements RepositoryInterface<Mail> {
    @Override
    public void add( Mail mail ) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(mail);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public Mail getById( int id ) {
        return getEntityManager().find(Mail.class,id);
    }

    @Override
    public Mail getByString( String address ) {
        return null;
    }

    @Override
    public List getByText( String text ) {
        TypedQuery<Mail> query=getEntityManager().createQuery("FROM Mail WHERE subject LIKE ?1",Mail.class).setParameter(1,"%"+text+"%");
        List<Mail> mailList=query.getResultList();
        return mailList;
    }

    @Override
    public List getAll() {
        Query query=getEntityManager().createQuery("FROM Mail",Mail.class);
        List<Mail>getAllMails=query.getResultList();
        return getAllMails;
    }
}
