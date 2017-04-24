package entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by guang on 2017/4/21.
 */
public class OrderListPK implements Serializable {
    private int vehicleVehicleId;
    private int userUserId;
    private int orderListId;

    @Column(name = "vehicle_vehicle_id", nullable = false)
    @Id
    public int getVehicleVehicleId() {
        return vehicleVehicleId;
    }

    public void setVehicleVehicleId(int vehicleVehicleId) {
        this.vehicleVehicleId = vehicleVehicleId;
    }

    @Column(name = "user_user_id", nullable = false)
    @Id
    public int getUserUserId() {
        return userUserId;
    }

    public void setUserUserId(int userUserId) {
        this.userUserId = userUserId;
    }

    @Column(name = "order_list_id", nullable = false)
    @Id
    public int getOrderListId() {
        return orderListId;
    }

    public void setOrderListId(int orderListId) {
        this.orderListId = orderListId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderListPK that = (OrderListPK) o;

        if (vehicleVehicleId != that.vehicleVehicleId) return false;
        if (userUserId != that.userUserId) return false;
        if (orderListId != that.orderListId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vehicleVehicleId;
        result = 31 * result + userUserId;
        result = 31 * result + orderListId;
        return result;
    }
}
