package database;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class FileControllerRedis implements FileController{
    JedisPool pool;

    public FileControllerRedis() {
        pool = new JedisPool("localhost", 6379);
    }

    public FileControllerRedis(String host, int port) {
        pool = new JedisPool(host, port);
    }

    public FileEntity download(String id) {
        try (Jedis jedis = pool.getResource()) {
            String entity;
            entity = jedis.get(id);
            return new FileEntity(id, entity.getBytes(StandardCharsets.UTF_8));
        }
    }

    public void upload(FileEntity fileEntity) {
        try (Jedis jedis = pool.getResource()) {
            jedis.set(fileEntity.getData(), fileEntity.getData());
        }
    }
}
