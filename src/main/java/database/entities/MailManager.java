package database.entities;

import database.DataBase;

import java.util.List;

public class MailManager extends DataBase implements RepositoryInterface<Mail>{
    @Override
    public void add( Mail mail ) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(mail);
        getEntityManager().getTransaction().commit();
    }

    @Override
    public Mail getById( int id ) {
        return null;
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
