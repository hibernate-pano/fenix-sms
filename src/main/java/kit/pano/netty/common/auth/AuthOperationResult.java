package kit.pano.netty.common.auth;

import kit.pano.netty.common.entity.OperationResult;
import lombok.Data;

@Data
public class AuthOperationResult extends OperationResult {

    private final boolean passAuth;

}
