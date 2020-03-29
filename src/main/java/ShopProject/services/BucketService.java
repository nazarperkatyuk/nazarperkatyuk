package ShopProject.services;

import java.util.List;

import ShopProject.dao.BucketDao;
import ShopProject.entities.Bucket;

public class BucketService {

    private static BucketService bucketService;

    private BucketDao bucketDao;

    public static BucketService getInstance() {
        if (bucketService == null) {
            bucketService = new BucketService();
        }
        return bucketService;
    }

    public BucketService() {
        bucketDao = new BucketDao();
    }

    public Bucket create(Bucket t) {
        return bucketDao.create(t);
    }

    public Bucket read(int id) {
        return bucketDao.read(id);
    }

    public void update(Bucket t) {
        bucketDao.update(t);
    }

    public void delete(Integer id) {
        bucketDao.delete(id);
    }

    public List<Bucket> readAll() {
        return bucketDao.readAll();
    }

    public List<Bucket> readAllByUserId(int userId) {
        return bucketDao.readAllByUserId(userId);
    }
}