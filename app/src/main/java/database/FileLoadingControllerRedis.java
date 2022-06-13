package database;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class FileLoadingControllerRedis implements FileLoadingController {
    private final JedisPool pool;
    private final AtomicBoolean isWork = new AtomicBoolean(false);

    public FileLoadingControllerRedis() {
        pool = new JedisPool("localhost", 6379);
    }

    public FileLoadingControllerRedis(String host, int port) {
        pool = new JedisPool(host, port);
    }

    @Override
    public FileEntity download(String id) {
        isWork.set(true);
        Jedis jedis = pool.getResource();
        byte[] fileBytes;
        fileBytes = jedis.get(id.getBytes(StandardCharsets.UTF_8));
        isWork.set(false);
        return new FileEntity(id, fileBytes);
    }

    @Override
    public void upload(FileEntity fileEntity) {
        isWork.set(true);
        Jedis jedis = pool.getResource();
        jedis.set(fileEntity.getId().getBytes(StandardCharsets.UTF_8), fileEntity.getData());
        isWork.set(false);
    }

    @Override
    public boolean isWorked() {
        return isWork.get();
    }

    @Override
    public void setWorkStatus(boolean status) {
        isWork.set(status);
    }
}
