package com.platformbuilders.service;

import com.platformbuilders.exception.UserExceptions;
import com.platformbuilders.model.User;
import com.platformbuilders.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getUsers(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<User> pagedResult = repository.findAll(paging);
        if(pagedResult.hasContent())
            return pagedResult.getContent();

        return new ArrayList<User>();
    }

    public User getUser(long id) throws UserExceptions.UserNotFoundException {
        Optional<User> user = repository.findById(id);
        if(user.isPresent())
            return user.get();

        throw new UserExceptions.UserNotFoundException();
    }

    public User saveUser(User s){
        return repository.save(s);
    }

    public User updateUser(long id, String name, String cpf, Date birthdate) throws UserExceptions.CannotUpdateUserException {
        Optional<User> optionalUser = repository.findById(id);
        if(optionalUser.isPresent() && (!name.isEmpty() || !cpf.isEmpty() || birthdate != null)) {
            User user = optionalUser.get();
            if(!name.isEmpty()) user.setName(name);
            if(!cpf.isEmpty()) user.setCpf(cpf);
            if(birthdate != null) user.setBirthday(birthdate);

            saveUser(user);
        }

        throw new UserExceptions.CannotUpdateUserException();
    }

    public boolean deleteUser(long id) throws UserExceptions.UserNotFoundException {
        Optional<User> user = repository.findById(id);
        if(user.isPresent())
            repository.delete(user.get());

        throw new UserExceptions.UserNotFoundException();
    }

    public long getTotal(){
        return repository.count();
    }
}
