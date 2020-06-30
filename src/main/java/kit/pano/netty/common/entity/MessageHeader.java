package kit.pano.netty.common.entity;

import lombok.Data;

/**
 * @author pano
 */
@Data
public class MessageHeader {

    private int version = 1;

    private int opCode;

    private long streamId;
}
