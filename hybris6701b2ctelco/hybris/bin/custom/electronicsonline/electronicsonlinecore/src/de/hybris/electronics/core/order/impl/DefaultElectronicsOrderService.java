package de.hybris.electronics.core.order.impl;

import de.hybris.electronics.core.order.ElectronicsOrderDao;
import de.hybris.electronics.core.order.ElectronicsOrderService;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.impl.DefaultOrderService;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The implementation for the {@link ElectronicsOrderService}
 */
public class DefaultElectronicsOrderService extends DefaultOrderService implements ElectronicsOrderService {
    private static final Logger LOG = Logger.getLogger(DefaultElectronicsOrderService.class);

    private ElectronicsOrderDao electronicsOrderDao;

    /**
     * Get the unpaid orders
     *
     * @return List of {@link OrderModel}
     */
    @Override
    public List<OrderModel> getUnpaidOrders() {
        return getElectronicsOrderDao().getOrdersByStatus(OrderStatus.PAYMENT_NOT_CAPTURED);
    }

    /**
     * Cancel the unpaid orders
     *
     * @param orders unpaid orders
     * @return
     */
    @Override
    public boolean cancelUnpaidOrders(final List<OrderModel> orders) {
        boolean success = true;

        if (CollectionUtils.isNotEmpty(orders)) {
            for (final OrderModel order : orders) {
                order.setStatus(OrderStatus.CANCELLED);
            }

            try {
                getModelService().saveAll(orders);
            } catch (final ModelSavingException e) {
                LOG.error("Cannot cancel the unpaid orders.");
                success = false;
            }
        }

        return success;
    }

    public ElectronicsOrderDao getElectronicsOrderDao() {
        return electronicsOrderDao;
    }

    public void setElectronicsOrderDao(final ElectronicsOrderDao electronicsOrderDao) {
        this.electronicsOrderDao = electronicsOrderDao;
    }
}
