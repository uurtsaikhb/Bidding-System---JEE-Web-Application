/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author uurtsaikh
 */
@Entity
@Table(name = "auction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auction.findAll", query = "SELECT a FROM Auction a"),
    @NamedQuery(name = "Auction.findById", query = "SELECT a FROM Auction a WHERE a.id = :id"),
    @NamedQuery(name = "Auction.findByStartingDate", query = "SELECT a FROM Auction a WHERE a.startingDate = :startingDate"),
    @NamedQuery(name = "Auction.findByEndDate", query = "SELECT a FROM Auction a WHERE a.endDate = :endDate"),
    @NamedQuery(name = "Auction.findByStatus", query = "SELECT a FROM Auction a WHERE a.status = :status"),
    @NamedQuery(name = "Auction.findByStartingPrice", query = "SELECT a FROM Auction a WHERE a.startingPrice = :startingPrice"),
    @NamedQuery(name = "Auction.findByBuyoutPrice", query = "SELECT a FROM Auction a WHERE a.buyoutPrice = :buyoutPrice"),
    @NamedQuery(name = "Auction.findByStepPrice", query = "SELECT a FROM Auction a WHERE a.stepPrice = :stepPrice"),
    @NamedQuery(name = "Auction.findByHammerPrice", query = "SELECT a FROM Auction a WHERE a.hammerPrice = :hammerPrice"),
    @NamedQuery(name = "Auction.findBySellerId", query = "SELECT a FROM Auction a WHERE a.sellerId = :sellerId")})
public class Auction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "starting_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startingDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "starting_price")
    private float startingPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "buyout_price")
    private float buyoutPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "step_price")
    private float stepPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hammer_price")
    private float hammerPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seller_id")
    private int sellerId;
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Item itemId;

    public Auction() {
    }

    public Auction(Integer id) {
        this.id = id;
    }

    public Auction(Integer id, Date startingDate, Date endDate, int status, float startingPrice, float buyoutPrice, float stepPrice, float hammerPrice, int sellerId) {
        this.id = id;
        this.startingDate = startingDate;
        this.endDate = endDate;
        this.status = status;
        this.startingPrice = startingPrice;
        this.buyoutPrice = buyoutPrice;
        this.stepPrice = stepPrice;
        this.hammerPrice = hammerPrice;
        this.sellerId = sellerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(float startingPrice) {
        this.startingPrice = startingPrice;
    }

    public float getBuyoutPrice() {
        return buyoutPrice;
    }

    public void setBuyoutPrice(float buyoutPrice) {
        this.buyoutPrice = buyoutPrice;
    }

    public float getStepPrice() {
        return stepPrice;
    }

    public void setStepPrice(float stepPrice) {
        this.stepPrice = stepPrice;
    }

    public float getHammerPrice() {
        return hammerPrice;
    }

    public void setHammerPrice(float hammerPrice) {
        this.hammerPrice = hammerPrice;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auction)) {
            return false;
        }
        Auction other = (Auction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.mum.waa.models.Auction[ id=" + id + " ]";
    }
    
}
