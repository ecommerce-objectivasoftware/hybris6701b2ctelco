package de.hybris.electronics.core.job;


import de.hybris.electronics.core.model.UnpaidOrderCancellationCronJobModel;
import de.hybris.electronics.core.order.ElectronicsOrderService;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * The job to cancel the unpaid orders
 */
public class UnpaidOrderCancellationJob extends AbstractJobPerformable<UnpaidOrderCancellationCronJobModel> {
    private ElectronicsOrderService electronicsOrderService;

    @Override
    public PerformResult perform(final UnpaidOrderCancellationCronJobModel unpaidOrderCancellationCronJobModel) {
        final List<OrderModel> unpaidOrders = getElectronicsOrderService().getUnpaidOrders();
        if (CollectionUtils.isNotEmpty(unpaidOrders)) {
            boolean result = getElectronicsOrderService().cancelUnpaidOrders(unpaidOrders);
            if (!result) {
                return new PerformResult(CronJobResult.FAILURE, CronJobStatus.ABORTED);
            }
        }

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    public ElectronicsOrderService getElectronicsOrderService() {
        return electronicsOrderService;
    }

    public void setElectronicsOrderService(final ElectronicsOrderService electronicsOrderService) {
        this.electronicsOrderService = electronicsOrderService;
    }
}
