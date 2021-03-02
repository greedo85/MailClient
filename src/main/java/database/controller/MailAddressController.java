package database.controller;

import database.DataBase;
import database.entities.Address;
import database.entities.MailAddress;
import database.entities.RepositoryInterface;

import java.util.List;

public class MailAddressController extends DataBase implements RepositoryInterface<MailAddress> {

    @Override
    public void add( MailAddress mailAddress ) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(mailAddress);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public MailAddress getById( int id ) {
        return null;
    }

    @Override
    public MailAddress getByMail( String address ) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }
}
