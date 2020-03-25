package ShopProject.services;

import ShopProject.dao.BucketDao;
import ShopProject.entities.Bucket;

import java.util.List;
import java.util.Optional;

public class BucketService {

    private static BucketService bucketService;
    private BucketDao bucketDao;

    public BucketService() {
        this.bucketDao = new BucketDao();
    }

    public static BucketService getInstance(){
        if (bucketService == null){
            bucketService = new BucketService();
        }
        return bucketService;
    }

    public Bucket create (Bucket t){
        return bucketDao.create(t);
    }

    public Optional<Bucket> read (int id){
        return bucketDao.read(id);
    }

    public void update (Bucket t){
        bucketDao.update(t);
    }

    public void delete (int id){

        bucketDao.delete(id);
    }

    public Optional<List<Bucket>> readAll (){
        return bucketDao.readAll();
    }
}