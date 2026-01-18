package paradox.store.global.client;

import paradox.store.global.client.dto.User;

public class MockUserClient extends UserClient {
    @Override
    public User findById(String userId) {
        return new User(
                "pdh0128",
                "pdh0128a@gmail.com",
                10000,
                "comodo",
                "USER",
                10000L,
                10000L,
                "DEMON",
                10000
        );
    }

    @Override
    public void deductMoney(String userId, Long amount) {
        // Mock implementation - 실제로는 외부 서비스 호출
    }
}
