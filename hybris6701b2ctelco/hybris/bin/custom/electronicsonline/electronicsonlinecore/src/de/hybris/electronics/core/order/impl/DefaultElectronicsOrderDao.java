package de.hybris.electronics.core.order.impl;

import de.hybris.electronics.core.order.ElectronicsOrderDao;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.daos.impl.DefaultOrderDao;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DefaultElectronicsOrderDao extends DefaultOrderDao implements ElectronicsOrderDao
{
    private static final String FIND_ORDERS_BY_STATUS = String.format(
            "SELECT {o.%s} FROM {%s AS o} WHERE {o.%s} = ?orderStatus AND {o.creationTime} <= ?creationTime",
            OrderModel.PK, OrderModel._TYPECODE, OrderModel.STATUS, OrderModel.CREATIONTIME
    );

    private ConfigurationService configurationService;

    /**
     * Get the orders by the status
     *
     * @param orderStatus order status enum
     * @return List of {@link OrderModel}
     */
    @Override
    public List<OrderModel> getOrdersByStatus(final OrderStatus orderStatus)
    {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_ORDERS_BY_STATUS);
        query.addQueryParameter("orderStatus", orderStatus);
        query.addQueryParameter("creationTime", getTheUnpaidDiffTime());
        return getFlexibleSearchService().<OrderModel> search(query).getResult();
    }

    private Date getTheUnpaidDiffTime()
    {
        final int diffSeconds = getConfigurationService().getConfiguration().getInt("electronicsonlinecore.order.unpaid.diff.time", -120);
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, diffSeconds);
        return calendar.getTime();
    }

    public ConfigurationService getConfigurationService()
    {
        return configurationService;
    }

    public void setConfigurationService(final ConfigurationService configurationService)
    {
        this.configurationService = configurationService;
    }
}
