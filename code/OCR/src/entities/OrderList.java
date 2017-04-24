package entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by guang on 2017/4/21.
 */
@Entity
@Table(name = "order_list", schema = "mydb", catalog = "")
@IdClass(OrderListPK.class)
public class OrderList {
    private int vehicleVehicleId;
    private int userUserId;
    private int orderListId;
    private Timestamp orderTime;

    @Id
    @Column(name = "vehicle_vehicle_id", nullable = false)
    public int getVehicleVehicleId() {
        return vehicleVehicleId;
    }

    public void setVehicleVehicleId(int vehicleVehicleId) {
        this.vehicleVehicleId = vehicleVehicleId;
    }

    @Id
    @Column(name = "user_user_id", nullable = false)
    public int getUserUserId() {
        return userUserId;
    }

    public void setUserUserId(int userUserId) {
        this.userUserId = userUserId;
    }

    @Id
    @Column(name = "order_list_id", nullable = false)
    public int getOrderListId() {
        return orderListId;
    }

    public void setOrderListId(int orderListId) {
        this.orderListId = orderListId;
    }

    @Basic
    @Column(name = "order_time", nullable = false)
    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderList orderList = (OrderList) o;

        if (vehicleVehicleId != orderList.vehicleVehicleId) return false;
        if (userUserId != orderList.userUserId) return false;
        if (orderListId != orderList.orderListId) return false;
        if (orderTime != null ? !orderTime.equals(orderList.orderTime) : orderList.orderTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vehicleVehicleId;
        result = 31 * result + userUserId;
        result = 31 * result + orderListId;
        result = 31 * result + (orderTime != null ? orderTime.hashCode() : 0);
        return result;
    }
}
