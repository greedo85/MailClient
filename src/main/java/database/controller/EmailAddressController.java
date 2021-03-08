package database.controller;

import database.DataBase;
import database.entities.EmailAddress;
import database.entities.MailAddress;
import database.entities.RepositoryInterface;


import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmailAddressController extends DataBase implements RepositoryInterface<EmailAddress> {
    @Override
    public void add( EmailAddress emailAddress ) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(emailAddress);
        getEntityManager().getTransaction().commit();
        //getEntityManager().close();
    }

    public EmailAddress findOrCreate( String mailAddress ) {
        EmailAddress emailAddress;
        try {
            emailAddress = getByString(mailAddress);
        } catch (NoResultException e) {
            emailAddress = new EmailAddress(mailAddress);
            add(emailAddress);
        }
        return emailAddress;
    }

    @Override
    public EmailAddress getById( int id ) {
        return getEntityManager().find(EmailAddress.class, id);
    }

    @Override
    public EmailAddress getByString( String mail ) {
        TypedQuery<EmailAddress> query = getEntityManager().createQuery("FROM EmailAddress WHERE mailAddress=:mail_address", EmailAddress.class).setParameter("mail_address", mail);
        EmailAddress emailAddress1 = query.getSingleResult();
        return emailAddress1;
    }

    @Override
    public List getByText( String text ) {
        return null;
    }

    @Override
    public List getAll() {
        Query query = getEntityManager().createQuery("FROM EmailAddress", EmailAddress.class);
        return (List<EmailAddress>) query.getResultList();
    }
}
