package kit.pano.netty.common.entity;

/**
 * @author pano
 */
public abstract class Operation extends MessageBody {

    public abstract OperationResult execute();
}
