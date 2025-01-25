package com.profile.protection.admin.mapper;

import com.profile.protection.admin.dto.UsersResource;
import com.profile.protection.domain.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {

    private static final Logger logger = LoggerFactory.getLogger(UserAssembler.class);

    /**
     * Convert the {@link Users} to a {@link UsersResource}.
     *
     * @param user the back end domain object
     * @return the JSON representation of an ApiKeys
     */
    public UsersResource fromUsers(Users user)
    {
        return UsersResource.builder()
                .id(user.getId())
                // skip password
                .email(user.getEmail())
                .loginName(user.getLoginName())
                .createdAt(user.getCreatedAt())
                .modifiedAt(user.getModifiedAt())
                // map roles here
                .build();
    }

}
