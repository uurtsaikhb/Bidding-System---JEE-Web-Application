/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.controllers;

import edu.mum.waa.controllers.exceptions.IllegalOrphanException;
import edu.mum.waa.controllers.exceptions.NonexistentEntityException;
import edu.mum.waa.controllers.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.mum.waa.models.Category;
import edu.mum.waa.models.Auction;
import edu.mum.waa.models.Item;
import java.util.ArrayList;
import java.util.Collection;
import edu.mum.waa.models.Picture;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author uurtsaikh
 */
public class ItemJpaController implements Serializable {

    public ItemJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Item item) throws RollbackFailureException, Exception {
        if (item.getAuctionCollection() == null) {
            item.setAuctionCollection(new ArrayList<Auction>());
        }
        if (item.getPictureCollection() == null) {
            item.setPictureCollection(new ArrayList<Picture>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Category categoryId = item.getCategoryId();
            if (categoryId != null) {
                categoryId = em.getReference(categoryId.getClass(), categoryId.getId());
                item.setCategoryId(categoryId);
            }
            Collection<Auction> attachedAuctionCollection = new ArrayList<Auction>();
            for (Auction auctionCollectionAuctionToAttach : item.getAuctionCollection()) {
                auctionCollectionAuctionToAttach = em.getReference(auctionCollectionAuctionToAttach.getClass(), auctionCollectionAuctionToAttach.getId());
                attachedAuctionCollection.add(auctionCollectionAuctionToAttach);
            }
            item.setAuctionCollection(attachedAuctionCollection);
            Collection<Picture> attachedPictureCollection = new ArrayList<Picture>();
            for (Picture pictureCollectionPictureToAttach : item.getPictureCollection()) {
                pictureCollectionPictureToAttach = em.getReference(pictureCollectionPictureToAttach.getClass(), pictureCollectionPictureToAttach.getId());
                attachedPictureCollection.add(pictureCollectionPictureToAttach);
            }
            item.setPictureCollection(attachedPictureCollection);
            em.persist(item);
            if (categoryId != null) {
                categoryId.getItemCollection().add(item);
                categoryId = em.merge(categoryId);
            }
            for (Auction auctionCollectionAuction : item.getAuctionCollection()) {
                Item oldItemIdOfAuctionCollectionAuction = auctionCollectionAuction.getItemId();
                auctionCollectionAuction.setItemId(item);
                auctionCollectionAuction = em.merge(auctionCollectionAuction);
                if (oldItemIdOfAuctionCollectionAuction != null) {
                    oldItemIdOfAuctionCollectionAuction.getAuctionCollection().remove(auctionCollectionAuction);
                    oldItemIdOfAuctionCollectionAuction = em.merge(oldItemIdOfAuctionCollectionAuction);
                }
            }
            for (Picture pictureCollectionPicture : item.getPictureCollection()) {
                Item oldItemIdOfPictureCollectionPicture = pictureCollectionPicture.getItemId();
                pictureCollectionPicture.setItemId(item);
                pictureCollectionPicture = em.merge(pictureCollectionPicture);
                if (oldItemIdOfPictureCollectionPicture != null) {
                    oldItemIdOfPictureCollectionPicture.getPictureCollection().remove(pictureCollectionPicture);
                    oldItemIdOfPictureCollectionPicture = em.merge(oldItemIdOfPictureCollectionPicture);
                }
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

    public void edit(Item item) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Item persistentItem = em.find(Item.class, item.getId());
            Category categoryIdOld = persistentItem.getCategoryId();
            Category categoryIdNew = item.getCategoryId();
            Collection<Auction> auctionCollectionOld = persistentItem.getAuctionCollection();
            Collection<Auction> auctionCollectionNew = item.getAuctionCollection();
            Collection<Picture> pictureCollectionOld = persistentItem.getPictureCollection();
            Collection<Picture> pictureCollectionNew = item.getPictureCollection();
            List<String> illegalOrphanMessages = null;
            for (Auction auctionCollectionOldAuction : auctionCollectionOld) {
                if (!auctionCollectionNew.contains(auctionCollectionOldAuction)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Auction " + auctionCollectionOldAuction + " since its itemId field is not nullable.");
                }
            }
            for (Picture pictureCollectionOldPicture : pictureCollectionOld) {
                if (!pictureCollectionNew.contains(pictureCollectionOldPicture)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Picture " + pictureCollectionOldPicture + " since its itemId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (categoryIdNew != null) {
                categoryIdNew = em.getReference(categoryIdNew.getClass(), categoryIdNew.getId());
                item.setCategoryId(categoryIdNew);
            }
            Collection<Auction> attachedAuctionCollectionNew = new ArrayList<Auction>();
            for (Auction auctionCollectionNewAuctionToAttach : auctionCollectionNew) {
                auctionCollectionNewAuctionToAttach = em.getReference(auctionCollectionNewAuctionToAttach.getClass(), auctionCollectionNewAuctionToAttach.getId());
                attachedAuctionCollectionNew.add(auctionCollectionNewAuctionToAttach);
            }
            auctionCollectionNew = attachedAuctionCollectionNew;
            item.setAuctionCollection(auctionCollectionNew);
            Collection<Picture> attachedPictureCollectionNew = new ArrayList<Picture>();
            for (Picture pictureCollectionNewPictureToAttach : pictureCollectionNew) {
                pictureCollectionNewPictureToAttach = em.getReference(pictureCollectionNewPictureToAttach.getClass(), pictureCollectionNewPictureToAttach.getId());
                attachedPictureCollectionNew.add(pictureCollectionNewPictureToAttach);
            }
            pictureCollectionNew = attachedPictureCollectionNew;
            item.setPictureCollection(pictureCollectionNew);
            item = em.merge(item);
            if (categoryIdOld != null && !categoryIdOld.equals(categoryIdNew)) {
                categoryIdOld.getItemCollection().remove(item);
                categoryIdOld = em.merge(categoryIdOld);
            }
            if (categoryIdNew != null && !categoryIdNew.equals(categoryIdOld)) {
                categoryIdNew.getItemCollection().add(item);
                categoryIdNew = em.merge(categoryIdNew);
            }
            for (Auction auctionCollectionNewAuction : auctionCollectionNew) {
                if (!auctionCollectionOld.contains(auctionCollectionNewAuction)) {
                    Item oldItemIdOfAuctionCollectionNewAuction = auctionCollectionNewAuction.getItemId();
                    auctionCollectionNewAuction.setItemId(item);
                    auctionCollectionNewAuction = em.merge(auctionCollectionNewAuction);
                    if (oldItemIdOfAuctionCollectionNewAuction != null && !oldItemIdOfAuctionCollectionNewAuction.equals(item)) {
                        oldItemIdOfAuctionCollectionNewAuction.getAuctionCollection().remove(auctionCollectionNewAuction);
                        oldItemIdOfAuctionCollectionNewAuction = em.merge(oldItemIdOfAuctionCollectionNewAuction);
                    }
                }
            }
            for (Picture pictureCollectionNewPicture : pictureCollectionNew) {
                if (!pictureCollectionOld.contains(pictureCollectionNewPicture)) {
                    Item oldItemIdOfPictureCollectionNewPicture = pictureCollectionNewPicture.getItemId();
                    pictureCollectionNewPicture.setItemId(item);
                    pictureCollectionNewPicture = em.merge(pictureCollectionNewPicture);
                    if (oldItemIdOfPictureCollectionNewPicture != null && !oldItemIdOfPictureCollectionNewPicture.equals(item)) {
                        oldItemIdOfPictureCollectionNewPicture.getPictureCollection().remove(pictureCollectionNewPicture);
                        oldItemIdOfPictureCollectionNewPicture = em.merge(oldItemIdOfPictureCollectionNewPicture);
                    }
                }
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
                Integer id = item.getId();
                if (findItem(id) == null) {
                    throw new NonexistentEntityException("The item with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Item item;
            try {
                item = em.getReference(Item.class, id);
                item.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The item with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Auction> auctionCollectionOrphanCheck = item.getAuctionCollection();
            for (Auction auctionCollectionOrphanCheckAuction : auctionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Item (" + item + ") cannot be destroyed since the Auction " + auctionCollectionOrphanCheckAuction + " in its auctionCollection field has a non-nullable itemId field.");
            }
            Collection<Picture> pictureCollectionOrphanCheck = item.getPictureCollection();
            for (Picture pictureCollectionOrphanCheckPicture : pictureCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Item (" + item + ") cannot be destroyed since the Picture " + pictureCollectionOrphanCheckPicture + " in its pictureCollection field has a non-nullable itemId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Category categoryId = item.getCategoryId();
            if (categoryId != null) {
                categoryId.getItemCollection().remove(item);
                categoryId = em.merge(categoryId);
            }
            em.remove(item);
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

    public List<Item> findItemEntities() {
        return findItemEntities(true, -1, -1);
    }

    public List<Item> findItemEntities(int maxResults, int firstResult) {
        return findItemEntities(false, maxResults, firstResult);
    }

    private List<Item> findItemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Item.class));
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

    public Item findItem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Item.class, id);
        } finally {
            em.close();
        }
    }

    public int getItemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Item> rt = cq.from(Item.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
