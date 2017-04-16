package com.nwook.wallet.db.dao;

import com.nwook.wallet.db.model.UserDbEntity;
import com.nwook.wallet.db.model.WalletDbEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;

@Transactional
@Repository
public interface UserDao extends CrudRepository<UserDbEntity, Long> {
    UserDbEntity findByUserId(final Long userId);
}
