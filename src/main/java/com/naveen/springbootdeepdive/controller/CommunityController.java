package com.naveen.springbootdeepdive.controller;

import com.naveen.springbootdeepdive.model.Community;
import com.naveen.springbootdeepdive.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommunityController {

    @Autowired
    private CommunityRepository communityRepository;

    @GetMapping("/communities")
    public ResponseEntity<List<Community>> getAllCommunities() {
        return new ResponseEntity<>(communityRepository.findAll(), HttpStatus.OK);
    }
}
