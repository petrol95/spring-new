package com.geekbrains.dao;

import com.geekbrains.entities.DiskHib;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository
public class DiskHibImpl implements DiskHibDao {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public DiskHib findById(Long id) {
        return sessionFactory.getCurrentSession().get(DiskHib.class, 1L);
    }

    @Override
    @Transactional
    public List<DiskHib> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from DiskHib", DiskHib.class).getResultList();
    }
}
