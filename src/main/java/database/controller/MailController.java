package database.controller;

import database.DataBase;
import database.entities.Mail;
import database.entities.RepositoryInterface;

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
    public Mail getByMail( String address ) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }
}
