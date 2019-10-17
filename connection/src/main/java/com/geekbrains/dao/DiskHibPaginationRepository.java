package com.geekbrains.dao;

import com.geekbrains.entities.DiskHib;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiskHibPaginationRepository extends PagingAndSortingRepository<DiskHib, Long> {
}
