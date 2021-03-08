package database.controller;

import database.DataBase;
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
        return getEntityManager().find(MailAddress.class,id);
    }

    @Override
    public MailAddress getByString( String address ) {
        return null;
    }

    @Override
    public List getByText( String text ) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }
}
