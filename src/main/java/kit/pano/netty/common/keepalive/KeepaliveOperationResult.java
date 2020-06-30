package kit.pano.netty.common.keepalive;

import kit.pano.netty.common.entity.OperationResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class KeepaliveOperationResult extends OperationResult {

    private final long time;

}
