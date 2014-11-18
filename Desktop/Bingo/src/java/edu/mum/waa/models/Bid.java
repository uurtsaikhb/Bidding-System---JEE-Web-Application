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
@Table(name = "bid")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bid.findAll", query = "SELECT b FROM Bid b"),
    @NamedQuery(name = "Bid.findById", query = "SELECT b FROM Bid b WHERE b.id = :id"),
    @NamedQuery(name = "Bid.findByAuctionId", query = "SELECT b FROM Bid b WHERE b.auctionId = :auctionId"),
    @NamedQuery(name = "Bid.findByBidPrice", query = "SELECT b FROM Bid b WHERE b.bidPrice = :bidPrice"),
    @NamedQuery(name = "Bid.findByCurrentbidPrice", query = "SELECT b FROM Bid b WHERE b.currentbidPrice = :currentbidPrice"),
    @NamedQuery(name = "Bid.findByBiddingDate", query = "SELECT b FROM Bid b WHERE b.biddingDate = :biddingDate")})
public class Bid implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "auction_id")
    private int auctionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bid_price")
    private int bidPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "currentbid_price")
    private int currentbidPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bidding_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date biddingDate;
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User buyerId;

    public Bid() {
    }

    public Bid(Integer id) {
        this.id = id;
    }

    public Bid(Integer id, int auctionId, int bidPrice, int currentbidPrice, Date biddingDate) {
        this.id = id;
        this.auctionId = auctionId;
        this.bidPrice = bidPrice;
        this.currentbidPrice = currentbidPrice;
        this.biddingDate = biddingDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public int getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(int bidPrice) {
        this.bidPrice = bidPrice;
    }

    public int getCurrentbidPrice() {
        return currentbidPrice;
    }

    public void setCurrentbidPrice(int currentbidPrice) {
        this.currentbidPrice = currentbidPrice;
    }

    public Date getBiddingDate() {
        return biddingDate;
    }

    public void setBiddingDate(Date biddingDate) {
        this.biddingDate = biddingDate;
    }

    public User getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(User buyerId) {
        this.buyerId = buyerId;
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
        if (!(object instanceof Bid)) {
            return false;
        }
        Bid other = (Bid) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.mum.waa.models.Bid[ id=" + id + " ]";
    }
    
}
