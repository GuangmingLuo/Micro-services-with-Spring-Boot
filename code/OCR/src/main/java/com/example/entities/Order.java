package com.example.entities;

import javax.persistence.*;

/**
 * Created by guang on 2017/4/28.
 */
@Entity
@Table(name = "order", schema = "mydb")
public class Order {
    private int id;
    private String content;
    private int table;
    private String status;
    private String comments;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 225)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "table", nullable = false)
    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    @Basic
    @Column(name = "comments", nullable = true, length = 225)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (table != order.table) return false;
        if (content != null ? !content.equals(order.content) : order.content != null) return false;
        if (status != null ? !status.equals(order.status) : order.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + table;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
