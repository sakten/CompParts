package dao;

import models.ComputerPart;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.Constants;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MySQLDao implements CompPartsDAO {                   //need try catch for safe resources management

    public ComputerPart getByName(String name) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx=null;
        ComputerPart result =null;
        try{
            tx=session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ComputerPart> query = builder.createQuery(ComputerPart.class);
            Root<ComputerPart> root = query.from(ComputerPart.class);
            query.select(root).where(builder.equal(root.get("name"),name));
            Query<ComputerPart> q =session.createQuery(query);
            result = q.getSingleResult();
            tx.commit();
        }
        catch (Throwable e){
           // e.printStackTrace();
            if (tx != null){
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
        return result;
    }

    public ComputerPart getById(int id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        ComputerPart result = null;
        try{
            tx=session.beginTransaction();
            result = session.get(ComputerPart.class,id);
            tx.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if (tx != null){
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
        return  result;
    }

    public void save(ComputerPart part) throws PersistenceException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
           session.save(part);
            tx.commit();
        }
        catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
        }
         finally{
            session.close();
        }
    }

    public void update(ComputerPart part) throws PersistenceException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(part);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            System.out.println("exception occurred due record update");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void delete(ComputerPart part) {
        Session sess = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx=null;
        try {
            tx = sess.beginTransaction();
            sess.delete(part);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            sess.close();
        }
    }

    public List<ComputerPart> getAll() {
        List<ComputerPart> result=null;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx=session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ComputerPart> query = builder.createQuery(ComputerPart.class);
            Root<ComputerPart> root = query.from(ComputerPart.class);
            query.select(root);
            Query<ComputerPart> q =session.createQuery(query);
            result = q.getResultList();
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return  result;
    }

    public List<ComputerPart> getAll(int page){
        List<ComputerPart> result=null;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx=session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ComputerPart> query = builder.createQuery(ComputerPart.class);
            Root<ComputerPart> root = query.from(ComputerPart.class);
            query.select(root);
            Query<ComputerPart> q =session.createQuery(query);
            q.setFirstResult(Constants.PAGE_SIZE*(page-1));
            q.setMaxResults(Constants.PAGE_SIZE);
            result = q.getResultList();
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return  result;
    }
    public int getCount(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Long count = 0L;
        try{
            tx=session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
            countQuery.select(builder.count(countQuery.from(ComputerPart.class)));
            count = session.createQuery(countQuery).getSingleResult();
            tx.commit();
        }
        catch (Exception e){
            if (tx !=null){
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
        int result = (int)(long)count;
        return result;
    }

    public List<ComputerPart> getAllNecessary(int page){
        List<ComputerPart> result=null;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx=session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ComputerPart> query = builder.createQuery(ComputerPart.class);
            Root<ComputerPart> root = query.from(ComputerPart.class);
            query.select(root).where(builder.equal(root.get("isNecessary"),true));;
            Query<ComputerPart> q =session.createQuery(query);
            q.setFirstResult(Constants.PAGE_SIZE*(page-1));
            q.setMaxResults(Constants.PAGE_SIZE);
            result = q.getResultList();
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return  result;
    }

    public List<ComputerPart> getAllUnnecessary(int page){
        List<ComputerPart> result=null;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx=session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ComputerPart> query = builder.createQuery(ComputerPart.class);
            Root<ComputerPart> root = query.from(ComputerPart.class);
            query.select(root).where(builder.equal(root.get("isNecessary"),false));;
            Query<ComputerPart> q =session.createQuery(query);
            q.setFirstResult(Constants.PAGE_SIZE*(page-1));
            q.setMaxResults(Constants.PAGE_SIZE);
            result = q.getResultList();
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return  result;
    }
    public int getCountNecessary(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Long count = 0L;
        try{
            tx=session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
            Root<ComputerPart> root = countQuery.from(ComputerPart.class);
            countQuery.select(builder.count(root));
            countQuery.where(builder.equal(root.get("isNecessary"),true));
            count = session.createQuery(countQuery).getSingleResult();
            tx.commit();
        }
        catch (Exception e){
            if (tx !=null){
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
        int result = (int)(long)count;
        return result;
    }
    public int getCountUnnecessary(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Long count = 0L;
        try{
            tx=session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
            Root<ComputerPart> root = countQuery.from(ComputerPart.class);
            countQuery.select(builder.count(root));
            countQuery.where(builder.equal(root.get("isNecessary"),false));
            count = session.createQuery(countQuery).getSingleResult();
            tx.commit();
        }
        catch (Exception e){
            if (tx !=null){
                tx.rollback();
            }
        }
        finally {
            session.close();
        }
        int result = (int)(long)count;
        return result;
    }
}
