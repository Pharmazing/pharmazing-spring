package com.vincentvanity.pharmazings.resolvers;

import com.vincentvanity.pharmazings.entities.User;
import com.vincentvanity.pharmazings.models.CreateUserInput;
import com.vincentvanity.pharmazings.respositories.UserRepository;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Slf4j
@Controller
@CrossOrigin
@Service
public class UsersController {

    private UserRepository userRepository;

    GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long")
            .aliasedScalar(ExtendedScalars.GraphQLLong)
            .build();

    @Autowired
    public UsersController (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @QueryMapping
    @PreAuthorize("isAuthenticated()")
    public Iterable<User> getAllUsers() {
        log.info("GQLQuery:: GetAllUsers");
        return userRepository.findAll();
    }

    @MutationMapping
    public User createUser(@Argument CreateUserInput user){
        User newUser = new User(user.firstName(), user.lastName(), user.email(), user.password(), user.age());
        userRepository.save(newUser);
        return newUser;
    }
}
