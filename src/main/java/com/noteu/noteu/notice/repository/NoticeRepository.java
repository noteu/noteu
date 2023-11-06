package com.noteu.noteu.notice.repository;

import com.noteu.noteu.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}