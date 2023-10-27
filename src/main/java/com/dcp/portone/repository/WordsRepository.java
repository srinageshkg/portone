package com.dcp.portone.repository;

import com.dcp.portone.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordsRepository extends JpaRepository<Word, Long> {
}
