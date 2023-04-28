package com.example.globallogicchallenge.services;

import com.example.globallogicchallenge.Authorization.JWTService;
import com.example.globallogicchallenge.entities.Phone;
import com.example.globallogicchallenge.entities.User;
import com.example.globallogicchallenge.exeptions.BadRequestException;
import com.example.globallogicchallenge.models.SingUpRequest;
import com.example.globallogicchallenge.models.UserResp;
import com.example.globallogicchallenge.repositories.PhoneRepository;
import com.example.globallogicchallenge.repositories.UserRepository;
import com.example.globallogicchallenge.statics.Static;
import com.example.globallogicchallenge.utils.ObjectMapperUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PhoneRepository phoneRepository;
    @Autowired
    JWTService jwtService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserResp createUser(SingUpRequest user) throws RuntimeException {
        this.validData(user.getPassword(), user.getEmail());
        User newUser = ObjectMapperUtils.map(user, User.class);
        List<Phone> phones = ObjectMapperUtils.mapAll(user.getPhones(), Phone.class);
        phones.forEach(phone -> phone.setUser(newUser));


        UUID userId = UUID.randomUUID();
        newUser.setPhones(phones);
        newUser.setIsActive(true);
        newUser.setId(userId);
        newUser.setPassword(encoder.encode(user.getPassword()));
        String jwt = jwtService.createJWT(newUser.getId(), user.getPassword());
        Claims claims = Jwts.parser().setSigningKey(Static.SECRET).parseClaimsJws(jwt).getBody();
        newUser.setTokenVersion(claims.getId());
        userRepository.save(newUser);

        newUser.setPassword(user.getPassword());
        UserResp userResp = ObjectMapperUtils.map(newUser, UserResp.class);
        userResp.setToken(jwt);
        return userResp;
    }

    @Override
    public UserResp authenticateUser(String token) throws RuntimeException {
        //si autenticara ademas por password deberia usar el encoder.matches con el password que envia el user y el password encriptado de la db
        User userFromRepo = jwtService.validateJwt(token);
        Claims claims = Jwts.parser().setSigningKey(Static.SECRET).parseClaimsJws(token.split("Bearer ")[1]).getBody();
        String passwordNotEncrypted = ((String) claims.get("password"));
        userFromRepo.setLastLogin(LocalDateTime.now());
        UserResp userResp = ObjectMapperUtils.map(userFromRepo, UserResp.class);
        String jwtNew = jwtService.createJWT(userFromRepo.getId(), passwordNotEncrypted);
        Claims claimsNew = Jwts.parser().setSigningKey(Static.SECRET).parseClaimsJws(jwtNew).getBody();
        userFromRepo.setTokenVersion(claimsNew.getId());
        userResp.setToken(jwtNew);
        userResp.setPassword(passwordNotEncrypted);

        userRepository.save(userFromRepo);

        return userResp;
    }

    public void validData(String password, String email) throws RuntimeException  {
        if (Objects.isNull(email) || Objects.isNull(password)) {
            throw new BadRequestException("email or password are mandatory in the request");
        }
        this.validEmail(email);
        this.validPassword(password);
    }


    public void validPassword(String password) throws RuntimeException  {
        Matcher matcherPassword = Static.VALID_PASSWORD_REGEX.matcher(password);
        if (!matcherPassword.find()) {
            throw new BadRequestException("Invalid password format");
        }
    }

    public void validEmail(String email) throws RuntimeException {
        Matcher matcherEmail = Static.VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (!matcherEmail.find()) {
            throw new BadRequestException("Invalid email format");
        }
    }


}
