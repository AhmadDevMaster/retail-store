package com.retail.store.discount.repo;

import com.retail.store.discount.model.Discount;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRulesDBRepository extends MongoRepository<Discount, ObjectId> {


	Discount findByRuleType(String ruleType);
	
}
