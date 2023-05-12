package com.petkpetk.service.domain.community.repository;

import java.util.List;

public interface HashtagRepositoryCustom {

	List<String> findAllByOrderByHitDesc();
}
