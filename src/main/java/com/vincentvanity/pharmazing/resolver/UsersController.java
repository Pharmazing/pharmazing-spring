package com.vincentvanity.pharmazing.resolver;

import com.vincentvanity.pharmazing.entities.User;
import com.vincentvanity.pharmazing.models.CreateUserInput;
import com.vincentvanity.pharmazing.respositories.UserRepository;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Controller
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
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @MutationMapping
    public User createUser(@Argument CreateUserInput user){
        User newUser = new User(user.firstName(), user.lastName(), user.email(), user.password(), user.age());
        userRepository.save(newUser);
        return newUser;
    }
}
