package ru.sberbank.sin.common.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by 1 on 25.03.2016.
 */
@XmlRootElement
@Entity
@NamedQueries({
        @NamedQuery(name = Account.QUERY_FIND_ALL, query = "SELECT a FROM Account a ORDER BY a.id ASC"),
        @NamedQuery(name = Account.QUERY_FIND_BY_NAME, query = "SELECT a FROM Account a WHERE a.owner = :owner")
})
@Table(name = "ACCOUNT")
public class Account implements Serializable {
    public static final String QUERY_FIND_ALL = "Account.findAll";
    public static final String QUERY_FIND_BY_NAME = "Account.findName";

    private Integer id;
    private Double amount;
    private String owner;

    @Id
    @SequenceGenerator(name="SEQ_GEN", sequenceName="SIN_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer accountId) {
        this.id = accountId;
    }

    @Column(name = "AMOUNT")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Column(name = "OWNER")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String ownerName) {
        this.owner = ownerName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        return (this.id==null?other.id==null:this.id.equals(other.id)) &&
        (this.amount==null?other.amount==null:this.amount.equals(other.amount)) &&
        (this.owner==null?other.owner==null:this.owner.equals(other.owner)) ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
