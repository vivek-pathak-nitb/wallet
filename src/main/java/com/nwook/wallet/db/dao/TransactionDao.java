package com.nwook.wallet.db.dao;

import com.nwook.wallet.db.model.TransactionDbEntity;
import com.nwook.wallet.db.model.WalletDbEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TransactionDao extends CrudRepository<TransactionDbEntity, Long> {

    List<TransactionDbEntity> findByWalletDbEntity(final WalletDbEntity walletDbEntity);
}
