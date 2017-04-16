package com.nwook.wallet.db.dao;

import com.nwook.wallet.db.model.UserDbEntity;
import com.nwook.wallet.db.model.WalletDbEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface WalletDao extends CrudRepository<WalletDbEntity, Long> {

    WalletDbEntity findByWalletId(final Long walletId);

    WalletDbEntity findByUserDbEntity(final UserDbEntity userDbEntity);
}
