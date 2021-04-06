package com.xcm.tcpservice.common.mongodbrepository;

import com.xcm.tcpservice.common.pojo.DataModel;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @描述
 * @创建人 xcm
 * @创建时间 2021/3/30
 */

public interface DataRepository extends MongoRepository<DataModel,String> {

}