package com.naveen.springbootdeepdive.repository;

import com.naveen.springbootdeepdive.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

    Community findByName(String name);

}
