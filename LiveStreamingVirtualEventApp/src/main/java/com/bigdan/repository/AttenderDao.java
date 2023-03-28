package com.bigdan.repository;

import com.bigdan.entity.Attender;
import com.bigdan.entity.Event;
import com.bigdan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttenderDao extends JpaRepository<Attender, Integer > {


    Attender findAttenderByEventAndAttenderEmail(Event event, String attenderEmail);

}
