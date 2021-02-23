package database.entities;

import database.DataBase;


import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class AddressManager extends DataBase implements RepositoryInterface<Address> {
    @Override
    public void add( Address address ) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(address);
        getEntityManager().getTransaction().commit();
        //getEntityManager().close();
    }

    @Override
    public Address getById( int id ) {
        return getEntityManager().find(Address.class,id);
    }

    @Override
    public Address getByMail( String mail ) {
        TypedQuery <Address> query=  getEntityManager().createQuery("FROM Address WHERE mailAddress=:mail_address",Address.class).setParameter("mail_address",mail);
        Address address1=  query.getSingleResult();
        return address1;
    }

    @Override
    public List getAll() {
        Query query=getEntityManager().createQuery("FROM Address",Address.class);
        List<Address>addresses= query.getResultList();
        return addresses;
    }
}
