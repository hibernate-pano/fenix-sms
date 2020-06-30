package kit.pano.netty.common.order;


import com.google.common.util.concurrent.Uninterruptibles;
import kit.pano.netty.common.entity.Operation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.java.Log;

import java.util.concurrent.TimeUnit;

@EqualsAndHashCode(callSuper = true)
@Data
@Log
public class OrderOperation extends Operation {

    private int tableId;
    private String dish;

    public OrderOperation(int tableId, String dish) {
        this.tableId = tableId;
        this.dish = dish;
    }

    @Override
    public OrderOperationResult execute() {
        log.info("order's executing startup with orderRequest: " + toString());
        //execute order logic
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        log.info("order's executing complete");
        OrderOperationResult orderResponse = new OrderOperationResult(tableId, dish, true);
        return orderResponse;
    }
}
