
import redis.clients.jedis.UnifiedJedis;

import java.util.*;

import static java.lang.System.out;
import static java.lang.Thread.sleep;

public class DatingSite {

    private UnifiedJedis jedis;
    private List<String> users = new ArrayList<>();
    private List<String> log = new ArrayList<>();

    public DatingSite(String address) {
        jedis = new UnifiedJedis(address);
        initUsers();
    }

    private void initUsers() {
        jedis.del("USERS");
        for (int i = 1; i <= 20; i++) {
            String userId = String.valueOf(i);
            jedis.zadd("USERS", new Date().getTime(), userId);
        }
        users = jedis.zrange("USERS", 0, -1);
    }

    public void stop() {
        jedis.close();
    }

    public List<String> getUsers() {
        return users;
    }

    private void vip() {
        Random random = new Random();
        int result = random.nextInt(users.size());
        out.println("> Пользователь " + users.get(result) + " оплатил платную услугу");
        out.println("— На главной странице показываем пользователя " + users.get(result));
        log.add(users.get(result));
    }

    public void print() {
        while (true) {
            log = new ArrayList<>();
            for (String user : users) {
                if (!log.contains(user) ) {
                    out.println("— На главной странице показываем пользователя " + user);
                }
                if (Math.random() < 0.10) {
                    vip();
                }
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
