package de.hybris.electronics.core.order;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.OrderService;

import java.util.List;

/**
 * Custom Electronics Order Service
 */
public interface ElectronicsOrderService extends OrderService {
    /**
     * Get the unpaid orders
     *
     * @return List of {@link OrderModel}
     */
    List<OrderModel> getUnpaidOrders();

    /**
     * Cancel the unpaid orders
     *
     * @param orders unpaid orders
     * @return
     */
    boolean cancelUnpaidOrders(final List<OrderModel> orders);
}
