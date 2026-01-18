package paradox.store.global.client;

import paradox.store.global.client.dto.User;

public abstract class UserClient {

    public abstract User findById(String userId);

    public abstract void deductMoney(String userId, Long amount);

}
