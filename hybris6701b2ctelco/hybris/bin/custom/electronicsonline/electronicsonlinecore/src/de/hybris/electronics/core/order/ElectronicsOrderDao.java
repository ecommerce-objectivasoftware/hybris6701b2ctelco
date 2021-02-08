package de.hybris.electronics.core.order;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.daos.OrderDao;

import java.util.List;

/**
 * Custom Electronics Order Dao
 */
public interface ElectronicsOrderDao extends OrderDao {
    /**
     * Get the orders by the status
     *
     * @param orderStatus order status enum
     * @return List of {@link OrderModel}
     */
    List<OrderModel> getOrdersByStatus(final OrderStatus orderStatus);
}
