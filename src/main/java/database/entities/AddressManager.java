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
        TypedQuery <Address> query= (TypedQuery<Address>) getEntityManager().createQuery("SELECT id, mailAddress FROM Address WHERE mailAddress=:email").setParameter("email",mail);
        Address address1=  query.getSingleResult();
        return address1;
    }

    @Override
    public List getAll() {
        return null;
    }
}
