/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.controllers;

import edu.mum.waa.controllers.exceptions.NonexistentEntityException;
import edu.mum.waa.controllers.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.mum.waa.models.Item;
import edu.mum.waa.models.Picture;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author uurtsaikh
 */
public class PictureJpaController implements Serializable {

    public PictureJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Picture picture) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Item itemId = picture.getItemId();
            if (itemId != null) {
                itemId = em.getReference(itemId.getClass(), itemId.getId());
                picture.setItemId(itemId);
            }
            em.persist(picture);
            if (itemId != null) {
                itemId.getPictureCollection().add(picture);
                itemId = em.merge(itemId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Picture picture) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Picture persistentPicture = em.find(Picture.class, picture.getId());
            Item itemIdOld = persistentPicture.getItemId();
            Item itemIdNew = picture.getItemId();
            if (itemIdNew != null) {
                itemIdNew = em.getReference(itemIdNew.getClass(), itemIdNew.getId());
                picture.setItemId(itemIdNew);
            }
            picture = em.merge(picture);
            if (itemIdOld != null && !itemIdOld.equals(itemIdNew)) {
                itemIdOld.getPictureCollection().remove(picture);
                itemIdOld = em.merge(itemIdOld);
            }
            if (itemIdNew != null && !itemIdNew.equals(itemIdOld)) {
                itemIdNew.getPictureCollection().add(picture);
                itemIdNew = em.merge(itemIdNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = picture.getId();
                if (findPicture(id) == null) {
                    throw new NonexistentEntityException("The picture with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Picture picture;
            try {
                picture = em.getReference(Picture.class, id);
                picture.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The picture with id " + id + " no longer exists.", enfe);
            }
            Item itemId = picture.getItemId();
            if (itemId != null) {
                itemId.getPictureCollection().remove(picture);
                itemId = em.merge(itemId);
            }
            em.remove(picture);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Picture> findPictureEntities() {
        return findPictureEntities(true, -1, -1);
    }

    public List<Picture> findPictureEntities(int maxResults, int firstResult) {
        return findPictureEntities(false, maxResults, firstResult);
    }

    private List<Picture> findPictureEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Picture.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Picture findPicture(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Picture.class, id);
        } finally {
            em.close();
        }
    }

    public int getPictureCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Picture> rt = cq.from(Picture.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
