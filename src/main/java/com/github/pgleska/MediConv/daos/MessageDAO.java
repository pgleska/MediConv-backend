package com.github.pgleska.MediConv.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.pgleska.MediConv.entities.Message;

public interface MessageDAO extends JpaRepository<Message, Integer> {
	List<Message> findAllByAuthorIdInAndReceiverIdInOrderByTimestampDesc(List<Integer> authorIds, List<Integer> receiversId);

}
